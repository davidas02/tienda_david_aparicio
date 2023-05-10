package controlador;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CompraDAO;
import modelo.Articulo;
import modelo.Usuario;
/**
 * Servlet implementation class FinalizarCompra
 */
@WebServlet("/finalizar")
public class FinalizarCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizarCompra() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HashMap<Integer,Articulo> articulos=(HashMap<Integer, Articulo>) request.getSession().getAttribute("carrito");
	    Cookie[] cookies=request.getCookies();
	    Usuario user=(Usuario) request.getSession().getAttribute("usuario");
	    if(user!=null) {
	    	if(articulos!=null) {
				CompraDAO.insertarPedido(articulos, user);
				request.getSession().setAttribute("carrito",new HashMap<Integer, Articulo>());
				request.getRequestDispatcher("compraFinalizada.jsp").forward(request, response);
			    request.getSession().removeAttribute("comprando");
			    if(cookies!=null) {
			    	for(Cookie cookie:cookies) {
			    		if(cookie.getName().equals("carrito")) {
			    			cookie.setMaxAge(0);
			    			cookie.setValue(null);
			    			response.addCookie(cookie);
			    		}
			    	}
			    }
		    }else {
		    	request.getRequestDispatcher("./").forward(request, response);
		    }
	    }else {
	    	request.getSession().setAttribute("comprando", true);
	    	request.getRequestDispatcher("login.jsp").forward(request, response);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
