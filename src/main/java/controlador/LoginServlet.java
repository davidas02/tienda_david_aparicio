package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.StrongPasswordEncryptor;

import dao.ConexionDAO;
import modelo.Usuario;
import service.ServicioLogin;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		

			String usuario = request.getParameter("usuario");
			String password = request.getParameter("password");
			if (usuario != "") {
				Usuario user = ServicioLogin.comprobarUsuario(usuario, password);
				if (user != null) {
					HttpSession sesion = request.getSession();
					sesion.setAttribute("usuario", user);
					Cookie cookie= new Cookie("UsuarioTienda", user.getEmail());
					cookie.setMaxAge(60*60*24);
					response.addCookie(cookie);
					if(request.getSession().getAttribute("comprando") != null) {
					request.getRequestDispatcher("carrito.jsp").forward(request, response);
					}else {
						request.getRequestDispatcher("").forward(request, response);
					}
				} else {
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
