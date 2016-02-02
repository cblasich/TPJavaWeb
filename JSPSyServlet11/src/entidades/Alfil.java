package entidades;

public class Alfil extends Pieza {

	public boolean esMovimientoValido(String posOrigen ,String posDestino) {
		
		int movX = Math.abs(this.getX(posOrigen)- this.getX(posDestino));
		int movY = Math.abs(this.getY(posOrigen)- this.getY(posDestino));
		
		
		if (movX == movY) return true;
		else return false;
		
	}
}
