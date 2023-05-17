package controlador;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.SerializationUtils;

import dao.ArticuloDAO;
import dao.CategoriaDAO;
import dao.ConfiguracionDAO;
import modelo.Articulo;
import modelo.Categoria;

//Controlador que se encarga de recuperar la lista de artículos desde la base de datos y mostrarlos en la vista
@WebServlet("")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Categoria> categorias=CategoriaDAO.getCategorias();
		List<Articulo> catalogo = ArticuloDAO.obtenerCatalogo("");
		request.setAttribute("catalogo", catalogo);
		request.setAttribute("categorias", categorias);
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie:cookies) {
				if(cookie.getName().equals("carrito")) {
					byte[] carritoBytes = Base64.getDecoder().decode(cookie.getValue().getBytes("UTF-8"));
	                HashMap<Integer, Articulo> carrito = (HashMap<Integer, Articulo>)SerializationUtils.deserialize(carritoBytes);
				}
			}
		}
		if (request.getSession(false) == null) {
			request.getSession();
			request.getSession().setAttribute("carrito", new HashMap<Integer, Articulo>() );
			request.getSession().setAttribute("categorias", categorias);
			
		}
		request.getSession().setAttribute("configuracion", ConfiguracionDAO.get());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Categoria> categorias=CategoriaDAO.getCategorias();
		//doGet(request, response);
		List<Articulo> catalogo = ArticuloDAO.obtenerCatalogo("");
		request.setAttribute("catalogo", catalogo);
		request.setAttribute("categorias", categorias);
		if(request.getParameter("escogerCategoria")!=null) {
			
			int categoria=Integer.parseInt(request.getParameter("escogerCategoria"));
			System.out.println(categoria);
			if(categoria>0) {
				if(request.getParameter("min")!=null&&request.getParameter("max")!=null) {
					if(!request.getParameter("min").equals("")&&!request.getParameter("max").equals("")) {
					int min=Integer.parseInt(request.getParameter("min"));
					int max=Integer.parseInt(request.getParameter("max"));
					catalogo = ArticuloDAO.obtenerCatalogo("where categoria.id="+categoria+"and precio between "+min+" and "+max);
					request.setAttribute("catalogo", catalogo);
					
					}else {catalogo = ArticuloDAO.obtenerCatalogo("where categoria.id="+categoria);
						request.setAttribute("catalogo", catalogo);
					}
				}
			}else {
				if(request.getParameter("min")!=null&&request.getParameter("max")!=null) {
					if(!request.getParameter("min").equals("")&&!request.getParameter("max").equals("")) {
					int min=Integer.parseInt(request.getParameter("min"));
					int max=Integer.parseInt(request.getParameter("max"));
					catalogo = ArticuloDAO.obtenerCatalogo("where precio between "+min+" and "+max);
					request.setAttribute("catalogo", catalogo);
				}
			}
			
		}
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}