package entidades;

public class Dama extends Pieza {
	
	public boolean esMovimientoValido(String posOrigen ,String posDestino) {
		
		int movX = Math.abs(this.getX(posOrigen)- this.getX(posDestino));
		int movY = Math.abs(this.getY(posOrigen)- this.getY(posDestino));
		
		
		
		if (movX == movY) return true;
		else if ((this.getX(posOrigen)) != (this.getX(posDestino))) {
			if ((this.getY(posOrigen)) != (this.getY(posDestino))) return false;
			else return true;}
		else if ((this.getY(posOrigen)) != (this.getY(posDestino))) {
			if ((this.getX(posOrigen)) != (this.getX(posDestino))) return false;
			else return true;}
		else return false;
		}
		
		;
		
		
	}

