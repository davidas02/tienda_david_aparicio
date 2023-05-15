package service;
//
import java.io.IOException;
//
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
//
public class MiLog {
//	
	private static Logger log = Logger.getLogger(MiLog.class);
//
	public static void main(String[] args) {
//		//OPCION 1: Salida por consola
//		//BasicConfigurator.configure();
//		
//		//OPCION 2: Configuraci�n a trav�s de Clases
//		try {
//			log.addAppender(new FileAppender(new PatternLayout(),"./ficheros/prueba.log", false));
//		} catch (IOException e) {
//			e.printStackTrace();
//			log.error(e.toString());
//		}
//		
//		//OPCION 3: Configuraci�n a trav�s de fichero de propiedades
		PropertyConfigurator.configure("./properties/log4j.properties");
		
		log.debug("DEBUG");
		log.info("INFO");
		log.warn("WARN");
		log.error("ERROR");
		log.fatal("FATAL");
		
	}

}
