package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import modelo.Articulo;
import modelo.Detalle;
import modelo.Estado;
import modelo.Pedido;
import modelo.Usuario;

public class CompraDAO {
	public static ArrayList<Detalle> insertarPedido(HashMap<Integer, Articulo> articulos, Usuario usuario) {
		double total = 0;
		int idDetalle = 1;
		int idPedido = getLastId()+1;
		for (Entry<Integer, Articulo> articulo : articulos.entrySet()) {
			Articulo art = articulo.getValue();
			Articulo articuloEscogido=ArticuloDAO.obtenerArticulo(art.getId());
			if (art.getCantidad() > 0&&art.getCantidad()<articuloEscogido.getStock()) {
				total += art.getPrecio() * art.getCantidad();
			}
		}
		
		
		ArrayList<Detalle> detalles = new ArrayList<>();
		Session sesion=HibernateManager.getSessionFactory().openSession();
		Transaction tx= sesion.beginTransaction();
		Pedido pedido= new Pedido(idPedido, usuario, "tarjeta", 0, total, Timestamp.valueOf(LocalDateTime.now()),EstadoDao.getEstado("PE"));
		sesion.save(pedido);
		tx.commit();
		sesion.close();
		for (Entry<Integer, Articulo> articulo : articulos.entrySet()) {
			Articulo art = articulo.getValue();
			Articulo articuloEscogido=ArticuloDAO.obtenerArticulo(art.getId());
			if (art.getCantidad() > 0&&art.getCantidad()<articuloEscogido.getStock()) {
				Detalle detalle =new Detalle(idDetalle,pedido,art,art.getCantidad(),art.getPrecio(),art.getImpuesto(),art.getCantidad()*art.getPrecio());
				DetalleDAO.introducirDetalle(detalle);
				idDetalle++;
			}
		}
		
//		String sql = "INSERT INTO pedido (usuario_id,metodopago,total,num_factura,fecha) values(?,?,?,?,NOW());";

//		Connection conexion = Conexion.getConexion();
//		try {
//			PreparedStatement ps = conexion.prepareStatement(sql);
//			ps.setInt(1, usuario.getId());
//			ps.setString(2, "tarjeta");
//			ps.setDouble(3, total);
//			ps.setInt(4, getNumFactura());
//			ps.execute();
//			conexion.commit();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		idPedido = getLastId();
//		for (Entry<Integer, Articulo> articulo : articulos.entrySet()) {
//			Articulo art = articulo.getValue();
//			Articulo articuloEscogido=ArticuloDAO.obtenerArticulo(art.getId());
//			if (art.getCantidad() > 0&&art.getCantidad()<articuloEscogido.getStock()) {
//				Detalle detalle =new Detalle(idDetalle)
//				DetalleDAO.introducirDetalle(detalle);
//				idDetalle++;
//			}
//		}
		return detalles;
	}

	private static int getLastId() {
	    try {
	        Session session = HibernateManager.getSessionFactory().openSession();
	        Transaction tx = session.beginTransaction();
	        Query query = session.createQuery("select max(id) from Pedido");
	        int maxId = (Integer) query.uniqueResult();
	        tx.commit();
	        session.close();
	        return maxId;
	    } catch (HibernateException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}

	private static int getNumFactura() {
		String sql = "SELECT max(num_factura) from pedido;";
		Connection con = Conexion.getConexion();

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public static List<Pedido> getPedidosPorCliente(Usuario u, String filtro) {
	    List<Pedido> pedidos = new ArrayList<>();
	    Session session=HibernateManager.getSessionFactory().openSession();
	    String hql = "SELECT p FROM Pedido p WHERE p.usuario.id = "+u.getId()+filtro;
	    pedidos =session.createQuery(hql, Pedido.class).getResultList();
	  
		
//		String sql="SELECT * from pedido where usuario_id=? "+filtro+";";
//		Connection conexion=Conexion.getConexion();
//		try {
//			PreparedStatement ps=conexion.prepareStatement(sql);
//			ps.setInt(1, idCliente);
//			ResultSet rs=ps.executeQuery();
//			while(rs.next()) {
//				pedidos.add(new Pedido(rs.getInt(1),rs.getInt(2),rs.getString(4), rs.getInt(5), rs.getDouble(6),rs.getDate(3)));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return pedidos;
	}
	public static Pedido getPedido(int id) {
		Pedido pedido=null;
		Session sesion=HibernateManager.getSessionFactory().openSession();
		pedido=sesion.get(Pedido.class, id);
		sesion.close();
		return pedido;
	}
	public static void cancelarPedido(int idPedido) {
		Estado estado=EstadoDao.getEstado("C");
		Pedido pedido=getPedido(idPedido);
		pedido.setEstado(estado);
		Session sesion=HibernateManager.getSessionFactory().openSession();
		Transaction tx=sesion.beginTransaction();
		sesion.update(pedido);
		List<Detalle> detalles=DetalleDAO.getDetallePedido(pedido.getIdPedido());
		for(Detalle detalle:detalles) {
			Articulo articulo=ArticuloDAO.obtenerArticulo(detalle.getProducto().getId());
			articulo.setStock(articulo.getStock()+detalle.getUnidades());
			sesion.update(articulo);
		}
		tx.commit();
		sesion.close();
	}
	public static int enviarPedido(int idPedido) {
		int numFactura=getNumFactura();
		Estado estado=EstadoDao.getEstado("E");
		Pedido pedido=getPedido(idPedido);
		pedido.setEstado(estado);
		pedido.setNumFactura(numFactura);
		Session sesion=HibernateManager.getSessionFactory().openSession();
		Transaction tx=sesion.beginTransaction();
		sesion.update(pedido);
		tx.commit();
		sesion.close();
		return numFactura;
	}
}
