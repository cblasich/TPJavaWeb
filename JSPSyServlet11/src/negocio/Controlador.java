package negocio;

import javax.swing.JOptionPane;

import entidades.Jugador;
import entidades.Pieza;
import datos.CatalogoJugadores;
import entidades.Partida;
import datos.CatalogoPartidas;

public class Controlador {
	
	private CatalogoJugadores bufferCatJug = new CatalogoJugadores();
	private CatalogoPartidas bufferCatPar = new CatalogoPartidas();
	private Partida pActual = new Partida();
	
	public CatalogoJugadores getBufferCatJug() {
		return bufferCatJug;
	}

	public void setBufferCatJug(CatalogoJugadores bufferCatJug) {
		this.bufferCatJug = bufferCatJug;
	}

	public CatalogoPartidas getBufferCatPar() {
		return bufferCatPar;
	}

	public void setBufferCatPar(CatalogoPartidas bufferCatPar) {
		this.bufferCatPar = bufferCatPar;
	}

	public Partida getpActual() {
		return pActual;
	}

	public void setpActual(Partida pActual) {
		this.pActual = pActual;
	}

	public boolean save(Jugador j) { //verifica si el jugador est� o no en el cat�logo
		Jugador jugNuevo = new Jugador(); //en este jugador creado se va a guardar el jugador encontrado
		jugNuevo = bufferCatJug.BuscarEnCatJug(j.getDni());
		if (jugNuevo == null){
									bufferCatJug.agregarJugador(j);
									return true;
									}
		else return false;
	}
	
	public boolean save(Partida p) { //verifica si la partida est� o no en el cat�logo
		//devuelve true si la partida NO estaba en el cat�logo
		pActual = bufferCatPar.BuscarEnCatPar(p.getJugadorB().getDni(), p.getJugadorN().getDni());
		if (pActual == null){
									bufferCatPar.agregarPartida(p);
									return true;
									}
		else bufferCatPar.update(p);
		return false;
	}
	
	
	public Partida buscarPartida(int dniB, int dniN) {
		
		return bufferCatPar.BuscarEnCatPar(dniB, dniN);
				
	}
	
	public Partida buscarPartida(Partida oldPartida)	{
		
		return bufferCatPar.BuscarEnCatPar(oldPartida);
		
	}
	
	public boolean esMovimientoValido (Partida p, String posOrigen, String posDestino) {
		
		boolean rta = false;
		Pieza piezaAMover = null,
			  piezaAComer = null;
		
		if (this.buscarPieza(p, posOrigen) != null) {
			
			piezaAMover = this.buscarPieza(p, posOrigen);
			
			
			if (this.validarDestino(p, posDestino)) {
				
				if (piezaAMover.esMovimientoValido(posOrigen, posDestino)) {
					
					for (Pieza pieza : p.getColPiezas()) {
						if (pieza.getPosicion() != null && pieza.getPosicion().equals(posDestino) && 
							!(pieza.getColor().equals(p.getTurno()))) {
							
							piezaAComer = p.instanciarPieza(pieza, pieza.getId());
						}
					}
					
					if (piezaAComer != null) {
						
						piezaAComer.setPosicion(null);
						this.cambiarPosicionPieza(p, piezaAComer); 
						
						if(piezaAComer.getId().equals("rb"))
							JOptionPane.showMessageDialog(null, "Jaque mate! Ganan las negras. ", 
									"Partida finalizada", JOptionPane.INFORMATION_MESSAGE, null );
						
						if(piezaAComer.getId().equals("rn"))
							JOptionPane.showMessageDialog(null, "Jaque mate! Ganan las blancas. ", 
									"Partida finalizada", JOptionPane.INFORMATION_MESSAGE, null );
						
						if(piezaAComer.getId().equals("rb") || piezaAComer.getId().equals("rn")) {
							
							//ELIMINAR PARTIDA ACTUAL DE LA BASE DE DATOS. PIEZAS Y "PARTIDA". para que los mismos dni 
							//puedan volver a jugar.
							eliminarPartida(pActual);
							System.exit(0); //cierra la aplicaci�n :)
						}

					}
					
					piezaAMover.setPosicion(posDestino);
					this.cambiarPosicionPieza(p, piezaAMover);
					
					rta = true;
				
				} else { rta = false;
						 JOptionPane.showMessageDialog(null, "Movimiento no permitido para esa pieza.", "Movimiento inv�lido", 
								 						JOptionPane.ERROR_MESSAGE, null );
				  }
				
			} else { rta = false;
			         JOptionPane.showMessageDialog(null, "Destino inv�lido", "Movimiento inv�lido", 
			        		 						JOptionPane.ERROR_MESSAGE, null );
			  }
			
		} else { JOptionPane.showMessageDialog(null, "Origen inv�lido", "Movimiento inv�lido", JOptionPane.ERROR_MESSAGE, null );
				 rta = false;
		  }
		
		return rta;
	}
	
	
public boolean esMovimientoValidoWeb (Partida p, String posOrigen, String posDestino) {
		
		boolean rta = false;
		Pieza piezaAMover = null,
			  piezaAComer = null;
		
		if (this.buscarPieza(p, posOrigen) != null) {
			
			piezaAMover = this.buscarPieza(p, posOrigen);
			
			
			if (this.validarDestino(p, posDestino)) {
				
				if (piezaAMover.esMovimientoValido(posOrigen, posDestino)) {
					
					for (Pieza pieza : p.getColPiezas()) {
						if (pieza.getPosicion() != null && pieza.getPosicion().equals(posDestino) && 
							!(pieza.getColor().equals(p.getTurno()))) {
							
							piezaAComer = p.instanciarPieza(pieza, pieza.getId());
						}
					}
					
					if (piezaAComer != null) {
						
							piezaAComer.setPosicion(null);
							this.cambiarPosicionPieza(p, piezaAComer); 
							
							if(piezaAComer.getId().equals("rb") || piezaAComer.getId().equals("rn")) {
								
								//ELIMINAR PARTIDA ACTUAL DE LA BASE DE DATOS. PIEZAS Y "PARTIDA". para que los mismos dni 
								//puedan volver a jugar.
								eliminarPartida(pActual);
								//System.exit(0); //cierra la aplicaci�n :)
							}

					}
					
					piezaAMover.setPosicion(posDestino);
					this.cambiarPosicionPieza(p, piezaAMover);
					
					rta = true;
				
				} else { rta = false;
						 //JOptionPane.showMessageDialog(null, "Movimiento no permitido para esa pieza.", "Movimiento inv�lido", 
								 					//	JOptionPane.ERROR_MESSAGE, null );
				  }
				
			} else { rta = false;
			         //JOptionPane.showMessageDialog(null, "Destino inv�lido", "Movimiento inv�lido", 
			        		 						//JOptionPane.ERROR_MESSAGE, null );
			  }
			
		} else { //JOptionPane.showMessageDialog(null, "Origen inv�lido", "Movimiento inv�lido", JOptionPane.ERROR_MESSAGE, null );
				 rta = false;
		  }
		
		return rta;
	}
	
	
	public Pieza buscarPieza(Partida p, String posOrigen) {
		
		Pieza piezaAMover = null;
		
		for (Pieza pieza : p.getColPiezas()) {
			
			if (pieza.getColor().equals(p.getTurno())) {
				
				if(pieza.getPosicion() != null) {
				if (pieza.getPosicion().equals(posOrigen)) {
					
					piezaAMover = p.instanciarPieza(pieza, pieza.getId());
					break; //sale del for porque ya encontr� pieza
				}
				}
			}
			
		}
		return piezaAMover;
	}

	
	public boolean validarDestino(Partida p, String posDestino) {
		
		boolean destino=false;
		
		for (Pieza pieza : p.getColPiezas()) {
			
			if (pieza.getPosicion() != null) { //solo entra si tiene una posicion distinta de null
				
				if (pieza.getPosicion().equals(posDestino)) { //entra si la pieza tiene su posicion igual = destino
					
					if(pieza.getColor().equals(p.getTurno())) { 
						destino = false;  //si el color de la pieza es = color turno, el destino es falso/invalido
						//JOptionPane.showMessageDialog(null, "Destino tiene pieza de su color", "Movimiento inv�lido", JOptionPane.ERROR_MESSAGE, null );
						break; //sale del for porque en el destino hay una pieza cuyo color = color turno 
					
					} else { destino = true;  //si el color de la pieza es != color turno, el destino es verdadero/valido
							                   //entonces voy a "comer" la pieza que est� en el destino
						     break; 																  
					  }
				
				} else	destino = true; //esto debe ser true para c/pieza en caso de destino estar vac�o
			}
		}
		return destino;
	}


	public void cambiarPosicionPieza(Partida p, Pieza piezaACambiar) {
	//este m�todo recibe una pieza (a comer o a mover), 

	for (Pieza pieza : p.getColPiezas()) {
			
			if(pieza.getId().equals(piezaACambiar.getId())) {
				
				pieza.setPosicion(piezaACambiar.getPosicion());
				
				break;
			}
		} 
		
	}
	
	public void eliminarPartida(Partida pActual){
		
		bufferCatPar.eliminarPartida(pActual);
		
	}
}
