package modelo;

import java.sql.*;
import java.util.ArrayList;

public class ClienteBD {
		
	public ClienteBD(){
		
	}
	
	public boolean insertarCliente(Cliente cliente) {
		
		Connection c = null;
		CallableStatement cst = null;
		boolean respuesta = false; 

		try {
			// ESTABLECIMIENTO DE CONEXION
			c = ConexionUtility.getConexion();
			
			// DESACTIVACION DE COMMIT AUTOMATICO
			c.setAutoCommit(false);
			
			//SE PREPARA LA CONSULTA
			String consulta = "{ call insertar_cliente( ?, ? ) }";
			cst = c.prepareCall(consulta);
			
			//PARAMETROS DE SALIDA
			cst.registerOutParameter(1, Types.VARCHAR); //nombre
			cst.registerOutParameter(2, Types.INTEGER); //codigo

			//PARAMETROS DE ENTRADA
			cst.setString(1, cliente.getNombre());
			
			// LLAMADO A PROCEDIMIENTO
			// executeUpdate es 0 cuando no se realizan updates como es el caso actual de insercion.
			if (cst.executeUpdate() == 0){ 
				c.commit(); //Se confirma transacción.
				respuesta = true;
			} else {
				ConexionUtility.cancelarCommit(c);
			}
		} catch (SQLException e) {
			System.err.println("Cliente BD - Insertar Cliente - SQL");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Cliente BD - Insertar Cliente - NO SQL");
			e.printStackTrace();
		} finally {
			// CLAUSURA DE CONEXION
			ConexionUtility.cerrarConexion(c);
			ConexionUtility.cerrarCst(cst);
		}
		return respuesta;
	}
	public ArrayList<Cliente> getClientes(){
			
			ArrayList<Cliente> arregloClientes = new ArrayList<Cliente>();
			Connection c = null;
			Statement statement = null;
			ResultSet rset = null;
			
			try {
			// ESTABLECIMIENTO DE CONEXIÓN
			c = ConexionUtility.getConexion();
			
			// CONSULTA
			String consulta = "select * from obtener_clientes";
			statement = c.createStatement();
			rset = statement.executeQuery(consulta);
			
			while (rset.next()){
				Cliente cliente = new Cliente();
				cliente.setCodigoCliente(rset.getInt(1));
				cliente.setNombre(rset.getString(2));	
				arregloClientes.add(cliente);
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionUtility.cerrarConexion(c);
			ConexionUtility.cerrarStmt(statement);
			ConexionUtility.cerrarRset(rset);
		}
		
		return arregloClientes;
	}
}
