package entidades;

public class Rey extends Pieza {
	
	public boolean esMovimientoValido(String posOrigen ,String posDestino) {
		
		if (Math.abs(this.getX(posOrigen)- this.getX(posDestino)) == 1){
			if((Math.abs(this.getY(posOrigen)- this.getY(posDestino)) <= 1)) return true;
			else return false; }
		else if (Math.abs(this.getX(posOrigen)- this.getX(posDestino)) == 1){
			if((Math.abs(this.getY(posOrigen)- this.getY(posDestino)) <=1)) return true;
			else return false; }
		else return false;
		
		}

}
