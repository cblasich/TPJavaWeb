package entidades;

public class Alfil extends Pieza {

	public boolean esMovimientoValido(String posOrigen ,String posDestino) {
		
		int movX = Math.abs(this.getX(posOrigen)- this.getX(posDestino));
		int movY = Math.abs(this.getY(posOrigen)- this.getY(posDestino));
		
		
		if (movX == movY) return true;
		else return false;
		
	}
	
	public boolean esMovimientoValidoPeon1(String posOrigen ,String posDestino, String idPeon) {
		return true;
		}
		
		public boolean esMovimientoValidoPeon2(String posOrigen ,String posDestino, Color cPeon) {
			return true;
		}
}
