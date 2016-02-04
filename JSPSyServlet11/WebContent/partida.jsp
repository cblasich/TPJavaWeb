<%@page import="com.javarevolutions.jsps.servlets.vo.VOPartida"%>
<%@page import="com.javarevolutions.jsps.servlets.vo.VOLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Partida de ajedrez</title>
	<script type="text/javascript" language="JavaScript">
		
		function carga() {
			document.getElementById("origen").focus();
		}	
	
		function valideIngresoPosiciones() {
			var origen = document.getElementById("origen");
			var destino = document.getElementById("destino");
			if((origen.value == "" || origen.value == '' || origen.value == null) &&
					(destino.value == "" || destino.value == '' || destino.value == null)) {
				alert("Ingrese las posiciones de origen y destino, por favor.");
				origen.focus();
				return false;
			} else if(origen.value == "" || origen.value == '' || origen.value == null) {
				alert("Ingrese posición de origen.");
				origen.focus();
				return false;
			} else if(destino.value == "" || destino.value == '' || destino.value == null) {
				alert("Ingrese destino de la pieza.");
				destino.focus();
				return false;
			} else  {
				return true;
			}
		}
	
		function guardarPartida() {
			
			var forma= document.getElementById("formaPartida");
			forma.action = "FuncionesServlet.servlet";
			var accion = document.getElementById("accion");
			accion.value="guardarPartida";
			forma.submit();
		}
		
		function mover() {
			
			valideIngresoPosiciones();
			
			var forma= document.getElementById("formaPartida");
			forma.action= "FuncionesServlet.servlet";
			var accion = document.getElementById("accion");
			accion.value="mover";
			forma.submit();
		}
	
	</script>
</head>
<body onload="carga();" background="http://fotos.subefotos.com/5bdb03d63ad2428c6434078e90f5abado.jpg">
	<form action="" method="POST" onsubmit="" id="formaPartida" name="formaPartida">
	<input type="hidden" name="accion" id="accion">
		<a href="login.html"> Ir a la página inicial </a>
		<p align="center" style="font-weight: bold; font-size: 20px;">
		Bienvenidos a la partida de ajedrez número: <%= session.getAttribute("nroPartida")%> 
		</p>
		<table cellpadding="5px" border="1px" align="center">
			<tr align="center"> <!--FILA 1)a)-->
				<td colspan="2" style="font-weight: bold; size: 20px;">
					Jugador de blancas:
				</td>
				<td colspan="2" style="font-weight: bold; size: 20px;">
					Jugador de negras:
				</td>
				<td colspan="4" style="font-weight: bold; size: 20px;">
					Posiciones del tablero:
				</td>
			</tr>
			<tr align="center"> <!--FILA 1)b)-->
				<td colspan="2" style="font-weight: bold; size: 20px;">
					<%= ((VOLogin)session.getAttribute("dniBlancasLogueado")).getDNI()%> 
					<input type="hidden" value="<%= ((VOLogin)session.getAttribute("dniBlancasLogueado")).getDNI()%>" name="dniBlancas" id="dniBlancasHidden"/>
				</td>
				<td colspan="2" style="font-weight: bold; size: 20px;">
					<%= ((VOLogin)session.getAttribute("dniNegrasLogueado")).getDNI()%>
					<input type="hidden" value="<%= ((VOLogin)session.getAttribute("dniNegrasLogueado")).getDNI()%>" name="dniNegras" id="dniNegrasHidden"/>
				</td>
				<td rowspan="9" style="border: 0px" align="center" colspan="4">
				<img src="https://github.com/utnfrrojava/java/blob/master/tp2015/board.png?raw=true"/>
				</td>
			</tr>
			<tr align="center"> <!--FILA 2-->
				<th style="font-weight: bold; size: 12px;" align="center">Pieza </th>
				<th style="font-weight: bold; size: 12px;" align="center">Posición </th>
				<th style="font-weight: bold; size: 12px;" align="center">Pieza </th>
				<th style="font-weight: bold; size: 12px;" align="center">Posición </th>
			</tr>
			<tr> <!--FILA 3-->
				<td align="left">Peón B1</td>
				<td align="center"><%= (session.getAttribute("posPeonB1").toString())%></td>
				<td align="left">Peón N1</td>
				<td align="center"><%= (session.getAttribute("posPeonN1").toString())%></td>
			</tr>
			<tr> <!--FILA 4-->
				<td align="left">Peón B2</td>
				<td align="center"><%= (session.getAttribute("posPeonB2").toString())%></td>
				<td align="left">Peón N2</td>
				<td align="center"><%= (session.getAttribute("posPeonN2").toString())%></td>
			</tr>	
			<tr> <!--FILA 5-->
				<td align="left">Peón B3</td>
				<td align="center"><%= (session.getAttribute("posPeonB3").toString())%></td>
				<td align="left">Peón N3</td>
				<td align="center"><%= (session.getAttribute("posPeonN3").toString())%></td>
			</tr>	
			<tr> <!--FILA 6-->
				<td align="left">Peón B4</td>
				<td align="center"><%= (session.getAttribute("posPeonB4").toString())%></td>
				<td align="left">Peón N4</td>
				<td align="center"><%= (session.getAttribute("posPeonN4").toString())%></td>
			</tr>
			<tr> <!--FILA 7-->
				<td align="left">Peón B5</td>
				<td align="center"><%= (session.getAttribute("posPeonB5").toString())%></td>
				<td align="left">Peón N5</td>
				<td align="center"><%= (session.getAttribute("posPeonN5").toString())%></td>
			</tr>
			<tr> <!--FILA 8-->
				<td align="left">Peón B6</td>
				<td align="center"><%= (session.getAttribute("posPeonB6").toString())%></td>
				<td align="left">Peón N6</td>
				<td align="center"><%= (session.getAttribute("posPeonN6").toString())%></td>
			</tr>	
			<tr> <!--FILA 9-->
				<td align="left">Peón B7</td>
				<td align="center"><%= (session.getAttribute("posPeonB7").toString())%></td>
				<td align="left">Peón N7</td>
				<td align="center"><%= (session.getAttribute("posPeonN7").toString())%></td>
			</tr>
			<tr> <!--FILA 10-->
				<td align="left">Peón B8</td>
				<td align="center"><%= (session.getAttribute("posPeonB8").toString())%></td>
				<td align="left">Peón N8</td>
				<td align="center"><%= (session.getAttribute("posPeonN8").toString())%></td>
				<td style="border: 0px"></td>
				<td style="font-weight: bold; size: 20px; border: 0px;" align="right">Turno:</td>
				<td align="left" width="50" style="border: 0px; color: red;"><%= (session.getAttribute("turno").toString())%> </td>
				<td style="border: 0px"></td>
			</tr>
			<tr> <!--FILA 11-->
				<td align="left">Torre B1</td>
				<td align="center"><%= (session.getAttribute("posTorreB1").toString())%></td>
				<td align="left">Torre N1</td>
				<td align="center"><%= (session.getAttribute("posTorreN1").toString())%></td>
				<td colspan="4" style="size: 20px; border: 0px;" align="center">De acuerdo al teclado de arriba, por</td>
			</tr>
			<tr> <!--FILA 12-->
				<td align="left">Torre B2</td>
				<td align="center"><%= (session.getAttribute("posTorreB2").toString())%></td>
				<td align="left">Torre N1</td>
				<td align="center"><%= (session.getAttribute("posTorreN2").toString())%></td>
				<td colspan="4" style="size: 20px; border: 0px;" align="center">favor ingrese origen y destino </td>
			</tr>
			<tr> <!--FILA 13-->
				<td align="left">Caballo B1</td>
				<td align="center"><%= (session.getAttribute("posCaballoB1").toString())%></td>
				<td align="left">Caballo N1</td>
				<td align="center"><%= (session.getAttribute("posCaballoN1").toString())%></td>
				<td colspan="4" style="size: 20px; border: 0px;" align="center">de la pieza a mover.</td>
			</tr>
			<tr> <!--FILA 14-->
				<td align="left">Caballo B2</td>
				<td align="center"><%= (session.getAttribute("posCaballoB2").toString())%></td>
				<td align="left">Caballo N2</td>
				<td align="center"><%= (session.getAttribute("posCaballoN2").toString())%></td>
				<td style="border: 0px" width="50"></td>
				<td style="font-weight: bold; size: 20px; border: 0px;" align="right">Origen:</td>
				<td style="border: 0px" align="left"><input type="text" style="width:70px;height:15px" name="origen" id="origen"></td>
				<td style="border: 0px" width="50"></td>
			</tr>
			<tr> <!--FILA 15-->
				<td align="left">Alfil B1</td>
				<td align="center"><%= (session.getAttribute("posAlfilB1").toString())%></td>
				<td align="left">Alfil N1</td>
				<td align="center"><%= (session.getAttribute("posAlfilN1").toString())%></td>
				<td style="border: 0px"></td>
				<td style="font-weight: bold; size: 20px; border: 0px;" align="right">Destino:</td>
				<td style="border: 0px" align="left"><input type="text" style="width:70px;height:15px" name="destino" id="destino" width="2px"></td>
				<td style="border: 0px"></td>
			</tr>
			<tr> <!--FILA 16-->
				<td align="left">Alfil B2</td>
				<td align="center"><%= (session.getAttribute("posAlfilB2").toString())%></td>
				<td align="left">Alfil N2</td>
				<td align="center"><%= (session.getAttribute("posAlfilN2").toString())%></td>
				<td colspan="4" style="border: 0px"  align="center"><input type="button" value="Mover" style="font-size: 14px;" id="Mover" onclick="mover()"></td>
			</tr>
			<tr> <!--FILA 17-->
				<td align="left">Dama B</td>
				<td align="center"><%= (session.getAttribute("posDamaB").toString())%></td>
				<td align="left">Dama N</td>
				<td align="center"><%= (session.getAttribute("posDamaN").toString())%></td>
			</tr>
			<tr> <!--FILA 18-->
				<td align="left">Rey B</td>
				<td align="center"><%= (session.getAttribute("posReyB").toString())%></td>
				<td align="left">Rey N</td>
				<td align="center"><%= (session.getAttribute("posReyN").toString())%></td>
				<td colspan="4" style="border: 0px" align="center"><input type="button" value="Guardar partida" style="font-size: 14px;" id="GuardarPartida" onclick="guardarPartida()"></td>
			</tr>	
		</table>
		</form>
</body>
</html>