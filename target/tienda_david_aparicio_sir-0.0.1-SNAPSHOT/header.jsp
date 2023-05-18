<%@page import="modelo.Configuracion"%>
<%@page import="modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Categoria"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="modelo.Articulo"%>

    <%List<Categoria> categorias=(List<Categoria>) request.getAttribute("categorias");
    	Usuario usuario=(Usuario)session.getAttribute("usuario");
    	Configuracion configuracion=(Configuracion)session.getAttribute("configuracion");
    %>
	<!-- Header -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="./"><%="Tienda " + configuracion.getNombre()%></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<%
				// Comprobamos si la variable "usuario" existe en la sesión
				if (usuario == null) {
				%>
				<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="alta.jsp">Alta
						Usuarios</a></li>
				<%
				} else {
				%>
				<li class="nav-item"><a class="nav-link" href="logout">Cerrar
						Session</a></li>
				<li class="nav-item"><a class="nav-link" href="perfil.jsp">Perfil</a>
				</li>
				<%
					if(usuario.getRol().getId()==1){
						%>
						<li class="nav-item"><a href="pedidos" class="nav-link">Control de Pedidos</a></li>
						<li class="nav-item"><a class="nav-link">Articulos</a></li>
						<%
					}else if(usuario.getRol().getId()==2){
						%>
						<li class="nav-item"><a href="pedidos" class="nav-link">Historial de Pedidos</a></li>
					<%}
				}
				%>
				<li class="nav-item"><a class="nav-link" href="contacto.jsp">Contacto</a>
				<li class="nav-item"><a class="nav-link" href="carrito.jsp">Carrito</a>
				</li>
			</ul>
		</div>
	</nav>