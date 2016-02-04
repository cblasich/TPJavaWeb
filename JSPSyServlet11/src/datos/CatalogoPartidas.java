package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Color;
import entidades.Partida;
import entidades.Pieza;

import java.util.ArrayList;

public class CatalogoPartidas {
	
	public void agregarPartida(Partida p)
	{
		
		ResultSet rs=null;
		PreparedStatement stmt=null;
		PreparedStatement stmt1=null;
		
	
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into partidas (dni_Blancas, dni_Negras, turno) values (?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS
				   );
			stmt.setInt(1, (p.getJugadorB()).getDni()); 
			stmt.setInt(2, (p.getJugadorN()).getDni());
			stmt.setString(3, "BLANCO");
			stmt.execute();

			rs=stmt.getGeneratedKeys();  //clave autoincremental "id"
			
			if(rs!=null && rs.next()){
				p.setId(rs.getInt(1));
			}
			
			
			
			for (Pieza pieza : p.getColPiezas()) {
				stmt1 = FactoryConexion.getInstancia().getConn().prepareStatement(
						"insert into piezas (id_Pieza, posicion, id_partida, color) values (?,?,?,?)");
				
				stmt1.setString(1, pieza.getId());
				stmt1.setString(2, pieza.getPosicion());
				stmt1.setInt(3, p.getId());
				if (pieza.getColor().equals(Color.BLANCO)) stmt1.setString(4, "BLANCO");
				else stmt1.setString(4, "NEGRO");
				stmt1.execute();
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			try {
				if(rs!=null ) rs.close();
				if(stmt != null) stmt.close();
				if(stmt1 != null) stmt1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			FactoryConexion.getInstancia().releaseConn();
		}
	}
	
	
	public Partida BuscarEnCatPar(int dniB, int dniN)
	{
		
		ResultSet rs=null;
		PreparedStatement stmt=null;
		Partida p=null;
		try {
			stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_Partida, dni_Blancas, dni_Negras, turno from partidas where dni_Blancas = ? AND dni_Negras = ?"
					);
			stmt.setInt(1, dniB);
			stmt.setInt(2, dniN);
			rs = stmt.executeQuery();  //para consulta
			
			if(rs !=null && rs.next()){
				p = new Partida();
				(p.getJugadorB()).setDni(rs.getInt("dni_Blancas"));
				(p.getJugadorN()).setDni(rs.getInt("dni_Negras"));
				p.setId(rs.getInt("id_Partida"));
				if(rs.getString("turno").equals("BLANCO")){ p.setTurno(Color.BLANCO);}
					else p.setTurno(Color.NEGRO);
			} ;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null) stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FactoryConexion.getInstancia().releaseConn();
		}
		return p;
	}
	
	public Partida BuscarEnCatPar(Partida oldPartida) { 
		
		ResultSet rs=null;
		PreparedStatement stmt=null;
		try {
			
			
			stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_Pieza, posicion, color from piezas where id_Partida = ?");
			stmt.setInt(1, oldPartida.getId());
			rs = stmt.executeQuery();
			
			while(rs !=null && rs.next()){
			   for (Pieza pieza : oldPartida.getColPiezas()){
				  
				   if (pieza.getId().equals((rs.getString("id_Pieza")))) pieza.setPosicion(rs.getString("posicion"));
				   
				   }
					
				}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null) stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FactoryConexion.getInstancia().releaseConn();
		}
		return oldPartida;
	}
	
	
	public void update(Partida pActual){
				
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"update piezas SET posicion = ? WHERE id_Partida = ? and id_Pieza = ?" );
			
			for (Pieza pieza : pActual.getColPiezas()) {
			
			
			stmt.setString(1, pieza.getPosicion());
			stmt.setInt(2, pActual.getId());
			stmt.setString(3, pieza.getId());
			stmt.executeUpdate();
			
			}
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"update partidas SET turno = ? WHERE id_Partida = ?" );
			
			stmt.setString(1, pActual.getTurno().toString());
			stmt.setInt(2, pActual.getId());
			stmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null) stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FactoryConexion.getInstancia().releaseConn();
		}
		
	
		
	}	
	
	public void eliminarPartida(Partida pActual){
		
		PreparedStatement stmt=null;
		
		try {
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"delete from partidas WHERE id_Partida = ?" );
			
			stmt.setInt(1, pActual.getId());
			stmt.execute();
			
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"delete from piezas WHERE id_Partida = ?" );
			
			stmt.setInt(1, pActual.getId());
			stmt.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
		finally
		{
			try {
				if(stmt!=null) stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FactoryConexion.getInstancia().releaseConn();
		}
		
		
	}

}