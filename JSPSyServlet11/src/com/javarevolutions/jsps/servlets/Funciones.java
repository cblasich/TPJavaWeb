package com.javarevolutions.jsps.servlets;

import negocio.Controlador;
import entidades.Partida;

import com.javarevolutions.jsps.servlets.vo.VOPartida;

public class Funciones {
	
	public static Partida convertVOPartidaAPartida(VOPartida pVO){
		//convierte una variable serializable VOPartida en clase Partida
		Partida p = new Partida();
		
		p.setColPiezas(pVO.getColPiezas());
		p.setTurno(pVO.getTurno());
		p.getJugadorB().setDni(pVO.getJugadorB().getDNI());
		p.getJugadorN().setDni(pVO.getJugadorN().getDNI());
		
		return p;
	}
	
	public static VOPartida convertPartidaToVOPartida(Partida p){
		//convierte una variable de clase Partida en clase VOPartida
		VOPartida pVO = new VOPartida();
		
		pVO.setColPiezas(p.getColPiezas());
		pVO.setTurno(p.getTurno());
		pVO.getJugadorB().setDNI(p.getJugadorB().getDni());
		pVO.getJugadorN().setDNI(p.getJugadorN().getDni());
		
		return pVO;
	}
	
	public static void guardarPartida() { //PARA BOTON GUARDAR (CORREGIDO: EN CASO DE QUE SE CREA PARTIDA NUEVA, Y LUEGO SE
		//CLICKEA EN GUARDAR)
		
		Controlador ctrl = LoginServlet.getCtrl();
		Partida p = ctrl.getpActual();
		if (ctrl.save(p)) System.out.println("Partida creada");
		else System.out.println("Partida sobreescrita");
		ctrl.setpActual(p);
		
	}
	
	public static void guardarPartida(Partida p) {
		
		Controlador ctrl = LoginServlet.getCtrl();
		if (ctrl.save(p)) System.out.println("Partida creada");
		else System.out.println("Partida sobreescrita");
		
	}
	
	

}
