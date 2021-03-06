package datos;

import java.sql.*;

import entidades.Jugador;

public class CatalogoJugadores {
	
	
	public void agregarJugador(Jugador j)
	{
		
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
	
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into jugadores (dni, nombre, apellido) values (?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS
				   );
			stmt.setInt(1, j.getDni());
			stmt.setString(2, j.getNombre());
			stmt.setString(3, j.getApellido());
			stmt.execute();

			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			try {
				if(rs!=null ) rs.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			FactoryConexion.getInstancia().releaseConn();
		}
	}
	
	public Jugador BuscarEnCatJug(int dni)
	{
		
		ResultSet rs=null;
		PreparedStatement stmt=null;
		Jugador j=null;
		try {
			stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
					"select dni, nombre, apellido from jugadores where dni = ?"
					);
			stmt.setInt(1, dni);
			rs = stmt.executeQuery();  //para consulta
			if(rs !=null && rs.next()){
				j=new Jugador();
				j.setDni(rs.getInt("dni"));
				j.setNombre(rs.getString("nombre"));
				j.setApellido(rs.getString("apellido"));
				
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
		return j;
	}
	

}
