package filtros;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modelo.Articulo;


	
//@WebFilter(urlPatterns = {"/compraFinalizada.jsp", "/perfil.jsp", "/finalizar"}, filterName = "Filtro2")

public class ComprobarCarritoFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // no se necesita inicialización
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpSession session = httpRequest.getSession(false);
    
    System.out.println("Estamos en filtro comprobarCarrito");
    
    if (session != null) {
      // Obtener el carrito de la sesión
      HashMap<Integer, Articulo> carrito = (HashMap<Integer, Articulo>) session.getAttribute("carrito");

      // Comprobar si el carrito está vacío
      if (carrito == null || carrito.isEmpty()) {
        
    	// Si está vacío, redirigir al catálogo
    	System.out.println("El carrito esta vacío");
        httpRequest.getRequestDispatcher("").forward(request, response);
        
        //Utilizamos esta instruccion para cortar la cadena de llamadas
        return;
      }
    }
    
    // Continuar con la cadena de filtros
    
    System.out.println("Nos ejecutamos en segundo orden, esto es pre");
    chain.doFilter(request, response);
    
    //Aquí normalmente hacemos cosas con la respuesta, es decir, con el objeto response
    System.out.println("Nos ejecutamos en segundo orden, esto es post");
  }

  @Override
  public void destroy() {
    // no se necesita limpieza
  }
}
