package com.javarevolutions.jsps.servlets.vo;

import java.io.Serializable;

public class VOLogin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7775167431914195386L;
	private Integer DNI;
	private String nombre;
	private String apellido;
	
	public Integer getDNI() {
		return DNI;
	}
	public void setDNI(Integer dNI) {
		DNI = dNI;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	
}
