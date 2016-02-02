package entidades;

public abstract class Pieza {
	
	private String posicion = new String();
	private String id = new String(); 
	private Color color;
	int x, y;
	
	public int getX(){
		/*Si x=0, devolver un cartel que diga que la posicion no existe*/
		
		int x;
		
		switch (this.posicion.charAt(0))
		{
			case 'a': x = 1;
			break;
			
			case 'b': x = 2;
			break;
			
			case 'c': x = 3;
			break;
			
			case 'd': x = 4;
			break;
			
			case 'e': x = 5;
			break;
			
			case 'f': x = 6;
			break;
			
			case 'g': x = 7;
			break;
			
			case 'h': x = 8;
			break;
			
			default: x = 0;
			
		}
		
		return x;
	}
	
	public int getX(String posDesUOri){
		/*Si x=0, devolver un cartel que diga que la posicion no existe*/
		
		int x;
		
		switch (posDesUOri.charAt(0))
		{
			case 'a': x = 1;
			break;
			
			case 'b': x = 2;
			break;
			
			case 'c': x = 3;
			break;
			
			case 'd': x = 4;
			break;
			
			case 'e': x = 5;
			break;
			
			case 'f': x = 6;
			break;
			
			case 'g': x = 7;
			break;
			
			case 'h': x = 8;
			break;
			
			default: x = 0;
			
		}
		
		return x;
	}
	
	public int getY(){
		/*Si y=0, devolver un cartel que diga que la posicion no existe*/
		
		int y;
		
		switch (this.posicion.charAt(1))
		{
			case '1': y = 1;
			break;
			
			case '2': y = 2;
			break;
			
			case '3': y = 3;
			break;
			
			case '4': y = 4;
			break;
			
			case '5': y = 5;
			break;
			
			case '6': y = 6;
			break;
			
			case '7': y = 7;
			break;
			
			case '8': y = 8;
			break;
			
			default: y = 0;
			
		}
		
		return y;
		
	}
	
	public int getY(String posDesUOri){
		/*Si y=0, devolver un cartel que diga que la posicion no existe*/
		
		int y;
		
		switch (posDesUOri.charAt(1))
		{
			case '1': y = 1;
			break;
			
			case '2': y = 2;
			break;
			
			case '3': y = 3;
			break;
			
			case '4': y = 4;
			break;
			
			case '5': y = 5;
			break;
			
			case '6': y = 6;
			break;
			
			case '7': y = 7;
			break;
			
			case '8': y = 8;
			break;
			
			default: y = 0;
			
		}
		
		return y;
		
	}
	
	
	/*public boolean estaEnTablero() {
		
		if(posicion != null) return true;
		else return false;
	} */

	
	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}
	
	
	public abstract boolean esMovimientoValido(String posOrigen ,String posDestino) ;

}
