package negocio;

import javax.swing.JOptionPane;

import entidades.Color;
import entidades.Jugador;
import entidades.Peon;
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

	public boolean save(Jugador j) { //verifica si el jugador está o no en el catálogo
		Jugador jugNuevo = new Jugador(); //en este jugador creado se va a guardar el jugador encontrado
		jugNuevo = bufferCatJug.BuscarEnCatJug(j.getDni());
		if (jugNuevo == null){
									bufferCatJug.agregarJugador(j);
									return true;
									}
		else return false;
	}
	
	public boolean save(Partida p) { //verifica si la partida está o no en el catálogo
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
	
	
	public boolean esMovimientoValidoWeb (Partida p, String posOrigen, String posDestino) {
		
		boolean rta = false;
		Pieza piezaAMover = null,
			  piezaAComer = null;
		
			if (this.buscarPieza(p, posOrigen) != null) {
				
				piezaAMover = this.buscarPieza(p, posOrigen);
				
				if(!(piezaAMover instanceof Peon)) {
				
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
								
								}
	
						}
						
						piezaAMover.setPosicion(posDestino);
						this.cambiarPosicionPieza(p, piezaAMover);
						
						rta = true;
					
					} else { rta = false;
							 
					  }
					
				} else { rta = false;
				         //JOptionPane.showMessageDialog(null, "Destino inválido", "Movimiento inválido", 
				        		 						//JOptionPane.ERROR_MESSAGE, null );
				  }
		
			}	//cierra bloque piezas != peon
				
				else if(piezaAMover instanceof Peon){
					System.out.println("voy a mover un peon");
					String idPeon = piezaAMover.getId();
					Color colorPeon;
					if(idPeon.charAt(1) == 'b') colorPeon = Color.BLANCO;
					else colorPeon = Color.NEGRO;
					System.out.println("Voy a mover el peon: " +idPeon);
					//si pAMover es un peon, lo primero que valido es que el destino esté vacio o no. ya que en caso de 
					//que haya posibilidad de "comer", el movimiento es distinto a un simple movimiento sin comer.
					
					if(this.validarDestino(p, posDestino)){ //si el destino es valido, voy a ver si esta vacío o con pieza.
						System.out.println("voy a validar destino");
						
						for(Pieza pieza : p.getColPiezas()){
							if (pieza.getPosicion() != null && pieza.getPosicion().equals(posDestino) && 
									!(pieza.getColor().equals(p.getTurno()))) {
									
									piezaAComer = p.instanciarPieza(pieza, pieza.getId());
								}
						}
						
						if(piezaAComer == null) { //si el destino está vacío, valido movimiento1 del peon
							System.out.println("No hay pieza para comer. Movimiento1 del peon");
							
							if(((Peon)piezaAMover).esMovimientoValidoPeon1(posOrigen, posDestino, idPeon)) {
								System.out.println("movimiento1 del peon validado en controlador");
								piezaAMover.setPosicion(posDestino);
								this.cambiarPosicionPieza(p, piezaAMover);
								
								rta = true;
							} else rta = false;
							
						} else { //(piezaAComer != null) => sí, hay algo para comer. Entonces valido movimiento2
							System.out.println("Hay pieza para comer. Movimiento2 del peon");
							
							if(((Peon)piezaAMover).esMovimientoValidoPeon2(posOrigen, posDestino, colorPeon)) {
								System.out.println("movimiento2 del peon validado en controlador");
								piezaAMover.setPosicion(posDestino);
								this.cambiarPosicionPieza(p, piezaAMover);
								
								piezaAComer.setPosicion(null);
								this.cambiarPosicionPieza(p, piezaAComer); 
								
								if(piezaAComer.getId().equals("rb"))
									JOptionPane.showMessageDialog(null, " Jaque mate! Ganan las negras. ", 
											"Partida finalizada", JOptionPane.INFORMATION_MESSAGE, null );
								
								if(piezaAComer.getId().equals("rn"))
									JOptionPane.showMessageDialog(null, " Jaque mate! Ganan las blancas. ", 
											"Partida finalizada", JOptionPane.INFORMATION_MESSAGE, null );
								
								if(piezaAComer.getId().equals("rb") || piezaAComer.getId().equals("rn")) {
									
									//ELIMINAR PARTIDA ACTUAL DE LA BASE DE DATOS. PIEZAS Y "PARTIDA". para que los mismos dni 
									//puedan volver a jugar.
									eliminarPartida(pActual);
									System.exit(0); //cierra la aplicación :)
								}
								
								rta = true;
								
							} else rta = false;
							
						  }
						
						
					} //cierra if(this.validarDestino(p, posDestino))
					
				} //cierra bloque para peones.
				
				
		} else { //cierra if de validar origen
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
					break; //sale del for porque ya encontró pieza
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
						JOptionPane.showMessageDialog(null, "Destino tiene pieza de su color", "Movimiento inválido", JOptionPane.ERROR_MESSAGE, null );
						break; //sale del for porque en el destino hay una pieza cuyo color = color turno 
					
					} else { destino = true;  //si el color de la pieza es != color turno, el destino es verdadero/valido
							                   //entonces voy a "comer" la pieza que está en el destino
						     break; 																  
					  }
				
				} else	destino = true; //esto debe ser true para c/pieza en caso de destino estar vacío
			}
		}
		return destino;
	}


	public void cambiarPosicionPieza(Partida p, Pieza piezaACambiar) {
	//este método recibe una pieza (a comer o a mover), 

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
