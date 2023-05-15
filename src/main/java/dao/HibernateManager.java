package dao;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import service.MiLog;

/**
 * Proporciona un objeto de la clase SessionFactory para ser utilizado con Hibernate
 */
public class HibernateManager {
	
	private static SessionFactory sessionFactory;
	private static Logger log = Logger.getLogger(HibernateManager.class);
	
	private static SessionFactory configureSessionFactory() {
		try {
		Configuration configuration = new Configuration();
		configuration.configure();
		sessionFactory = configuration.buildSessionFactory();
		return sessionFactory;
		}catch (Exception e) {
			PropertyConfigurator.configure("D:/DAW2_22_23/FCT/tienda_david_aparicio_sir/properties/log4j.properties");
			log.error(e.getMessage());
		}
		return null;
	}

	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null)
			sessionFactory = configureSessionFactory();
		return sessionFactory;
	}

}
