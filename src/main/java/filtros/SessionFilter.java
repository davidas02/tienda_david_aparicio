	package filtros;

import java.io.IOException;

/**
 * Servlet Filter implementation class SessionFilter
 */
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 
// @WebFilter(urlPatterns = {"/compraFinalizada.jsp", "/perfil.jsp", "/finalizar"}, filterName = "Filter1")
 public class SessionFilter implements Filter {

     private ServletContext context;

     public void init(FilterConfig config) throws ServletException {
         this.context = config.getServletContext();
         this.context.log("SessionFilter initialized");
     }

     
     
     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
         HttpServletRequest req = (HttpServletRequest) request;
         HttpServletResponse res = (HttpServletResponse) response;
         HttpSession session = req.getSession(false);

         String uri = req.getRequestURI();
         this.context.log("Requested Resource::" + uri);

         
         System.out.println("Estamos en filtro session");
         
         // si la sesión no existe o el usuario no está en la sesión
         if ( session.getAttribute("usuario") == null) {
//             this.context.log("Unauthorized access request");
//             System.out.println("No pasamos el filtro de sesion");
             
         //  Obtener la URL de la página anterior (si existe)
     	    String referer = req.getHeader("Referer");
     	    
     	    if(referer.contains("carrito"))
     	    {
     	    	req.getSession().setAttribute("comprando", true);
     	    }
//             
             request.getRequestDispatcher("login.jsp").forward(request, response);
             return;
         } else {
             // si el usuario está en la sesión, permitimos el acceso
        	
        	 
        	 //System.out.println("Pasamos el filtro de sesion en primera ejecucion");
             chain.doFilter(request, response);
            // System.out.println("Pasamos el filtro de sesion en segunda ejecucion");
         }
     }

     public void destroy() {
         //close any resources here
     }
 }
