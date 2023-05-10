package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class Conexion {
	
	  
     
	static Connection conexion; //atributo para  guardar el objeto Connection
        
	public static Connection getConexion() {
	   //SINGLETON
    	if (conexion == null) {
	    	crearConexion();
	    }
    	
	    return conexion;
	    
    }
    
    // devuelve true si se ha creado correctamente
    public static boolean crearConexion() {
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	URL appResourceURL = loader.getResource("bbdd.properties");
    	String fichero = appResourceURL.getPath();

        try {
            Properties propiedades = new Properties();
			propiedades.load(new FileInputStream(fichero));
		   	 String url=propiedades.getProperty("url");
		   	 String user=propiedades.getProperty("usuario");
		   	try {
		        //cargo el driver
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        conexion = DriverManager.getConnection(url,user,"");    
	              System.out.println("conectado");  
	          conexion.setAutoCommit(false);
		        
		    } catch (SQLException e) {
		    	System.out.println(e.getMessage());
		    	return false;
		    }
		    catch (Exception e) {
		    	System.out.println(e.getMessage());
		    	return false;
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	    
	    return true;
    }

    public static void desconectar(){
    	try {
            conexion.close();
            conexion = null;
            System.out.println("La conexion a la  base de datos ha terminado");
    	
    	} catch (SQLException e) {
    		System.out.println("Error al cerrar la conexion");
        }
    }
    
    public static void main(String[] args) {
		getConexion();
	}
   
}
