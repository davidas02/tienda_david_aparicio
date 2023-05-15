package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ConexionDAO;
import dao.RolDAO;
import modelo.Usuario;
import service.ServicioLogin;



/**
 * Servlet implementation class Alta
 */
@WebServlet("/alta")
public class Alta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Alta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String email=request.getParameter("email");
		String password1=request.getParameter("password1");
		String password2=request.getParameter("password2");
		String nombre=request.getParameter("nombre");
		String apellidos=request.getParameter("apellidos");
		
		if(!email.equals("")&&!password1.equals("")&&!password2.equals("")) {
			if(ConexionDAO.comprobarUsuarioEnBD(email)==null) {
				Usuario usuario=new Usuario(0,RolDAO.getRol(2),email,Usuario.encriptarPassword(password1),nombre,apellidos,false);
				ConexionDAO.insertarUsuario(usuario);
				request.setAttribute("login", "Usuario "+ email+" creado con exito");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else {
				request.setAttribute("error", "Ya existe un usuario en la base de datos con este email");
			}
		}else {
			request.setAttribute("error", "Alguno de los campos está vacío");
		}
		request.getRequestDispatcher("alta.jsp").forward(request, response);
	}

}
