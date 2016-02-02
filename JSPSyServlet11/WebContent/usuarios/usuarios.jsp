<%@page import="java.util.List"%>
<%@page import="com.javarevolutions.jsps.servlets.vo.VOLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>TITULO A ELEGIR</title>
	
	<script>
	function guardar() {
		var forma = document.getElementById("formaUsuarios");
		forma.action = "adminUsuarios.servlet";
		var accion = document.getElementById("accion");
		accion.value = "guardar";
		forma.submit();
	}
	
	function actualizar() {
		var forma = document.getElementById("formaUsuarios");
		forma.action = "adminUsuarios.servlet";
		var accion = document.getElementById("accion");
		accion.value = "actualizar";
		forma.submit();
	}
	
	function borrar() {
		var forma = document.getElementById("formaUsuarios");
		forma.action = "adminUsuarios.servlet";
		var accion = document.getElementById("accion");
		accion.value = "borrar";
		forma.submit();
	}
	
	function carga() {
		document.getElementById("usuario").focus();
	}
	</script>
	
	</head>
	<body onload="carga();">
	<form action="" method="POST" id="formaUsuarios">
	<center>
	
	
	<input type="hidden" name="accion" id="accion">
	
	<p style="font-weight: bold; font-size: 20px;">
	Esto es para crear, modificar y borrar jugadores. La idea es que el linkLabel
	"Administración de Usuarios" esté en la primer página, abajo y hacia un costado, del login.
	</p>
	
	<table>
	<tr>
	<td style="font-weight: bold; size: 12px;" align="right">DNI : </td>
	<td><input type="text" name="DNI" id="DNI"></td>
	</tr>
	<tr>
	<td style="font-weight: bold; size: 12px;" align="right">Nombre(s) : </td>
	<td><input type="text" name="nombre" id="nombre"></td>
	</tr>
	<tr>
	<td style="font-weight: bold; size: 12px;" align="right">Apellido(s) : </td>
	<td><input type="text" name="apellido" id="apellido"></td>
	</tr>
	<tr>
	<td colspan="2">
	<input type="button" value="Guardar" style="font-size: 14px;"
	onclick="guardar();">
	<input type="button" value="Actualizar" style="font-size: 14px;"
	onclick="actualizar();">
	<input type="button" value="Eliminar" style="font-size: 14px;"
	onclick="borrar();">
	</td>
	
	</tr>
	</table>
	
	<table>
	<tr>
	<th>DNI</th><th>Nombre(s)</th><th>Apellido</th>
	</tr>
	<%
	List<VOLogin> lista = (List<VOLogin>)session.getAttribute("listaUsuario");
	for(VOLogin obj: lista) {
	%>
		<tr>
		<td><%= obj.getDNI() %></td>
		<td><%= obj.getNombre() %></td>
		<td><%= obj.getApellido() %></td>
		</tr>
	<%	
	}
	%>
	</table>
	</form>
	
	</center>
	</body>
</html>