package entidades;

public class Peon extends Pieza {
	
	private String posicionInicial = new String();
	
	public String getPosicionInicial() {
		return posicionInicial;
	}

	public void setPosicionInicial(String posicionInicial) {
		this.posicionInicial = posicionInicial;
	}


	public boolean esMovimientoValido(String posOrigen ,String posDestino) {
		return true;
	}
	
	//getX toma la letra de la posicion y devuelve un nro
	//getY toma el numero de la posicion y devuelve un nro
		
	public boolean esMovimientoValidoPeon1(String posOrigen ,String posDestino, String idPeon) {
		//movimiento1 es cuando el peon SOLO SE MUEVE, pero NO COME.
		//valido que se mueva 1 espacio verticalmente o 2, si es el primer movimiento del peon en cuestion.
		
		boolean movimiento = false;
		Partida pPrueba = new Partida();
		System.out.println("llego al metodo esMovimientoValidoPeon1");
		
		Color colorPeon;
		if(idPeon.charAt(1) == 'b') colorPeon = Color.BLANCO;
		else colorPeon = Color.NEGRO;
		System.out.println(colorPeon);
		
		if ((this.getY(posOrigen)) == (this.getY(posDestino))) { movimiento = false; } //si se quiere mover horizontalmente, falso.
		
		else if((this.getX(posOrigen)) == (this.getX(posDestino))) { //si se mueve en la misma columna..
			System.out.println("me muevo en la misma columna");
			pPrueba.peonesPosicIniciales(); //creo mi coleccion "auxilar" de peones, que tiene las posic iniciales
			for(Peon peon : pPrueba.getColPeones()) {  // a c/peon de esa coleccion pido su id
					
					if(peon.getId().equals(idPeon)) { //si coincide con el id que estoy por mover..
							
						if(peon.getPosicionInicial().toString().equals(posOrigen)) { //valido que la posicion de origen sea la posicion inicial
								//permito mover dos lugares
									System.out.println("puedo moverme 2 lugares como maximo");
								if(colorPeon.equals(Color.BLANCO)){ 
									
									if(((this.getY(posDestino))-(this.getY(posOrigen))) == 2 || ((this.getY(posDestino))-(this.getY(posOrigen))) == 1){ 
										movimiento = true;
										System.out.println("la diferencia es 2 o 1.");
									}
									else {
										movimiento = false;//es falso si no se mueve (diferencia == 0) o si se mueve mas lugares.
									}
								} 
								
								else if(colorPeon.equals(Color.NEGRO)){ //si el color es negro
									
									if( (((this.getY(posDestino))-(this.getY(posOrigen))) == -2) || (((this.getY(posDestino))-(this.getY(posOrigen))) == -1) ) {
									movimiento = true;
									System.out.println("la diferencia es -2 o -1.");
									
									} else movimiento = false;//es falso si no se mueve (diferencia == 0) o si se mueve mas lugares.
								
								} else movimiento = false;
								
						} 	else{ //esto hace si el origen NO es la posicion inicial
									
										if(colorPeon.equals(Color.BLANCO)){ 
											
											if(((this.getY(posDestino))-(this.getY(posOrigen))) == 1){ 
												movimiento = true;
												System.out.println("la diferencia es 2 o 1.");
											}
											else {
												movimiento = false;//es falso si no se mueve (diferencia == 0) o si se mueve mas lugares.
											}
										} 
										
										else if(colorPeon.equals(Color.NEGRO)){ //si el color es negro
											
											if( (((this.getY(posDestino))-(this.getY(posOrigen))) == -1) ) {
											movimiento = true;
											System.out.println("la diferencia es 2 o 1.");
											
											} else movimiento = false;//es falso si no se mueve (diferencia == 0) o si se mueve mas lugares.
										
										} else movimiento = false;
						
							}
					} //cierre if(peon.getId().equals(idPeon))
					
			} //cierre for
			
		} //cierre bloque:  else if((this.getX(posOrigen)) == (this.getX(posDestino)))
		
		else movimiento = false;
	
		
		
		
	return movimiento;
	
	}
	
	public boolean esMovimientoValidoPeon2(String posOrigen ,String posDestino, Color colorPeon) {
		//movimiento2 se activa cuando en destino hay una pieza.
		
		boolean movimiento = false;
		System.out.println("llego al metodo esMovimientoValidoPeon2");
		
		System.out.println(colorPeon);
		int movX = this.getX(posOrigen)- this.getX(posDestino);
		int movY = this.getY(posOrigen)- this.getY(posDestino);
		
		
		if (colorPeon.equals(Color.BLANCO)){
			if ((movX == movY) && (movX == 1 || movX == -1) && movY == -1 ) movimiento = true;
			else movimiento = false;
		}
		else if (colorPeon.equals(Color.NEGRO)){
			if ((movX == movY) && (movX == 1 || movX == -1) && movY == 1 ) movimiento = true;
			else movimiento = false;
		}
		else movimiento = false;
		
		return movimiento;
	}

}
