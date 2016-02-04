package entidades;

import java.util.ArrayList;

public class Partida {

	int id;
	Jugador jugadorB = new Jugador(), jugadorN = new Jugador();
	Color turno;
	ArrayList<Pieza> colPiezas = new ArrayList<Pieza>();
	ArrayList<Peon> colPeones = new ArrayList<Peon>();
	
	public Partida(){
		
		instanciarYAddPiezas();
		
	}
	
	public void instanciarYAddPiezas() {

		Peon pb1 = new Peon(), pb2 = new Peon(), pb3 = new Peon(), pb4 = new Peon(), 
			 pb5 = new Peon(), pb6 = new Peon(), pb7 = new Peon(), pb8 = new Peon(),
			 pn1 = new Peon(), pn2 = new Peon(), pn3 = new Peon(), pn4 = new Peon(), 
		     pn5 = new Peon(), pn6 = new Peon(), pn7 = new Peon(), pn8 = new Peon();

		Torre tb1 = new Torre(), tb2 = new Torre(),
			  tn1 = new Torre(), tn2 = new Torre();
		
		Caballo cb1 = new Caballo(), cb2 = new Caballo(), 
				cn1 = new Caballo(), cn2 = new Caballo();
		
		Alfil ab1 = new Alfil(), ab2 = new Alfil(), 
			  an1 = new Alfil(), an2 = new Alfil();
		
		Rey rb = new Rey(), 
		    rn = new Rey();
		
		Dama db = new Dama(),
	         dn = new Dama();
		
		
		Pieza[] arrayPeonesBlancos = new Peon[8];
		Pieza[] arrayPeonesNegros = new Peon[8];
		Pieza[] arrayTorresBlancas = new Torre[2];
		Pieza[] arrayTorresNegras = new Torre[2];
		Pieza[] arrayCaballosBlancos = new Caballo[2];
		Pieza[] arrayCaballosNegros = new Caballo[2];
		Pieza[] arrayAlfilesBlancos = new Alfil[2];
		Pieza[] arrayAlfilesNegros = new Alfil[2];
		
		
		arrayPeonesBlancos[0] = pb1;
		arrayPeonesBlancos[1] = pb2;
		arrayPeonesBlancos[2] = pb3;
		arrayPeonesBlancos[3] = pb4;
		arrayPeonesBlancos[4] = pb5;
		arrayPeonesBlancos[5] = pb6;
		arrayPeonesBlancos[6] = pb7;
		arrayPeonesBlancos[7] = pb8;	
		arrayPeonesNegros[0] = pn1;
		arrayPeonesNegros[1] = pn2;
		arrayPeonesNegros[2] = pn3;
		arrayPeonesNegros[3] = pn4;
		arrayPeonesNegros[4] = pn5;
		arrayPeonesNegros[5] = pn6;
		arrayPeonesNegros[6] = pn7;
		arrayPeonesNegros[7] = pn8;
		
		arrayTorresBlancas[0] = tb1;
		arrayTorresBlancas[1] = tb2;
		arrayTorresNegras[0] = tn1;
		arrayTorresNegras[1] = tn2;
		
		arrayCaballosBlancos[0] = cb1;
		arrayCaballosBlancos[1] = cb2;
		arrayCaballosNegros[0] = cn1;
		arrayCaballosNegros[1] = cn2;
		
		arrayAlfilesBlancos[0] = ab1;
		arrayAlfilesBlancos[1] = ab2;
		arrayAlfilesNegros[0] = an1;
		arrayAlfilesNegros[1] = an2;
		
		for (int i=0; i<=7; i++){
			
			arrayPeonesBlancos[i].setId("pb"+(i+1));
			arrayPeonesBlancos[i].setColor(Color.BLANCO);
			colPiezas.add(arrayPeonesBlancos[i]);
		}
		
		for (int i=0; i<=7; i++){
			
			arrayPeonesNegros[i].setId("pn"+(i+1));
			arrayPeonesNegros[i].setColor(Color.NEGRO);
			colPiezas.add(arrayPeonesNegros[i]);	
		}
		
		for (int i=0; i<=1; i++){
			
			arrayTorresBlancas[i].setId("tb"+(i+1));
			arrayTorresBlancas[i].setColor(Color.BLANCO);
			colPiezas.add(arrayTorresBlancas[i]);
		}
		
		for (int i=0; i<=1; i++){
			arrayTorresNegras[i].setId("tn"+(i+1));
			arrayTorresNegras[i].setColor(Color.NEGRO);
			colPiezas.add(arrayTorresNegras[i]);
		}
		
		for (int i=0; i<=1; i++){
			
			arrayCaballosBlancos[i].setId("cb"+(i+1));
			arrayCaballosBlancos[i].setColor(Color.BLANCO);
			colPiezas.add(arrayCaballosBlancos[i]);
		}
		
		for (int i=0; i<=1; i++){
			arrayCaballosNegros[i].setId("cn"+(i+1));
			arrayCaballosNegros[i].setColor(Color.NEGRO);
			colPiezas.add(arrayCaballosNegros[i]);
		}
		
		for (int i=0; i<=1; i++){
			arrayAlfilesBlancos[i].setId("ab"+(i+1));
			arrayAlfilesBlancos[i].setColor(Color.BLANCO);
			colPiezas.add(arrayAlfilesBlancos[i]);
		}	
			
		for (int i=0; i<=1; i++){
		arrayAlfilesNegros[i].setId("an"+(i+1));
			arrayAlfilesNegros[i].setColor(Color.NEGRO);
			colPiezas.add(arrayAlfilesNegros[i]);
		}
		
		
		rb.setId("rb");
		rb.setColor(Color.BLANCO);
		colPiezas.add(rb);
		rn.setId("rn");
		rn.setColor(Color.NEGRO);
		colPiezas.add(rn);
		
		db.setId("db");
		db.setColor(Color.BLANCO);
		colPiezas.add(db);
		dn.setId("dn");
		dn.setColor(Color.NEGRO);
		colPiezas.add(dn);
		
	
	}
	
	public void asignarPosicIniciales() {
		
		//peones blancos
		this.getColPiezas().get(0).setPosicion("a2");
		this.getColPiezas().get(1).setPosicion("b2");
		this.getColPiezas().get(2).setPosicion("c2");
		this.getColPiezas().get(3).setPosicion("d2");
		this.getColPiezas().get(4).setPosicion("e2");
		this.getColPiezas().get(5).setPosicion("f2");
		this.getColPiezas().get(6).setPosicion("g2");
		this.getColPiezas().get(7).setPosicion("h2");
		
		//peones negros
		this.getColPiezas().get(8).setPosicion("a7");
		this.getColPiezas().get(9).setPosicion("b7");
		this.getColPiezas().get(10).setPosicion("c7");
		this.getColPiezas().get(11).setPosicion("d7");
		this.getColPiezas().get(12).setPosicion("e7");
		this.getColPiezas().get(13).setPosicion("f7");
		this.getColPiezas().get(14).setPosicion("g7");
		this.getColPiezas().get(15).setPosicion("h7");
		
		//torres blancas
		this.getColPiezas().get(16).setPosicion("a1");
		this.getColPiezas().get(17).setPosicion("h1");
		
		//torres negras
		this.getColPiezas().get(18).setPosicion("a8");
		this.getColPiezas().get(19).setPosicion("h8");
		
		//caballos blancos
		this.getColPiezas().get(20).setPosicion("b1");
		this.getColPiezas().get(21).setPosicion("g1");
		
		//caballos negros
		this.getColPiezas().get(22).setPosicion("b8");
		this.getColPiezas().get(23).setPosicion("g8");
		
		//alfiles blancos
		this.getColPiezas().get(24).setPosicion("c1");
		this.getColPiezas().get(25).setPosicion("f1");
		
		//alfiles negros
		this.getColPiezas().get(26).setPosicion("c8");
		this.getColPiezas().get(27).setPosicion("f8");
		
		//rey blanco
		this.getColPiezas().get(28).setPosicion("e1");
		
		//rey negro
		this.getColPiezas().get(29).setPosicion("e8");
		
		//dama blanca
		this.getColPiezas().get(30).setPosicion("d1");
		
		//dama negra
		this.getColPiezas().get(31).setPosicion("d8");	
			
		}
		
	public void peonesPosicIniciales(){
		
		Peon pb1 = new Peon(), pb2 = new Peon(), pb3 = new Peon(), pb4 = new Peon(), 
			 pb5 = new Peon(), pb6 = new Peon(), pb7 = new Peon(), pb8 = new Peon(),
			 pn1 = new Peon(), pn2 = new Peon(), pn3 = new Peon(), pn4 = new Peon(), 
		     pn5 = new Peon(), pn6 = new Peon(), pn7 = new Peon(), pn8 = new Peon();
		
		Peon[] arrayPeonesBlancos = new Peon[8];
		Peon[] arrayPeonesNegros = new Peon[8];
		
		arrayPeonesBlancos[0] = pb1;
		arrayPeonesBlancos[1] = pb2;
		arrayPeonesBlancos[2] = pb3;
		arrayPeonesBlancos[3] = pb4;
		arrayPeonesBlancos[4] = pb5;
		arrayPeonesBlancos[5] = pb6;
		arrayPeonesBlancos[6] = pb7;
		arrayPeonesBlancos[7] = pb8;	
		arrayPeonesNegros[0] = pn1;
		arrayPeonesNegros[1] = pn2;
		arrayPeonesNegros[2] = pn3;
		arrayPeonesNegros[3] = pn4;
		arrayPeonesNegros[4] = pn5;
		arrayPeonesNegros[5] = pn6;
		arrayPeonesNegros[6] = pn7;
		arrayPeonesNegros[7] = pn8;
		
		for (int i=0; i<=7; i++){
			
			arrayPeonesBlancos[i].setId("pb"+(i+1));
			arrayPeonesBlancos[i].setColor(Color.BLANCO);
			colPeones.add(arrayPeonesBlancos[i]);
		}
		
		for (int i=0; i<=7; i++){
			
			arrayPeonesNegros[i].setId("pn"+(i+1));
			arrayPeonesNegros[i].setColor(Color.NEGRO);
			colPeones.add(arrayPeonesNegros[i]);	
		}
		
		
		//peones blancos
		((Peon)this.getColPeones().get(0)).setPosicionInicial("a2");
		((Peon)this.getColPeones().get(1)).setPosicionInicial("b2");
		((Peon)this.getColPeones().get(2)).setPosicionInicial("c2");
		((Peon)this.getColPeones().get(3)).setPosicionInicial("d2");
		((Peon)this.getColPeones().get(4)).setPosicionInicial("e2");
		((Peon)this.getColPeones().get(5)).setPosicionInicial("f2");
		((Peon)this.getColPeones().get(6)).setPosicionInicial("g2");
		((Peon)this.getColPeones().get(7)).setPosicionInicial("h2");
		
		//peones negros
		((Peon)this.getColPeones().get(8)).setPosicionInicial("a7");
		((Peon)this.getColPeones().get(9)).setPosicionInicial("b7");
		((Peon)this.getColPeones().get(10)).setPosicionInicial("c7");
		((Peon)this.getColPeones().get(11)).setPosicionInicial("d7");
		((Peon)this.getColPeones().get(12)).setPosicionInicial("e7");
		((Peon)this.getColPeones().get(13)).setPosicionInicial("f7");
		((Peon)this.getColPeones().get(14)).setPosicionInicial("g7");
		((Peon)this.getColPeones().get(15)).setPosicionInicial("h7");
		
	}
	
	public Color getTurno() {
		return turno;
	}

	public void setTurno(Color turno) {
		this.turno = turno;
	}

	public ArrayList<Pieza> getColPiezas() {
		return colPiezas;
	}

	public void setColPiezas(ArrayList<Pieza> colPiezas) {
		this.colPiezas = colPiezas;
	}
	
	public ArrayList<Peon> getColPeones() {
		return colPeones;
	}

	public void setColPeones(ArrayList<Peon> colPeones) {
		this.colPeones = colPeones;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Jugador getJugadorB() {
		return jugadorB;
	}
	
	public void setJugadorB(Jugador JugadorB) {
		this.jugadorB = JugadorB;
	}
	
	public Jugador getJugadorN() {
		return jugadorN;
	}
	
	public void setJugadorN(Jugador JugadorN) {
		this.jugadorN = JugadorN;
	}
	
	
	public Pieza instanciarPieza(Pieza piezaIndefinida, String idPieza) {
		//recibe una pieza de la coleccion y la instancia 
		
		Pieza piezaDefinida = null;
		
		if(piezaIndefinida instanceof Peon) piezaDefinida = new Peon(); 
		if(piezaIndefinida instanceof Torre) piezaDefinida = new Torre(); 
		if(piezaIndefinida instanceof Caballo) piezaDefinida = new Caballo(); 
		if(piezaIndefinida instanceof Alfil) piezaDefinida = new Alfil(); 
		if(piezaIndefinida instanceof Dama) piezaDefinida = new Dama(); 
		if(piezaIndefinida instanceof Rey) piezaDefinida = new Rey(); 
		
		piezaDefinida.setId(idPieza);
		return piezaDefinida;
		
	}
}
	
	
	

