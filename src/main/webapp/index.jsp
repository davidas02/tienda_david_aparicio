<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.sql.Blob"%>
<%@page import="service.ServicioArticulo"%>
<%@page import="java.util.Base64"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Tienda David Aparicio</title>
<!-- Enlaces a las librerías de Bootstrap -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<form action="./" method="post" class="form-horizontal">
		<fieldset>

			<!-- Form Name -->
			<legend>FiltrarCategorias</legend>

			<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="selectbasic">Escoja
					Categoria</label>
				<div class="col-md-4">
					<select id="escogerCategoria" name="escogerCategoria"
						class="form-control">

						<option value="0">Todas</option>
						<%
						if (categorias != null) {
							for (Categoria categoria : categorias) {
						%>
						<option value="<%=categoria.getId()%>">
							<%=categoria.getNombreCategoria()%></option>
						<%
						}
						}
						%>
					</select> <label>Precio</label> <input type="number" class="form-control"
						placeholder="Cantidad" name="min" value="1"> <input
						type="number" class="form-control" placeholder="Cantidad"
						name="max" value="">
				</div>
			</div>
			<button type="submit" class="btn btn-primary mt-2">Filtrar</button>

		</fieldset>
	</form>
	<!-- Zona central -->
	<div class="container my-5">
		<div class="row">
			<%
			List<Articulo> catalogo = (List<Articulo>) request.getAttribute("catalogo");
			%>
			<%
			if (catalogo != null) {
				for (Articulo articulo : catalogo) {
			%>
			<div class="col-lg-3 col-md-4 col-sm-6 mb-4">
				<%
				if (articulo.getStock() > 0&& !articulo.isBaja()) {
				%>
				<div class="card">
					<%
					if (articulo.getImagen() != null) {
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
						<form action="agregar" method="post">
							<input type="hidden" name="id" value="<%=articulo.getId()%>">
							<div class="input-group mb-3">
								<!-- 									<div class="input-group-prepend"> -->
								<!-- 										<button class="btn btn-outline-secondary" type="button" -->
								<!-- 											id="menos">-</button> -->
								<!-- 									</div> -->
								<input type="number" class="form-control" placeholder="Cantidad"
									name="cantidad" value="1" min="1"
									max="<%=articulo.getStock()%>">
								<!-- 									<div class="input-group-append"> -->
								<!-- 										<button class="btn btn-outline-secondary" type="button" -->
								<!-- 											id="mas">+</button> -->
								<!-- 									</div> -->
							</div>
							<button type="submit" class="btn btn-primary mt-2">Agregar
								al carrito</button>
						</form>
						<a href="muestraArticulo?id=<%=articulo.getId()%>">Mostrar
							Articulo</a>
					</div>
				</div>
			</div>
			<%}
			} 
		}%>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>