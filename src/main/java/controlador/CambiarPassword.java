package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ConexionDAO;
import modelo.Usuario;
import service.ServicioLogin;

/**
 * Servlet implementation class CambiarPassword
 */
@WebServlet("/cambiarPassword")
public class CambiarPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiarPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String email= request.getParameter("email");
		String passwordAntigua=request.getParameter("passwordAntiguo");
		String password1=request.getParameter("passwordNuevo1");
		String password2=request.getParameter("passwordNuevo2");
		if(!email.equals("")&&!password1.equals("")&&!password2.equals("")) {
			if(password1.equals(password2)) {
			if(ServicioLogin.comprobarUsuario(email, passwordAntigua)!=null) {
				ConexionDAO.cambiarPassword(email,Usuario.encriptarPassword(password1));
				request.getRequestDispatcher("./").forward(request, response);
			}else {
				request.setAttribute("error", "Contraseña Incorrecta");
				request.getRequestDispatcher("perfil.jsp").forward(request, response);
			}
			}else {
				request.setAttribute("error", "La nueva contraseña no coincide con la repeticion");
				request.getRequestDispatcher("perfil.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("error", "Alguno de los campos está vacío");
			request.getRequestDispatcher("perfil.jsp").forward(request, response);
		}
		
	}

}
