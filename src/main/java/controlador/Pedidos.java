package controlador;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CompraDAO;
import modelo.Usuario;

/**
 * Servlet implementation class Pedidos
 */
@WebServlet("/pedidos")
public class Pedidos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pedidos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession sesion= request.getSession();
		Usuario user=(Usuario) sesion.getAttribute("usuario");
		String fecha1=request.getParameter("fecha1");
		String fecha2=request.getParameter("fecha2");
		if(fecha1!=null&&!fecha1.equals("")&&fecha2!=null&&!fecha2.equals("")) {
			request.setAttribute("pedidos",CompraDAO.getPedidosPorCliente(user,"and fecha between '"+Date.valueOf(fecha1)+"' and '"+Date.valueOf(fecha2)+"'"));
		}
		else {
			if(fecha1!=null&&!fecha1.equals("")) {
				request.setAttribute("pedidos",CompraDAO.getPedidosPorCliente(user,"and fecha between '"+Date.valueOf(fecha1)+"' and now()"));
			}else if(fecha2!=null&&!fecha2.equals("")) {
				request.setAttribute("pedidos",CompraDAO.getPedidosPorCliente(user,"and fecha between '2000-01-01' and '"+Date.valueOf(fecha2)+"'"));
			}else {
				request.setAttribute("pedidos",CompraDAO.getPedidosPorCliente(user,""));
			}
		}
		request.getRequestDispatcher("pedidos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
