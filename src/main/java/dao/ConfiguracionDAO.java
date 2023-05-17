package dao;

import org.hibernate.Session;

import modelo.Configuracion;

public class ConfiguracionDAO {
public static Configuracion get() {
	Session sesion=HibernateManager.getSessionFactory().openSession();
	 Configuracion config=(Configuracion) sesion.createQuery("SELECT c FROM Configuracion c").getResultList().get(0);
	 sesion.close();
	 return config;
	
}
}
