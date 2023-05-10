package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Consulta;

public class ConsultaDAO {
	public static int insertarConsulta(Consulta consulta) {
		int id=0;
		Session sesion=HibernateManager.getSessionFactory().openSession();
		Transaction tx=sesion.beginTransaction();
		sesion.save(consulta);
		tx.commit();
		sesion.close();
		return id;
	}
	public static Consulta buscarConsulta(int id) {
		Consulta consulta=null;
		Session sesion=HibernateManager.getSessionFactory().openSession();
		Transaction tx=sesion.beginTransaction();
		consulta=sesion.get(Consulta.class, id);
		tx.commit();
		sesion.close();
		return consulta;
	}
}
