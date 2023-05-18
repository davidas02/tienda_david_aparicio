<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contacto</title>
<!-- Enlaces a las librerías de Bootstrap -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<%@include file="header.jsp"%>
	<form method="post" action="contacto" class="form-horizontal" >
		<fieldset>

			<!-- Form Name -->
			<legend>Form Name</legend>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="email">Email</label>
				<div class="col-md-4">
					<input id="email" name="email" type="text"
						placeholder="email@email.com" class="form-control input-md"
						required="" <%if(request.getCookies()!=null){
									for(Cookie cookie:request.getCookies()){
										if(cookie.getName().equals("UsuarioTienda")){
									%>
										value="<%= cookie.getValue() %>"
									<%
										}
									}
								}%>
								> <span class="help-block">Introduzca su
						email</span>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="tituloConsulta">Consulta</label>
				<div class="col-md-4">
					<input id="tituloConsulta" name="tituloConsulta" type="text"
						placeholder="" class="form-control input-md">

				</div>
			</div>

			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="descripcion">Descripcion</label>
				<div class="col-md-4">
					<textarea class="form-control" id="descripcion" name="descripcion"
						placeholder="Introduzca su consulta, máximo 255 caracteres"></textarea>
				</div>
			</div>

			<!-- Button (Double) -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="enviar"></label>
				<div class="col-md-8">
					<button id="enviar" name="enviar" class="btn btn-success">Enviar</button>
					<a href="./" class="btn btn-danger">Cancelar</a>
				</div>
			</div>

		</fieldset>
	</form>
	<%@include file="footer.jsp"%>
</body>
</html>