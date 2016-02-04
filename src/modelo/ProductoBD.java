package modelo;
import java.sql.*;
import java.util.ArrayList;

public class ProductoBD {
		
	public ProductoBD() {

	}
	
	public boolean insertarProducto(Producto p) {
		
		Connection c = null;
		CallableStatement cst = null;
		boolean respuesta = false;
		
		try {
			// ESTABLECIMIENTO DE CONEXION
			c = ConexionUtility.getConexion();
			
			// DESACTIVACION DE COMMIT AUTOMATICO
			c.setAutoCommit(false);
			
			// SE PREPARA LA CONSULTA
			String consulta = "{ call insertar_producto( ?, ?, ? ) }";
			cst = c.prepareCall(consulta);
			
			// PARAMETROS DE SALIDA
			cst.registerOutParameter(1, Types.VARCHAR); // descripcion
			cst.registerOutParameter(2, Types.NUMERIC);	// precio
			cst.registerOutParameter(3, Types.INTEGER); // codigo de producto

			// PARAMETROS DE ENTRADA
			cst.setString(1, p.getDescripcion());
			cst.setBigDecimal(2, new java.math.BigDecimal(p.getPrecio())); // Se castea double a numeric.
			
			// LLAMADO A PROCEDIMIENTO
			if (cst.executeUpdate() == 0){
				c.commit(); //Se confirma transacción.
				respuesta = true;
			} else {
				ConexionUtility.cancelarCommit(c); //Se vuelve a estado inicial.
			}
		} catch (SQLException e) {
			System.err.println("Producto BD - Insertar Cliente - SQL");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Producto BD - Insertar Cliente - NO SQL");
			e.printStackTrace();
		} finally {
			// CLAUSURA DE CONEXION
			ConexionUtility.cerrarConexion(c);
			ConexionUtility.cerrarCst(cst);
		}
		return respuesta;
	}
	
	public boolean actualizarProducto(Producto producto){
		Connection c = null;
		CallableStatement cst = null;
		boolean respuesta = false;
		
		try {
			// ESTABLECIMIENTO DE CONEXIÓN
			c = ConexionUtility.getConexion();
			c.setAutoCommit(false);
			
			// CONSULTA
			String consulta = "{ call actualizar_producto(?, ?, ?)}";
			cst = c.prepareCall(consulta);
			
			// PARAMETROS DE SALIDA
			cst.registerOutParameter(1, Types.INTEGER);	// codigo de producto		
			cst.registerOutParameter(2, Types.VARCHAR); // descripcion
			cst.registerOutParameter(3, Types.NUMERIC); // precio
			
			// PARAMETROS DE ENTRADA
			cst.setInt(1, producto.getCodigoProducto());
			cst.setString(2, producto.getDescripcion());
			cst.setBigDecimal(3, new java.math.BigDecimal(producto.getPrecio()));

			// LLAMADO A PROCEDIMIENTO
			if (cst.executeUpdate() == 0){
				c.commit(); //Se confirma transacción.
				respuesta = true;
			} else {
				ConexionUtility.cancelarCommit(c); //Se vuelve a estado inicial.
			}
						
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionUtility.cerrarConexion(c);
			ConexionUtility.cerrarCst(cst);
		}
		return respuesta;
	}
	
	public ArrayList<Producto> getProductos(){
		
		ArrayList<Producto> arregloProductos = new ArrayList<Producto>();
		Connection c = null;
		Statement statement = null;
		ResultSet rset = null;
		
		try {
			// ESTABLECIMIENTO DE CONEXIÓN
			c = ConexionUtility.getConexion();
			
			// CONSULTA
			String consulta = "select * from obtener_productos";
			statement = c.createStatement();
			rset = statement.executeQuery(consulta);
			
			while (rset.next()){
				Producto p = new Producto();
				p.setCodigoProducto(rset.getInt(1));
				p.setDescripcion(rset.getString(2));	
				p.setPrecio(rset.getDouble(3));
				arregloProductos.add(p);
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
		
		return arregloProductos;
	}
	
	public Producto obtenerProducto(int codigoProducto){
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rset = null;
		Producto p = new Producto();
		
		try {
			// ESTABLECIMIENTO DE CONEXIÓN
			c = ConexionUtility.getConexion();
			
			// CONSULTA
			String consulta = "select * from obtener_producto(?)";
			pst = c.prepareStatement(consulta);
			
			// PARAMETROS DE ENTRADA
			pst.setInt(1, codigoProducto);
			rset = pst.executeQuery();
			
			if (rset.next()) {
	            p.setCodigoProducto(rset.getInt(1));
	            p.setDescripcion(rset.getString(2));
	            p.setPrecio(rset.getDouble(3));
			}
						
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionUtility.cerrarConexion(c);
			ConexionUtility.cerrarPreparedStmt(pst);
			ConexionUtility.cerrarRset(rset);
		}
		return p;
	}
}
