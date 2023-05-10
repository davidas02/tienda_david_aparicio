package configuracion;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import filtros.ComprobarCarritoFilter;
import filtros.SessionFilter;

/**
 * Application Lifecycle Listener implementation class MiListener
 *
 */
@WebListener
public class MiListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        
    	// Código que se ejecutará al arrancar la aplicación
    	ServletContext context = event.getServletContext();

        // Register filters
        FilterRegistration.Dynamic filter1 = context.addFilter("Filter1", SessionFilter.class);
        FilterRegistration.Dynamic filter2 = context.addFilter("Filter2", ComprobarCarritoFilter.class);
       
        System.out.println("Hemos arrancado y configurado la app");
       
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Código que se ejecutará al detener la aplicación
    }
}
