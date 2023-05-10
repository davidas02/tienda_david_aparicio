<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>alta</title>
<!-- Enlaces a las librerías de Bootstrap -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
	<%
	if (request.getAttribute("error") != null) {
	%>
	<p style="color: red"><%=request.getAttribute("error").toString()%></p>
	<%
	}
	%>
	<form class="form-horizontal" action="alta" method="post">
		<fieldset>

			<!-- Form Name -->
			<legend>Alta Usuarios</legend>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="email">Email</label>
				<div class="col-md-4">
					<input id="email" name="email" type="text"
						placeholder="email@dominio.com" class="form-control input-md">
					<span class="help-block"> </span>
				</div>
			</div>

			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="password1">Introduce
					Password</label>
				<div class="col-md-4">
					<input id="password1" name="password1" type="password"
						placeholder="" class="form-control input-md"> <span
						class="help-block"> </span>
				</div>
			</div>

			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="password2">Repite
					Password</label>
				<div class="col-md-4">
					<input id="password2" name="password2" type="password"
						placeholder="" class="form-control input-md"> <span
						class="help-block"> </span>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="nombre">Nombre</label>
				<div class="col-md-4">
					<input id="nombre" name="nombre" type="text" placeholder="Nombre"
						class="form-control input-md"> <span class="help-block">
					</span>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="apellidos">Apellidos</label>
				<div class="col-md-4">
					<input id="apellidos" name="apellidos" type="text"
						placeholder="Apellidos" class="form-control input-md" required="">
					<span class="help-block"> </span>
				</div>
			</div>

			<!-- Button (Double) -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="button1id"></label>
				<div class="col-md-8">
					<button id="button1id" name="button1id" class="btn btn-success">Aceptar</button>
					<a href="./" id="button2id" class="btn btn-danger">Cancelar</a>
				</div>
			</div>
		</fieldset>
	</form>
<%@ include file="footer.jsp" %>