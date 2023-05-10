package dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Articulo;

//Clase DAO que se encarga de obtener los artículos desde la base de datos
public class ArticuloDAO {

	public ArticuloDAO() {
		
	}

	public static List<Articulo> obtenerCatalogo(String filtro) {
		List<Articulo> catalogo = new ArrayList<>();
		Session sesion=HibernateManager.getSessionFactory().openSession();
		Transaction tx=sesion.beginTransaction();
		catalogo=sesion.createQuery("From Articulo "+filtro).getResultList();
		tx.commit();
		sesion.close();
		
//		try {
//			Connection conexion=Conexion.getConexion();
//			Statement statement = conexion.createStatement();
//			String sql="SELECT * FROM producto "+filtro+ " limit 24;";
//			ResultSet resultSet = statement.executeQuery(sql);
//			while (resultSet.next()) {
//				int id = resultSet.getInt("id");
//				String nombre = resultSet.getString("nombre");
//				String descripcion = resultSet.getString("descripcion");
//				double precio = resultSet.getDouble("precio");
//				Blob imagen = resultSet.getBlob("imagen");
//				int cantidad=resultSet.getInt("stock");
//				float impuesto=resultSet.getFloat("impuesto");
//				int idCategoria=resultSet.getInt("id_categoria");
//				if (imagen != null) {
//					
//					Articulo articulo = new Articulo(id, nombre, descripcion, precio, imagen,cantidad,impuesto,CategoriaDAO.getCategoria(idCategoria));
//					catalogo.add(articulo);
//				}else {
//					Articulo articulo = new Articulo(id, nombre, descripcion, precio, null,cantidad,impuesto,CategoriaDAO.getCategoria(idCategoria));
//					catalogo.add(articulo);
//				}
//			}
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			Conexion.desconectar();
//		}
		return catalogo;
	}

	public static Articulo obtenerArticulo(int id) {
		Articulo articulo=null;
		Session sesion=HibernateManager.getSessionFactory().openSession();
		Transaction transaccion=sesion.beginTransaction();
		try {
			articulo=sesion.get(Articulo.class,id);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			transaccion.commit();
			sesion.close();
		}
		
		
		return articulo;
		
	}
	
	
}