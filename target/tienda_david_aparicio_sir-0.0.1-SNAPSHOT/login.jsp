
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulario de Login</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<style>
body {
	background-color: #f2f2f2;
}

.card {
	margin-top: 100px;
	margin-bottom: 100px;
	padding: 30px;
	border: none;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.card-header {
	font-size: 24px;
	font-weight: bold;
	text-align: center;
	margin-bottom: 30px;
}

.form-group label {
	font-size: 18px;
	font-weight: bold;
	margin-bottom: 10px;
}

.form-control {
	height: 50px;
	font-size: 18px;
	border-radius: 5px;
	box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
}

.btn {
	height: 50px;
	font-size: 18px;
	font-weight: bold;
	border-radius: 5px;
	background-color: #3d3d3d;
	border-color: #3d3d3d;
	box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
}

.btn:hover {
	background-color: #2c2c2c;
	border-color: #2c2c2c;
}

.header {
	background-color: #3d3d3d;
	padding: 20px 0;
}

.logo {
	display: inline-block;
	width: 100px;
	height: 100px;
	margin-right: 20px;
}

.heading {
	display: inline-block;
	color: white;
	font-size: 24px;
	font-weight: bold;
}

.footer {
	background-color: #3d3d3d;
	color: white;
	padding: 20px 0;
}

.footer p {
	margin: 0;
	font-size: 16px;
	font-weight: bold;
}
</style>
</head>
<body>
	<header class="header">
		<div class="text-center">
			<div class="container">
				<h1 class="heading">Login Tienda David Aparicio</h1>
				<a href="pruebaFiltro"></a>
			</div>
		</div>
	</header>
	<div class="container">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">

				<div class="card">
					<div class="card-header">Inicio de sesión</div>
					<form method="post" action="login">
						<div class="form-group">
							<label for="usuario">Usuario</label> <input type="text"
								class="form-control" id="usuario"
								placeholder="Introduce tu usuario" name="usuario"
								<%if(request.getCookies()!=null){
									for(Cookie cookie:request.getCookies()){
										if(cookie.getName().equals("UsuarioTienda")){
									%>
										value="<%= cookie.getValue() %>"
									<%
										}
									}
								}%>
								>
								<% 
								if(request.getAttribute("errorusuario")!=null){%>
								<p>
								<%= request.getAttribute("errorusuario")%>
								</p>
								<%} %>
						</div>
						<div class="form-group">
							<label for="password">Contraseña</label> <input type="password"
								class="form-control" id="password"
								placeholder="Introduce tu contraseña" name="password">
								<% 
								if(request.getAttribute("errorpassword")!=null){%>
								<p>
								<%= request.getAttribute("errorpassword")%>
								</p>
								<%} %>
						</div>
						<button type="submit" class="btn btn-primary btn-block">Iniciar
							sesión</button>
					</form>
				</div>
			</div>
			<div class="col-sm-4">
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
<footer class="footer">
	<div class="container">
		<p class="text-center">&copy; Tienda David Aparicio</p>
	</div>
</footer>

</html>