package com.javarevolutions.jsps.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javarevolutions.jsps.servlets.vo.VOLogin;

import negocio.*;
import entidades.*;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Controlador ctrl = new Controlador();
    
    public static Controlador getCtrl() {
		return ctrl;
	}

	public static void setCtrl(Controlador ctrl) {
		LoginServlet.ctrl = ctrl;
	}

    /**
     * Default constructor. 
     */
    public LoginServlet() {
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
		
		String dniBlancas = request.getParameter("dniBlancas");
		String dniNegras = request.getParameter("dniNegras");
		
		VOLogin voBlancas = new VOLogin();
		VOLogin voNegras = new VOLogin();
		
		voBlancas.setDNI(Integer.parseInt(dniBlancas));
		voNegras.setDNI(Integer.parseInt(dniNegras));		
		
		HttpSession session = request.getSession(true);
		
		session.setAttribute("dniBlancasLogueado", voBlancas);
		session.setAttribute("dniNegrasLogueado", voNegras);
		
		FuncionesServlet servletFunciones = new FuncionesServlet();
		servletFunciones.setearAtributoPosiciones(request, response);
		
		Partida pEncontrada = ctrl.buscarPartida(Integer.parseInt(dniBlancas), Integer.parseInt(dniNegras));
		
		//validacion usada en welcome.jsp linea 94
		if (pEncontrada != null) { 
			session.setAttribute("nroPartida", pEncontrada.getId());
			session.setAttribute("turno", "");

		}else{
			session.setAttribute("nroPartida", null);
			session.setAttribute("turno", "");
				} 
		//fin validacion
		
		request.getRequestDispatcher("welcome.jsp").forward(request, response);	
		
	}
}
