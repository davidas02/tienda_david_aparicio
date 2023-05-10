package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.protocol.Resultset;

import modelo.Categoria;

public class CategoriaDAO {
	public static List<Categoria> getCategorias(){
		Session sesion=HibernateManager.getSessionFactory().openSession();
		  Transaction transaccion=sesion.beginTransaction();
		  List<Categoria>categorias= sesion.createQuery("from Categoria").getResultList();
		  transaccion.commit();
		  sesion.close();
		  return categorias;
	}
	public static Categoria getCategoria(int id){
		Categoria categoria=null;
		Session sesion=HibernateManager.getSessionFactory().openSession();
		  Transaction transaccion=sesion.beginTransaction();
		  categoria=sesion.get(Categoria.class, id);
		  transaccion.commit();
		  sesion.close();
		  return categoria;
	}
}
