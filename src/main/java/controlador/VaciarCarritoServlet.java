package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
/**
 * Servlet implementation class VaciarCarritoServlet
 */
import javax.servlet.http.*;

@WebServlet("/vaciar")
public class VaciarCarritoServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.removeAttribute("carrito");
        }
        Cookie[] cookies=request.getCookies();
	    if(cookies!=null) {
	    	for(Cookie cookie:cookies) {
	    		if(cookie.getName().equals("carrito")) {
	    			cookie.setMaxAge(0);
	    			response.addCookie(cookie);
	    		}
	    	}
	    }
        request.getRequestDispatcher("").forward(request, response);
    }
}
