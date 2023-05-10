package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Estado;

public class EstadoDao {
	public static List<Estado> listaEstados(){
		List<Estado> estados=null;
		Session sesion=HibernateManager.getSessionFactory().openSession();
		estados=sesion.createQuery("FROM Estado").list();
		sesion.close();
		return estados;
	}
	public static Estado getEstado(String letra) {
		Estado estado=null;
		Session sesion=HibernateManager.getSessionFactory().openSession();
		estado=sesion.get(Estado.class, letra);
		sesion.close();
		return estado;
	}
	
	public static void insertarEstado(Estado e) {
		Session sesion=HibernateManager.getSessionFactory().openSession();
		Transaction tx =sesion.beginTransaction();
		
			sesion.save(e);
			
		
		tx.commit();
		sesion.close();
	}
}
