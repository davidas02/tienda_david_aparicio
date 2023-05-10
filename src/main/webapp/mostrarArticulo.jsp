<%@page import="java.sql.Blob"%>
<%@page import="service.ServicioArticulo"%>
<%@page import="java.util.Base64"%>
<%@page import="modelo.Articulo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MostrarArticulo</title>
<!-- Enlaces a las librerías de Bootstrap -->
<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
<script
    src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
    src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="header.jsp" %>
    <!-- Zona central -->
<% Articulo articulo=(Articulo)request.getAttribute("articulo"); 
if(articulo!=null){
%>
	<div class="card">
		<%Blob imagen=articulo.getImagen();
                    if(imagen!=null){
                    byte[] imagenBytes=ServicioArticulo.convertirImagen(imagen);
                    String base64Image=Base64.getEncoder().encodeToString(imagenBytes);
                    String imageData="data:image/png;base64,"+base64Image;
                    %>
                    <img class="img-thumbnail" src="<%=imageData%>"
                        alt="Card image cap" height="250px" width="250px">
                        <%} %>
			<div class="card-body">
				<h5 class="card-title"><%=articulo.getNombre()%></h5>
				<p class="card-text"><%=articulo.getDescripcion()%></p>
				<p class="card-text">
					Precio: <%=articulo.getPrecio()%>€</p>
				<form action="agregar" method="post">
					<input type="hidden" name="id" value="<%=articulo.getId()%>">
					<div class="input-group mb-3">
<!-- 						<div class="input-group-prepend"> -->
<!-- 							<button class="btn btn-outline-secondary" type="button" -->
<!-- 								id="menos">-</button> -->
<!-- 						</div> -->
						<input type="number" class="form-control" placeholder="Cantidad"
							name="cantidad" value="1" min="1" max="<%=articulo.getStock()%>">
<!-- 						<div class="input-group-append"> -->
<!-- 							<button class="btn btn-outline-secondary" type="button" id="mas">+</button> -->
<!-- 						</div> -->
					</div>
					<button type="submit" class="btn btn-primary mt-2">Agregar
						al carrito</button>
						<a href="./" class="btn btn-primary mt-2">Volver</a>
				</form>
			</div>
	</div>
	
	<%} %>
	<%@ include file="footer.jsp" %>
</body>
</html>