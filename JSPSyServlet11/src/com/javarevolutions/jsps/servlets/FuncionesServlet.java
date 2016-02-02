package com.javarevolutions.jsps.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.Controlador;

import com.javarevolutions.jsps.servlets.vo.VOLogin;
import com.javarevolutions.jsps.servlets.vo.VOPartida;

import entidades.Color;
import entidades.Partida;

/**
 * Servlet implementation class FuncionesServlet
 */
@WebServlet("/FuncionesServlet.servlet")
public class FuncionesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FuncionesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		Controlador ctrl = LoginServlet.getCtrl();
		String accion = request.getParameter("accion");
		System.out.println("Accion: "+accion);
		String posOrigen = request.getParameter("origen");
		String posDestino = request.getParameter("destino");
		
		FuncionesServlet servletFunciones = new FuncionesServlet();
		int dniBlancas = ((VOLogin)session.getAttribute("dniBlancasLogueado")).getDNI();
		int dniNegras = ((VOLogin)session.getAttribute("dniNegrasLogueado")).getDNI();
		
		if(accion.equals("nueva")){
			System.out.println("accion: nueva partida. LoginServlet.");
			System.out.println("Accion(variable): "+accion);
			
			VOPartida pAJugar = new VOPartida();
			pAJugar.getJugadorB().setDNI(dniBlancas);
			pAJugar.getJugadorN().setDNI(dniNegras);
			pAJugar.asignarPosicIniciales();
			pAJugar.setTurno(Color.BLANCO);
			Partida pAJugarPartida = Funciones.convertVOPartidaAPartida(pAJugar);
			Funciones.guardarPartida(pAJugarPartida);
			System.out.println("El id de la partida convertida es " +pAJugarPartida.getId());
			session.setAttribute("nroPartida", pAJugarPartida.getId());
			session.setAttribute("turno", pAJugar.getTurno().toString());
			ctrl.setpActual(pAJugarPartida);
			
			servletFunciones.guardarPosicEnSession(request, response, pAJugar);
			request.getRequestDispatcher("partida.jsp").forward(request, response);	
		} 
		
		else if(accion.equals("nuevaConIdAnterior")){
			System.out.println("accion: nueva con Id anterior. LoginServlet.");
			System.out.println("Accion(variable): "+accion);
			
			VOPartida pAJugar = new VOPartida();
			
			Partida pEncontrada = ctrl.buscarPartida(dniBlancas, dniNegras);
			if(pEncontrada!=null){
				pAJugar.getJugadorB().setDNI(dniBlancas);
				pAJugar.getJugadorN().setDNI(dniNegras);
				session.setAttribute("nroPartida", pEncontrada.getId());
				pAJugar.asignarPosicIniciales();
				pAJugar.setTurno(Color.BLANCO);
				
				Partida pAJugarPartida = Funciones.convertVOPartidaAPartida(pAJugar);
				Funciones.guardarPartida(pAJugarPartida);  
				session.setAttribute("turno", pAJugar.getTurno().toString());
				
				ctrl.setpActual(pAJugarPartida);	
			}
			
			servletFunciones.guardarPosicEnSession(request, response, pAJugar);
			request.getRequestDispatcher("partida.jsp").forward(request, response);	
		}
		
		else if(accion.equals("reanudar")){
			System.out.println("accion reanudar partida. ");
			System.out.println("Accion(variable): "+accion);
			
			VOPartida pAJugar = new VOPartida();
			
			Partida pEncontrada = ctrl.buscarPartida(dniBlancas, dniNegras);
			if(pEncontrada!=null){
				Partida pEncontradaConPosiciones = ctrl.buscarPartida(pEncontrada);
				ctrl.setpActual(pEncontradaConPosiciones);
				session.setAttribute("nroPartida", pEncontrada.getId());
				pAJugar.setTurno(pEncontrada.getTurno());
				session.setAttribute("turno", pAJugar.getTurno().toString());
				pAJugar.setColPiezas(pEncontradaConPosiciones.getColPiezas());
			}
			
			servletFunciones.guardarPosicEnSession(request, response, pAJugar);
			request.getRequestDispatcher("partida.jsp").forward(request, response);	
			
		}
		
		else if(accion.equals("guardarPartida")){
			System.out.println("Accion(variable): "+accion);
			Funciones.guardarPartida();
			System.out.println("Funciones servlet. guardar partida.");
			
			request.getRequestDispatcher("partida.jsp").forward(request, response);	
		}
		
		else if(accion.equals("mover")){
			System.out.println("Accion(variable): "+accion);
			System.out.println("funciones servlet. mover pieza.");
			
			Color turnoActual = ctrl.getpActual().getTurno();
			Partida p = ctrl.getpActual();
			boolean puedeMover = ctrl.esMovimientoValidoWeb(p, posOrigen, posDestino);			
			
			if(puedeMover){
				if(turnoActual == Color.BLANCO) { 
					p.setTurno(Color.NEGRO);
					session.setAttribute("turno", p.getTurno());
					if(p.getColPiezas().get(29).getPosicion() == null){ //index 29 pertenece al rey negro
						session.setAttribute("ganador", p.getJugadorB().getDni());
						request.getRequestDispatcher("juegoGanado.jsp").forward(request, response);
					//ganan blancas
					}
					
				} else {  //si el turno es negro
					p.setTurno(Color.BLANCO);
					session.setAttribute("turno", p.getTurno());
					if(p.getColPiezas().get(28).getPosicion() == null){ //index 28 pertenece al rey blanco
						session.setAttribute("ganador", p.getJugadorN().getDni());
						request.getRequestDispatcher("juegoGanado.jsp").forward(request, response);
					}
				}
				VOPartida pAJugar = Funciones.convertPartidaToVOPartida(p);
				servletFunciones.guardarPosicEnSession(request, response, pAJugar);
				request.getRequestDispatcher("partida.jsp").forward(request, response);
			} 
			else {
				request.getRequestDispatcher("movimientoIncorrecto.jsp").forward(request, response);
			}
		}
		
	}
	
	public static void juegoGanadoReqResp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	public void guardarPosicEnSession(HttpServletRequest request, HttpServletResponse response, VOPartida pAJugar) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String posicionPieza = null;
		
		posicionPieza = pAJugar.getColPiezas().get(0).getPosicion();
		if(posicionPieza!=null){
			String posPeonB1 = posicionPieza.toString();
			session.setAttribute("posPeonB1", posPeonB1);
		} else session.setAttribute("posPeonB1", "");
		
		posicionPieza = pAJugar.getColPiezas().get(1).getPosicion();
		if(posicionPieza!=null){
			String posPeonB2 = posicionPieza.toString();
			session.setAttribute("posPeonB2", posPeonB2);
		} else session.setAttribute("posPeonB2", "");

		posicionPieza = pAJugar.getColPiezas().get(2).getPosicion();
		if(posicionPieza!=null){
			String posPeonB3 = posicionPieza.toString();
			session.setAttribute("posPeonB3", posPeonB3);
		} else session.setAttribute("posPeonB3", "");
		
		posicionPieza = pAJugar.getColPiezas().get(3).getPosicion();
		if(posicionPieza!=null){
			String posPeonB4 = posicionPieza.toString();
			session.setAttribute("posPeonB4", posPeonB4);
		} else session.setAttribute("posPeonB4", "");
			
		posicionPieza = pAJugar.getColPiezas().get(4).getPosicion();
		if(posicionPieza!=null){
			String posPeonB5 = posicionPieza.toString();
			session.setAttribute("posPeonB5", posPeonB5);
		} else session.setAttribute("posPeonB5", "");
		
		posicionPieza = pAJugar.getColPiezas().get(5).getPosicion();
		if(posicionPieza!=null){
			String posPeonB6 = posicionPieza.toString();
			session.setAttribute("posPeonB6", posPeonB6);
		} else session.setAttribute("posPeonB6", "");
		
		posicionPieza = pAJugar.getColPiezas().get(6).getPosicion();
		if(posicionPieza!=null){
			String posPeonB7 = posicionPieza.toString();
			session.setAttribute("posPeonB7", posPeonB7);
		} else session.setAttribute("posPeonB7", "");
		
		posicionPieza = pAJugar.getColPiezas().get(7).getPosicion();
		if(posicionPieza!=null){
			String posPeonB8 = posicionPieza.toString();
			session.setAttribute("posPeonB8", posPeonB8);
		} else session.setAttribute("posPeonB8", "");
		
		posicionPieza = pAJugar.getColPiezas().get(8).getPosicion();
		if(posicionPieza!=null){
			String posPeonN1 = posicionPieza.toString();
			session.setAttribute("posPeonN1", posPeonN1);
		} else session.setAttribute("posPeonN1", "");
		
		posicionPieza = pAJugar.getColPiezas().get(9).getPosicion();
		if(posicionPieza!=null){
			String posPeonN2 = posicionPieza.toString();
			session.setAttribute("posPeonN2", posPeonN2);
		} else session.setAttribute("posPeonN2", "");
		
		

		posicionPieza = pAJugar.getColPiezas().get(10).getPosicion();
		if(posicionPieza!=null){
			String posPeonN3 = posicionPieza.toString();
			session.setAttribute("posPeonN3", posPeonN3);
		} else session.setAttribute("posPeonN3", "");
		
			posicionPieza = pAJugar.getColPiezas().get(11).getPosicion();
			if(posicionPieza!=null){
				String posPeonN4 = posicionPieza.toString();
				session.setAttribute("posPeonN4", posPeonN4);
			} else session.setAttribute("posPeonN4", "");
			
			posicionPieza = pAJugar.getColPiezas().get(12).getPosicion();
			if(posicionPieza!=null){
				String posPeonN5 = posicionPieza.toString();
				session.setAttribute("posPeonN5", posPeonN5);
			} else session.setAttribute("posPeonN5", "");
			
			posicionPieza = pAJugar.getColPiezas().get(13).getPosicion();
			if(posicionPieza!=null){
				String posPeonN6 = posicionPieza.toString();
				session.setAttribute("posPeonN6", posPeonN6);
			} else session.setAttribute("posPeonN6", "");
			
			posicionPieza = pAJugar.getColPiezas().get(14).getPosicion();
			if(posicionPieza!=null){
				String posPeonN7 = posicionPieza.toString();
				session.setAttribute("posPeonN7", posPeonN7);
			} else session.setAttribute("posPeonN7", "");
			
			posicionPieza = pAJugar.getColPiezas().get(15).getPosicion();
			if(posicionPieza!=null){
				String posPeonN8 = posicionPieza.toString();
				session.setAttribute("posPeonN8", posPeonN8);
			} else session.setAttribute("posPeonN8", "");
			
			
			
			posicionPieza = pAJugar.getColPiezas().get(16).getPosicion();
			if(posicionPieza!=null){
				String posTorreB1 = posicionPieza.toString();
				session.setAttribute("posTorreB1", posTorreB1);
			} else session.setAttribute("posTorreB1", "");
			
			
			posicionPieza = pAJugar.getColPiezas().get(17).getPosicion();
			if(posicionPieza!=null){
				String posTorreB2 = posicionPieza.toString();
				session.setAttribute("posTorreB2", posTorreB2);
			} else session.setAttribute("posTorreB2", "");
			
			posicionPieza = pAJugar.getColPiezas().get(18).getPosicion();
			if(posicionPieza!=null){
				String posTorreN1 = posicionPieza.toString();
				session.setAttribute("posTorreN1", posTorreN1);
			} else session.setAttribute("posTorreN1", "");
			
			posicionPieza = pAJugar.getColPiezas().get(19).getPosicion();
			if(posicionPieza!=null){
				String posTorreN2 = posicionPieza.toString();
				session.setAttribute("posTorreN2", posTorreN2);
			} else session.setAttribute("posTorreN2", "");
			
			
			posicionPieza = pAJugar.getColPiezas().get(20).getPosicion();
			if(posicionPieza!=null){
				String posCaballoB1 = posicionPieza.toString();
				session.setAttribute("posCaballoB1", posCaballoB1);
			} else session.setAttribute("posCaballoB1", "");
			
			posicionPieza = pAJugar.getColPiezas().get(21).getPosicion();
			if(posicionPieza!=null){
				String posCaballoB2 = posicionPieza.toString();
				session.setAttribute("posCaballoB2", posCaballoB2);
			} else session.setAttribute("posCaballoB2", "");
			
			
			posicionPieza = pAJugar.getColPiezas().get(22).getPosicion();
			if(posicionPieza!=null){
				String posCaballoN1 = posicionPieza.toString();
				session.setAttribute("posCaballoN1", posCaballoN1);
			} else session.setAttribute("posCaballoN1", "");
			
			posicionPieza = pAJugar.getColPiezas().get(23).getPosicion();
			if(posicionPieza!=null){
				String posCaballoN2 = posicionPieza.toString();
				session.setAttribute("posCaballoN2", posCaballoN2);
			} else session.setAttribute("posCaballoN2", "");
			
			
			posicionPieza = pAJugar.getColPiezas().get(24).getPosicion();
			if(posicionPieza!=null){
				String posAlfilB1 = posicionPieza.toString();
				session.setAttribute("posAlfilB1", posAlfilB1);
			} else session.setAttribute("posAlfilB1", "");
			
			posicionPieza = pAJugar.getColPiezas().get(25).getPosicion();
			if(posicionPieza!=null){
				String posAlfilB2 = posicionPieza.toString();
				session.setAttribute("posAlfilB2", posAlfilB2);
			} else session.setAttribute("posAlfilB2", "");
			
			posicionPieza = pAJugar.getColPiezas().get(26).getPosicion();
			if(posicionPieza!=null){
				String posAlfilN1 = posicionPieza.toString();
				session.setAttribute("posAlfilN1", posAlfilN1);
			} else session.setAttribute("posAlfilN1", "");
			
			posicionPieza = pAJugar.getColPiezas().get(27).getPosicion();
			if(posicionPieza!=null){
				String posAlfilN2 = posicionPieza.toString();
				session.setAttribute("posAlfilN2", posAlfilN2);
			} else session.setAttribute("posAlfilN2", "");
			
			
			posicionPieza = pAJugar.getColPiezas().get(28).getPosicion();
			if(posicionPieza!=null){
				String posReyB = posicionPieza.toString();
				session.setAttribute("posReyB", posReyB);
			} else session.setAttribute("posReyB", "");
			
			posicionPieza = pAJugar.getColPiezas().get(29).getPosicion();
			if(posicionPieza!=null){
				String posReyN = posicionPieza.toString();
				session.setAttribute("posReyN", posReyN);
			} else session.setAttribute("posReyN", "");
			
			
			posicionPieza = pAJugar.getColPiezas().get(30).getPosicion();
			if(posicionPieza!=null){
				String posDamaB = posicionPieza.toString();
				session.setAttribute("posDamaB", posDamaB);
			} else session.setAttribute("posDamaB", "");
			
			posicionPieza = pAJugar.getColPiezas().get(31).getPosicion();
			if(posicionPieza!=null){
				String posDamaN = posicionPieza.toString();
				session.setAttribute("posDamaN", posDamaN);
			} else session.setAttribute("posDamaN", "");
			
		
		
	}
	
	public void setearAtributoPosiciones(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		
		String posPeonB1 = new String();
		session.setAttribute("posPeonB1", posPeonB1);
		String posPeonB2 = new String();
		session.setAttribute("posPeonB2", posPeonB2);
		String posPeonB3 = new String();
		session.setAttribute("posPeonB3", posPeonB3);
		String posPeonB4 = new String();
		session.setAttribute("posPeonB4", posPeonB4);
		String posPeonB5 = new String();
		session.setAttribute("posPeonB5", posPeonB5);
		String posPeonB6 = new String();
		session.setAttribute("posPeonB6", posPeonB6);
		String posPeonB7 = new String();
		session.setAttribute("posPeonB7", posPeonB7);
		String posPeonB8 = new String();
		session.setAttribute("posPeonB8", posPeonB8);
		
		String posPeonN1 = new String();
		session.setAttribute("posPeonN1", posPeonN1);
		String posPeonN2 = new String();
		session.setAttribute("posPeonN2", posPeonN2);
		String posPeonN3 = new String();
		session.setAttribute("posPeonN3", posPeonN3);
		String posPeonN4 = new String();
		session.setAttribute("posPeonN4", posPeonN4);
		String posPeonN5 = new String();
		session.setAttribute("posPeonN5", posPeonN5);
		String posPeonN6 = new String();
		session.setAttribute("posPeonN6", posPeonN6);
		String posPeonN7 = new String();
		session.setAttribute("posPeonN7", posPeonN7);
		String posPeonN8 = new String();
		session.setAttribute("posPeonN8", posPeonN8);
		
		
		String posTorreB1 = new String();
		session.setAttribute("posTorreB1", posTorreB1);
		String posTorreB2 = new String();
		session.setAttribute("posTorreB2", posTorreB2);
		
		String posTorreN1 = new String();
		session.setAttribute("posTorreN1", posTorreN1);
		String posTorreN2 = new String();
		session.setAttribute("posTorreN2", posTorreN2);
		
		
		String posCaballoB1 = new String();
		session.setAttribute("posCaballoB1", posCaballoB1);
		String posCaballoB2 = new String();
		session.setAttribute("posCaballoB2", posCaballoB2);
		
		String posCaballoN1 = new String();
		session.setAttribute("posCaballoN1", posCaballoN1);
		String posCaballoN2 = new String();
		session.setAttribute("posCaballoN2", posCaballoN2);
		
		
		String posAlfilB1 = new String();
		session.setAttribute("posAlfilB1", posAlfilB1);
		String posAlfilB2 = new String();
		session.setAttribute("posAlfilB2", posAlfilB2);
		
		String posAlfilN1 = new String();
		session.setAttribute("posAlfilN1", posAlfilN1);
		String posAlfilN2 = new String();
		session.setAttribute("posAlfilN2", posAlfilN2);
		
		
		String posReyB = new String();
		session.setAttribute("posReyB", posReyB);
		String posReyN = new String();
		session.setAttribute("posReyN", posReyN);
		
		
		String posDamaB = new String();
		session.setAttribute("posDamaB", posDamaB);
		String posDamaN = new String();
		session.setAttribute("posDamaN", posDamaN);
	}

}
