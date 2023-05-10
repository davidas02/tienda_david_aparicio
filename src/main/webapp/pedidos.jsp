<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="modelo.Pedido"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Pedidos Tienda David Aparicio</title>
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
<%
ArrayList<Pedido> pedidos=(ArrayList<Pedido>)request.getAttribute("pedidos");
if(pedidos!=null){
	%>
	<form action="pedidos">
	   <label for="fecha1">fecha1</label>
	   <input type="date" name="fecha1" id="fecha1">
	   <label for="fecha2">fecha2</label>
       <input type="date" name="fecha2" id="fecha2">
       <button type="submit">Filtrar</button>
	</form>
	<%
	for(Pedido pedido:pedidos){
	%>
	<div class="col-lg-3 col-md-4 col-sm-6 mb-4">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Pedido nº: <%=pedido.getIdPedido()%></h5>
						<p class="card-text">Fecha del pedido: <%=pedido.getFecha()%></p>
						<%if(pedido.getNumFactura()>0){%>
						<p class="card-text">nº de Factura: <%=pedido.getNumFactura() %></p>
						<%} %>
						<p class="card-text">Metodo de pago: <%=pedido.getMetodoPago()%></p>
						<p class="card-text">Estado del pedido: <%=pedido.getEstado().getNombre()%></p>
						<%if (pedido.getEstado().getAbreviatura().equals("PE")){
							%>
							<a href="CancelarPedido?id=<%=pedido.getIdPedido() %>" id="cancelar" class="btn btn-danger">Cancelar Pedido</a>
							<%
						} %>
						<p class="card-text">
							Total: <%=pedido.getTotal()%>€</p>
						<a href="detalle?idPedido=<%= pedido.getIdPedido()%>">Productos del pedido</a>
					</div>
				</div>

			</div>
	<%}
}%>
<%@ include file="footer.jsp"%>