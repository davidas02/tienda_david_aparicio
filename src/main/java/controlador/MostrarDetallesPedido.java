package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticuloDAO;
import dao.CompraDAO;
import dao.DetalleDAO;
import modelo.Articulo;
import modelo.Detalle;

/**
 * Servlet implementation class MostrarDetallesPedido
 */
@WebServlet("/detalle")
public class MostrarDetallesPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarDetallesPedido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<Detalle> detalles=(ArrayList<Detalle>) DetalleDAO.getDetallePedido(Integer.parseInt(request.getParameter("idPedido")));
		ArrayList<Articulo> articulosDetalle=new ArrayList<>();
		for (Detalle detalle : detalles) {
			Articulo articulo=ArticuloDAO.obtenerArticulo(detalle.getProducto().getId());
			articulo.setPrecio(detalle.getPrecioPorUnidad());
			articulo.setCantidad(detalle.getUnidades());
			articulosDetalle.add(articulo);
		}
		request.setAttribute("numPedido",request.getParameter("idPedido"));
		request.setAttribute("detalles",articulosDetalle);
		request.getRequestDispatcher("mostrarDetalles.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
