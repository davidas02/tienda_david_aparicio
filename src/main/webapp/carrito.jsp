<%@page import="org.apache.commons.lang3.SerializationUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="modelo.Articulo"%>
<%@ page import="java.util.Base64"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>Ver carrito</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style type="text/css">
.relleno {
	height: 300px;
	width: 100%;
	background-color: #f0f0f0;
	/* Puedes cambiar el color de fondo a tu gusto */
}
</style>

</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container">
			<a class="navbar-brand" href="#">Mi tienda</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link"
						href="./">Inicio</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container my-5">
		<h1>Ver carrito</h1>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Nombre</th>
					<th scope="col">Descripción</th>
					<th scope="col">Cantidad</th>
					<th scope="col">Precio</th>
					<th scope="col">Total</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<%
				HashMap<Integer, Articulo> carrito = (HashMap<Integer, Articulo>) session.getAttribute("carrito");
				double total = 0;
				if (carrito == null || carrito.size() == 0) {
					Cookie[] cookies = request.getCookies();

					if (cookies != null) {

						// Buscar cookie por nombre
						for (Cookie cookie : cookies) {
					if (cookie.getName().equals("carrito")) {
					    
						// Decodificar valor de la cookie y convertir a HashMap
						byte[] carritoBytes = Base64.getDecoder().decode(cookie.getValue().getBytes("UTF-8"));
						HashMap<Integer, Articulo> carrito2 = (HashMap<Integer, Articulo>) SerializationUtils.deserialize(carritoBytes);
						if(carrito!=null){
							// Hacer algo con el HashMap recuperado
							for (Map.Entry<Integer, Articulo> entry : carrito.entrySet()) {
								System.out.println("ID: " + entry.getKey() + ", Artículo: " + entry.getValue().getNombre());
							}
							carrito=carrito2;
							session.setAttribute("carrito", carrito);
						}
					}
						}
					}
					
				}
				if (carrito != null&& carrito.size()>0) {
					for (Map.Entry<Integer, Articulo> entry : carrito.entrySet()) {
						Articulo articulo = entry.getValue();
						int cantidad = articulo.getCantidad();
						double precio = articulo.getPrecio();
						double subtotal = cantidad * precio;
						total += subtotal;
				%>
				<tr>
					<th scope="row"><%=articulo.getId()%></th>
					<td><%=articulo.getNombre()%></td>
					<td><%=articulo.getDescripcion()%></td>
					<td>
					<div class="input-group mb-3">
	                    <div class="input-group-prepend">
	                        <a href="agregar?id=<%=articulo.getId()%>&cantidad=-1" >-</a>
	                    </div>
						<%=cantidad%> 
						<div class="input-group-append">
	                      <a href="agregar?id=<%=articulo.getId()%>&cantidad=1">+</a>
                        </div> 
                    </div>
                    </td>
					<td><%=precio%> €</td>
					<td><%=subtotal%> €</td>
					<td><a href="eliminarCarrito?id=<%=articulo.getId()%>"
						class="text-danger"> <i class="fas fa-trash-alt"></i>
					</a></td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="7" class="text-center">No hay elementos en el
						carrito</td>
				</tr>
				<%
				}
				%>
			</tbody>
			<tfoot>
				<tr>
					<th scope="row"></th>
					<td colspan="4" class="text-right font-weight-bold">Total:</td>
					<td><%=total%> €</td>
					<td></td>
				</tr>
			</tfoot>
		</table>
		<div class="text-right">
			<a href="./" class="btn btn-primary">Volver</a>
			<a href="vaciar" class="btn btn-warning">Vaciar Carrito</a>
			<a href="finalizar" class="btn btn-success">Finalizar compra</a>
		</div>
	</div>

	<div class="relleno"></div>
	<script src="https://kit.fontawesome.com/cd55d2c02b.js"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi4h1V"
		crossorigin="anonymous">
		
	</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNVQ6jr"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
<%@ include file="footer.jsp" %>