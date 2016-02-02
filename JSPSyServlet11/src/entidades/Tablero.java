package entidades;

public class Tablero {
	
	private String[][] posicionesFichas = new String[8][8];

	
	public String[][] getPosicionesFichas() {
		return posicionesFichas;
	}


	public void setPosicionesFichas(String[][] posicionesFichas) {
		this.posicionesFichas = posicionesFichas;
	}
	
	
	public String cambioString(String posTab) {
		
		String posJava = new String();
		int j=0;
		
		//POR QUE NO SE PUEDE DECLARAR COMO PRIVATE A letraNum?
		
		final String[][] letraNum = {{"a","0"},{"b","1"},{"c","2"},{"d","3"},
				{"e","4"},{"f","5"},{"g","6"},{"h","7"}};
		
		for (int i=0 ; i<8; i++) { 
			
			
			if( String.valueOf(posTab.charAt(0)).equals(letraNum[i][j]) )  {
				
				int num =  Math.abs((Integer.parseInt(String.valueOf(posTab.charAt(1))) - 1) - 7);
				
				String numPos = Integer.toString(num);
				
				posJava = numPos + letraNum[i][j+1];
				
				
				/* Primero creamos un arreglo que hace corresponder a cada letra del tablero nros
				 * del 0 al 7, que es como entenderia Java. Luego al nro obtenido por la letra
				 * lo concatenamos con el nro de la posicion ingresada por el jugador, y este
				 * nro debe ser reducido en 1, ya que arreglos en java comienzan en 0, pero el tablero
				 * en 1 */
				
			}
			
		}
		
		return posJava;
	}
	
	
	//se usa para buscar en el origen y destino dentro de la 
	//colPiezas. Devuelve una objeto de tipo Pieza o null.
	
	/*public Pieza buscarPieza(String origenTeclado){
		
		String origen = this.cambioString(origenTeclado); //origen es la posicion, ya entendida por java
		
	} */
	
	
	}



