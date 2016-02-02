<%@page import="com.javarevolutions.jsps.servlets.vo.VOPartida"%>
<%@page import="com.javarevolutions.jsps.servlets.vo.VOLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Partida de Ajedrez</title>
		<script type="text/javascript" language="JavaScript">
			
			function nuevaPartida() {
				var forma = document.getElementById("formaWelcome");
				forma.action = "FuncionesServlet.servlet";
				var accion = document.getElementById("accion");
				accion.value = "nueva";
				forma.submit();
			}
			
			function nuevaPartidaIdAnterior() {
				var forma = document.getElementById("formaWelcome");
				forma.action = "FuncionesServlet.servlet";
				var accion = document.getElementById("accion");
				accion.value = "nuevaConIdAnterior";
				forma.submit();
			}
			
			function reanudarPartida() {
				var forma = document.getElementById("formaWelcome");
				forma.action = "FuncionesServlet.servlet";
				var accion = document.getElementById("accion");
				accion.value = "reanudar";
				forma.submit();
			}

		</script>
	</head>
	<body onload="" background="http://fotos.subefotos.com/5bdb03d63ad2428c6434078e90f5abado.jpg">
		<form action="" method="POST" onsubmit="" id="formaWelcome" name="formaWelcome">
		<input type="hidden" name="accion" id="accion">
			<%
			if(session.getAttribute("nroPartida")!=null){%>
						<script type="text/javascript" language="JavaScript">
						if(confirm("Desea reanudar la partida anterior? \nSi presiona Cancelar, la respuesta es No.")){
							reanudarPartida();
							}
						else{ 
							nuevaPartidaIdAnterior();
						}
						</script>
			<%
			}else{%>
				<script type="text/javascript" language="JavaScript">
				nuevaPartida();
				</script>
					<%
				}%>
	
		</form>
	</body>
</html>