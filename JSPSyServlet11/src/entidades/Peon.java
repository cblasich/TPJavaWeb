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
		
		//this.getPosicionInicial();
		
		return true;
	}

}
