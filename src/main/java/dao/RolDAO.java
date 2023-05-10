package dao;


import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Rol;

public class RolDAO {
	public static Rol getRol(int id) {
		Rol rol=null;
		Session sesion=HibernateManager.getSessionFactory().openSession();
		Transaction transaccion=sesion.beginTransaction();
		rol=(Rol) sesion.get(Rol.class, id);
		transaccion.commit();
		sesion.close();
		System.out.println(rol.getNombre());
		return rol;
	}
}
