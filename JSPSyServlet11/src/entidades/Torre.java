package entidades;

public class Torre extends Pieza {

	public boolean esMovimientoValido(String posOrigen ,String posDestino) {
		
		if ((this.getX(posOrigen)) != (this.getX(posDestino))) {
			if ((this.getY(posOrigen)) != (this.getY(posDestino))) return false;
			else return true;}
		else if ((this.getY(posOrigen)) != (this.getY(posDestino))) {
			if ((this.getX(posOrigen)) != (this.getX(posDestino))) return false;
			else return true;}
		else return false;
	
	}
	
	public boolean esMovimientoValidoPeon1(String posOrigen ,String posDestino, String idPeon) {
	return true;
	}
	
	public boolean esMovimientoValidoPeon2(String posOrigen ,String posDestino, Color cPeon) {
		return true;
	}
}
