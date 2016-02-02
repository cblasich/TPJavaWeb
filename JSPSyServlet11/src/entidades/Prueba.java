package entidades;

public class Prueba {

	public static void main(String[] args) {
		/*int a = -1;
		int b = 1;
		int c = a+b;
		System.out.println(+c);
		
		/*Partida p = new Partida();
		p.asignarPosicIniciales();
		String posOrigen = "e1"; 
		
		boolean origenValido = false, 
				destinoValido = false;
		Partida pActualizada = null;
		Pieza piezaAMover = null;
		
		for (Pieza pieza : p.getColPiezas()) {
			
			if((pieza.getPosicion().equals(posOrigen))) {
				//if((pieza.getPosicion().equals(posOrigen))) {} 
				//origen es valido si alli hay pieza de color = color turno
				
				origenValido = true; 
				
				if(pieza instanceof Peon) piezaAMover = new Peon(); 
				if(pieza instanceof Torre) piezaAMover = new Torre(); 
				if(pieza instanceof Caballo) piezaAMover = new Caballo(); 
				if(pieza instanceof Alfil) piezaAMover = new Alfil(); 
				if(pieza instanceof Dama) piezaAMover = new Dama(); 
				if(pieza instanceof Rey) piezaAMover = new Rey(); 
				
				break;
			}
		}
		
		if(posOrigen.equals("e1"))
		System.out.println("La pieza a mover es de tipo" + piezaAMover.getClass().toString());
		
		/*Partida p = new Partida();
		p.asignarPosicIniciales();
		String posOrigen = "e1"; 
		Pieza piezaAMover = null;
		
		for(Pieza pieza : p.getColPiezas()) {
			
			if((pieza.getPosicion().equals(posOrigen))) {
				
				if(pieza instanceof Peon) piezaAMover = new Peon(); 
				if(pieza instanceof Torre) piezaAMover = new Torre(); 
				if(pieza instanceof Caballo) piezaAMover = new Caballo(); 
				if(pieza instanceof Alfil) piezaAMover = new Alfil(); 
				if(pieza instanceof Dama) piezaAMover = new Dama(); 
				if(pieza instanceof Rey) piezaAMover = new Rey(); 
			} 
		} 
		System.out.println(piezaAMover.getClass());
		
		/*Partida p = new Partida();
		p.asignarPosicIniciales();
		String posOrigen = "d1"; 
		int a = 3;
		
Pieza piezaAMover = null;
		
		//este if valida SOLAMENTE que en la posicion de origen haya una pieza cuyo color = color del turno
		//y que la posicion de destino esté vacía o haya una pieza del color contrario
		
		
		if (posOrigen.equals("d1")) {  //HASTA ACÁ LLEGA. Switch NO probado.
			
			switch(a)
			{
			case 1: piezaAMover = p.newPeon(); 
					break;
					
			case 2: piezaAMover = p.newTorre();
					break;
					
			case 3: piezaAMover = p.newCaballo();
					break;
					
			case 4: piezaAMover = p.newAlfil();
					break;
					
			case 5: piezaAMover = p.newDama();
					break;
					
			case 6: piezaAMover = p.newRey();
					break;
					
			default: System.out.println("Hola mundo");
			}
		}
			
			System.out.println(piezaAMover.getClass());
			
			
			
		/*int idAInstanciar = 0;
		
		for(Pieza pieza : p.getColPiezas()) {
			
			if((pieza.getPosicion().equals(posOrigen))) {
				
				if(pieza instanceof Peon) idAInstanciar = 1; 
				if(pieza instanceof Torre) idAInstanciar = 2;
				if(pieza instanceof Caballo) idAInstanciar = 3;
				if(pieza instanceof Alfil) idAInstanciar = 4;
				if(pieza instanceof Dama) idAInstanciar = 5;
				if(pieza instanceof Rey) idAInstanciar = 6;
			} 
		} 
		
		System.out.println(idAInstanciar);
		
		/*
		String posDestino = "";
		Color turno;
		turno = Color.BLANCO;
		
		boolean origenValido = false, destinoValido = false;
		
		for (Pieza pieza : p.getColPiezas()) {
			
			if((pieza.getPosicion().equals(posOrigen)) && pieza.getColor().equals(turno)) 
				origenValido = true; 
			
		}
		
		for (Pieza pieza : p.getColPiezas()) {
			
			if(origenValido) System.out.println("origen valido yes");
			}
		
		
		/*Partida p = new Partida();
		p.asignarPosicIniciales();
		
		String posOrigen = "a2";
		
		for (Pieza pieza : p.getColPiezas()) {
			
			if((pieza.getPosicion().equals(posOrigen))) 
				{ 	System.out.println("Valido");
					System.out.println(pieza.getId());
				
				}
			
		}
				
				
	
		/*Color tu1, tu2, tu3;
		tu1 = Color.BLANCO;
		tu2 = Color.NEGRO;
		tu3 = Color.BLANCO;
		
		if(tu1.equals(tu3)) System.out.println("Son mismo color");
		
		if(!(tu1.equals(tu2))) System.out.println("Son distinto color"); 
		
		
		
		/*Peon pb1 = new Peon();
		Peon pb2 = new Peon(), pb3 = new Peon(), pb4 = new Peon(), 
				pb5 = new Peon(), pb6 = new Peon(), pb7 = new Peon(), pb8 = new Peon(), pn1 = new Peon(), pn2 = new Peon(), pn3 = new Peon(), pn4 = new Peon(), pn5 = new Peon(), pn6 = new Peon(), pn7 = new Peon(), pn8 = new Peon();
		Pieza[] arrayPeonesBlancos = new Peon[8];
		Pieza[] arrayPeonesNegros = new Peon[8];
		
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
			System.out.println(arrayPeonesBlancos[i].getId());
			
		}
		
		String posJava = new String();
		int j=0;
		String posTab = new String();
		posTab = "h5";
		
		//POR QUE NO SE PUEDE DECLARAR COMO PRIVATE A letraNum?
		
		final String[][] letraNum = {{"a","0"},{"b","1"},{"c","2"},{"d","3"},
				{"e","4"},{"f","5"},{"g","6"},{"h","7"}};
		
		for (int i=0 ; i<8; i++) { 
			
			
			if( String.valueOf(posTab.charAt(0)).equals(letraNum[i][j] )) {
				
				int num =  Math.abs((Integer.parseInt(String.valueOf(posTab.charAt(1))) - 1) - 7);
				
				String numPos = Integer.toString(num);
				
				posJava = numPos + letraNum[i][j+1];
	
				
			}
			
			
			
		}
		
		System.out.println(posJava); */
	}
} 
