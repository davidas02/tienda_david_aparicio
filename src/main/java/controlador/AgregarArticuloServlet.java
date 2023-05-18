package controlador;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;

import dao.ArticuloDAO;
import modelo.Articulo;

@WebServlet("/agregar")
public class AgregarArticuloServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3794898853696467410L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArticuloDAO articuloDAO = new ArticuloDAO();

        // Obtenemos los parámetros del formulario
        int id = Integer.parseInt(request.getParameter("id"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        
        // Obtenemos el artículo correspondiente a partir del ID
        Articulo articulo = null;
		try {
			articulo = articuloDAO.obtenerArticulo(id);
			articulo.setImagen(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Obtenemos el carrito de la sesión o lo creamos si no existe
        HashMap<Integer, Articulo> carrito = (HashMap<Integer, Articulo>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new HashMap<>();
            articulo.setCantidad(cantidad);
            System.out.println(Double.parseDouble(String.format("%.2f",articulo.getPrecio()).replace(",", ".")));
            articulo.setPrecio(Double.parseDouble(String.format("%.2f",articulo.getPrecio()).replace(",", ".")));
            carrito.put(articulo.getId(), articulo);
            session.setAttribute("carrito", carrito);
            
        }else {
	        // Agregamos el artículo al carrito o actualizamos su cantidad
	        if (carrito.containsKey(id)) {
	            Articulo articuloExistente = carrito.get(id);
	            articuloExistente.setImagen(null);
	            if(articuloExistente.getCantidad()+cantidad<=articuloExistente.getStock()) {
		            if(articuloExistente.getCantidad()+cantidad>0) {
		            	System.out.println(cantidad+"   "+articuloExistente.getCantidad());
		            	articuloExistente.setCantidad(articuloExistente.getCantidad()+cantidad);
		            	System.out.println(Double.parseDouble(String.format("%.2f",articuloExistente.getPrecio()).replace(",", ".")));
		            	articuloExistente.setPrecio(Double.parseDouble(String.format("%.2f",articuloExistente.getPrecio()).replace(",", ".")));
		            	carrito.replace(id, articuloExistente);
		            }else {
		            	if(articuloExistente.getCantidad()<=0) {
		            		
		            		carrito.remove(articulo.getId());
		            	}else {
		            		
		            		articuloExistente.setCantidad(articuloExistente.getCantidad()+cantidad);
		            		System.out.println(Double.parseDouble(String.format("%.2f",articuloExistente.getPrecio()).replace(",", ".")));
		            		articuloExistente.setPrecio(Double.parseDouble(String.format("%.2f",articuloExistente.getPrecio()).replace(",", ".")));
		            		carrito.replace(id, articulo, articuloExistente);
		            	}
		            }
	            }
	        } else {
	        	articulo.setImagen(null);
	            articulo.setCantidad(cantidad);
	            carrito.put(id, articulo);
	        }
        }
        ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(carrito);
		try {
		    // Codificar el valor de la cookie
			// Convertir HashMap a String y codificarlo en Base64
	        String carritoString = new String(Base64.getEncoder().encode(SerializationUtils.serialize(carrito)), "UTF-8");
	        
	        // Crear cookie y añadir valor
	        Cookie cookie = new Cookie("carrito", carritoString);
	        cookie.setMaxAge(60*60*24);
	        response.addCookie(cookie);
		    
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}
        request.getSession().setAttribute("carrito", carrito);

        // Redireccionamos a la página de visualización del carrito
        request.getRequestDispatcher("carrito.jsp").forward(request, response);
    	   }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
