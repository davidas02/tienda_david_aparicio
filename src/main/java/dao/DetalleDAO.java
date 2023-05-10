package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Detalle;
import modelo.Pedido;

public class DetalleDAO {
	public static void introducirDetalle(Detalle detalle) {
		Session session=HibernateManager.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
		session.save(detalle);
		tx.commit();
		session.close();
//		int devolver=0;
//		Connection conexion=Conexion.getConexion();
//		String sql="INSERT INTO detalle(id,impuesto,pedido_id,preciounidad,producto_id,total,unidades) values(?,?,?,?,?,?,?);";
//		PreparedStatement ps;
//		try {
//			ps = conexion.prepareStatement(sql);
//			ps.setInt(1, detalle.getId());
//			ps.setDouble(2, detalle.getImpuestos());
//			ps.setInt(3, detalle.getPedido().getIdPedido());
//			ps.setDouble(4,detalle.getPrecioPorUnidad());
//			ps.setInt(5, detalle.getProducto().getId());
//			ps.setDouble(6, detalle.getTotal());
//			ps.setInt(7, detalle.getUnidades());
//			ps.executeUpdate();
//			conexion.commit();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return devolver;
	}
	public static List<Detalle> getDetallePedido(int id){
		List<Detalle> detalle=new ArrayList<>();
		Session sesion=HibernateManager.getSessionFactory().openSession();
		Transaction tx=sesion.beginTransaction();
		detalle = sesion.createQuery("FROM Detalle where pedido.idPedido=:idPedido").setParameter("idPedido", id).getResultList();
		tx.commit();
		sesion.close();
		return detalle;
//		String sql="Select * from detalle where pedido_id=?;";
//		Connection conexion=Conexion.getConexion();
//		PreparedStatement ps;
//		try {
//			ps = conexion.prepareStatement(sql);
//			ps.setInt(1, idPedido);
//			ResultSet rs=ps.executeQuery();
//			while (rs.next()) {
//				detalle.add(new Detalle(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5), rs.getDouble(6),rs.getDouble(7)));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
