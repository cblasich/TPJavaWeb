<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Juego ganado</title>
</head>
<body onload="" background="http://fotos.subefotos.com/5bdb03d63ad2428c6434078e90f5abado.jpg">
<form action="" method="POST">
	<table align="center">
		<tr>
		<td colspan="2" style="font-weight: bold; size: 20px; color: red; text-align: center;" ><p>Ganó la partida el jugador de DNI <%= session.getAttribute("ganador")%></p></td>
		</tr>
		<tr>
		<td colspan="2" style="font-weight: bold; size: 20px; text-align: center;"><a href="login.html"> Volver a jugar </a>
		</tr>
	</table>
</form>

</body>
</html>