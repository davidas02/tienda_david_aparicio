<%@page import="modelo.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="modelo.Articulo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Lista de artículos</title>
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


	<%
	Usuario user = (Usuario) session.getAttribute("usuario");
	%>
	<%
	if (user != null) {
	%>
	<!-- Zona central -->
	<div class="container my-5">
		<div class="row">
			<div class="col-lg-3 col-md-4 col-sm-6 mb-4">

				<div class="card">

					<div class="card-body">
						<p class="card-text"><%=user.getNombre() + " " + user.getApellidos()%></p>
						<p class="card-text"><%=user.getEmail()%></p>
					</div>
				</div>
			</div>
		</div>
		<form class="form-horizontal" action="cambiarPassword" method="post">
		<%if(request.getAttribute("error")!=null){
			%>
			<p style="color: red'"><%=request.getAttribute("error") %></p>
			<%
		} %>
			<fieldset>

				<!-- Form Name -->
				<legend>Cambiar Password</legend>

				<!-- Text input-->
				<div class="form-group">
					<div class="col-md-4">
						<input id="email" name="email" type="text"
							placeholder="mail@mail.com" class="form-control input-md"
							required="true" hidden="true" value="<%=user.getEmail()%>">
					</div>
				</div>
				<!-- Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="passwordAntiguo">Introduce
						la contraseña actual</label>
					<div class="col-md-4">
						<input id="passwordAntiguo" name="passwordAntiguo" type="password"
							placeholder="Introduce la contraseña actual"
							class="form-control input-md" required="">

					</div>
				</div>

				<!-- Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="passwordnuevo1">Introduce
						la contraseña nueva</label>
					<div class="col-md-4">
						<input id="passwordNuevo1" name="passwordNuevo1" type="password"
							placeholder="Introduce la contraseña nueva"
							class="form-control input-md" required="">

					</div>
				</div>

				<!-- Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="passwordNuevo2">Repite
						la contraseña nueva</label>
					<div class="col-md-4">
						<input id="passwordNuevo2" name="passwordNuevo2" type="password"
							placeholder="Repite la contraseña nueva"
							class="form-control input-md">

					</div>
				</div>
				<!-- Button (Double) -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="aceptar"></label>
					<div class="col-md-8">
						<button id="aceptar" type="submit" name="aceptar"
							class="btn btn-success">Aceptar</button>
						<a href="./" id="cancelar" class="btn btn-danger">Cancelar</a>
					</div>
				</div>

			</fieldset>
		</form>
	</div>
		<%
		}
		%>

		
	<%@ include file="footer.jsp"%>
</body>
</html>