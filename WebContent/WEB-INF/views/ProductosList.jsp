<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
    <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de productos</title>
    </head>
    <body>
        <div align="center">
	        <h1>Listado de productos</h1>
	        <a href="new"><p class="btn btn-primary">Nuevo producto</p></a>
	        
        	<table border="1" class="table table-striped">
	        	<th>Referencia</th>
	        	<th>Nombre</th>
	        	<th>Unidades</th>
	        	<th>Precio</th>
	        	<th>Fecha</th>
	        	<th>Acciones</th>
	        	
	        	
				<c:forEach var="producto" items="${productosList}" varStatus="status">
	        	<tr>
	        		<td>${producto.id}</td>
					<td>${producto.nombre}</td>
					<td>${producto.unidades}</td>
					<td>${producto.precio}</td>
					<td>${producto.fecha}</td>
					<td>
						<a href="edit?id=${producto.id}"><p class="btn btn-warning">Editar</p></a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="delete?id=${producto.id}"><p class="btn btn-danger">Eliminar</p></a>
					</td>
	        	</tr>
				</c:forEach>	        	
        	</table>
        	<p><a href="inicio" class="btn btn-default">Atras</a>
        	<a href="compra" class="btn btn-default">Comprar</a></p>
        </div>
    </body>
</html>
