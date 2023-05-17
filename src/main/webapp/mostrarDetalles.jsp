<%@page import="java.sql.Blob"%>
<%@page import="service.ServicioArticulo"%>
<%@page import="java.util.Base64"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalles</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%
	ArrayList<Articulo> articulos = (ArrayList<Articulo>) request.getAttribute("detalles");
	if (articulos != null) {
	%>
	<h1>
		Detalles del pedido
		<%=request.getAttribute("numPedido")%></h1>
	<%
		for (Articulo articulo : articulos) {
		%>
		<div class="col-lg-3 col-md-4 col-sm-6 mb-4">
	
			<div class="card">
				<% if (articulo.getImagen() != null) {
							byte[] imagenBytes=ServicioArticulo.convertirImagen(articulo.getImagen());
							if(imagenBytes!=null){
							String base64Image = Base64.getEncoder().encodeToString(imagenBytes);
							String imageData = "data:image/png;base64," + base64Image;
							%>
						<img class="card-img-top" src="<%=imageData%>" alt="Card image cap">
						<%
							}
						}
						%>
				<div class="card-body">
					<h5 class="card-title"><%=articulo.getNombre()%></h5>
					<p class="card-text"><%=articulo.getDescripcion()%></p>
					<p class="card-text">
						Precio:
						<%=articulo.getPrecio()%>€
					</p>
					<p class="card-text">
						Cantidad:
						<%=articulo.getCantidad()%></p>
				</div>
				<p></p>
				<a class="btn btn-warning"
					href="muestraArticulo?id=<%=articulo.getId()%>">Mostrar Articulo</a>
			</div>
		</div>
	
		</div>
		<%
		}
	}
	%>
	</div>
	<a href="pedidos" class="btn btn-primary mt-2">Volver</a>
	<%@ include file="footer.jsp"%>