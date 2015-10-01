package carrito;

import java.sql.*;

public class Conexion {
	
	// DRIVER DE JDBC Y URL
	static final String JDBC_DRIVER = "org.postgresql.Driver";  
	static final String DB_URL = "jdbc:postgresql://192.168.1.105:5432/carrito";

	// CREDENCIALES DE USUARIO
	static final String USUARIO = "postgres";
	static final String CONTRASEÑA = "postgres";
	
	public Connection getConexion(){
		Connection connection = null;
		try {
			// DRIVER DE JDBC PARA POSTGRESQL
			Class.forName(JDBC_DRIVER);

			// ESTABLECE CONEXION
			connection = DriverManager.getConnection(DB_URL, USUARIO, CONTRASEÑA);
		} catch(SQLException se){
			//Manejo de errores para JDBC
			System.err.println("En Excepcion de JDBC");
			se.printStackTrace();
		} catch(Exception e){
		    //Manejo de errores para Class.forName
			System.err.println("En Excepcion de Class.forName");
		    e.printStackTrace();
		} finally {
			return connection;
		}
	}
	
	
	/*
	 * En los sucesivos métodos se evita NullPointerException 
	 * para que no pise la Exception que me interesa detectar. 
	 */

	
	public void cerrarConexion(Connection connection){
		if (connection != null){ 
			try {connection.close();} 
			catch (SQLException e) {e.printStackTrace();}
		}
	}
	
    public void cancelarCommit(Connection c) {
    	if (c != null) {
            try{c.rollback();}
            catch (SQLException e){e.printStackTrace();}
    	}
    }
	
	public void cerrarRset(ResultSet rset){
		if (rset != null){ 
			try {rset.close();} 
			catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	public void cerrarStmt(Statement stmt){
		if (stmt != null){ 
			try {stmt.close();} 
			catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	public void cerrarCst(CallableStatement cst){
		if (cst != null){ 
			try {cst.close();} 
			catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	
	public void cerrarPreparedStmt(PreparedStatement pst){
		if (pst != null){ 
			try {pst.close();} 
			catch (SQLException e) {e.printStackTrace();}
		}
	}

}
