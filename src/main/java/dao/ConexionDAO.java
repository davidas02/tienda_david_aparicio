package dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jasypt.util.password.StrongPasswordEncryptor;

import modelo.Usuario;

public class ConexionDAO {
	public static Usuario comprobarUsuarioEnBD(String email) {
		Usuario usuario=null;
		  Session sesion=HibernateManager.getSessionFactory().openSession();
		  Transaction transaccion=sesion.beginTransaction();
		  Query query=sesion.createQuery("SELECT u FROM Usuario u WHERE u.email=:email");
		  query.setParameter("email", email);
		  try {
			  usuario=(Usuario) query.getSingleResult();
		  }catch(NoResultException nre) {
			  System.out.println(nre.getMessage());
		  }finally {
		  transaccion.commit();
		  sesion.close();
		  }
		  return usuario;
	}
	public static Usuario comprobarDatos(String mail, String password) {
		Usuario u=null;
		Usuario u2=comprobarUsuarioEnBD(mail);
		if(u2!=null&&comprobarPasswordLogin(u2.getClave(), password)) {
			u=u2;
		}
		return u;
	}
	public static int insertarUsuario(Usuario usuario) {
		Session session=HibernateManager.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
		int i=(int)session.save(usuario);
		tx.commit();
		session.close();
		return i;
	}
	public static boolean comprobarPasswordLogin(String passwordCod,String password) {
		StrongPasswordEncryptor encriptador = new StrongPasswordEncryptor();
		return encriptador.checkPassword(password, passwordCod);
	}
	public static void cambiarPassword(String email,String password) {
		Usuario user=comprobarUsuarioEnBD(email);
		Session session=HibernateManager.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
		user.setClave(password);
		session.update(user);
		tx.commit();
		session.close();
	}
}
