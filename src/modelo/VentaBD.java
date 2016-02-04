package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;

public class VentaBD {
		
	public VentaBD(){

	}

	public boolean insertarVenta(Venta venta, ArrayList<DetalleVenta> arregloDetalle) {
		Connection c = null;
		CallableStatement cst = null;
		boolean respuesta = false;
		try {
			// ESTABLECIMIENTO DE CONEXION
			c = ConexionUtility.getConexion();
			
			// DESACTIVACION DE COMMIT AUTOMATICO
			c.setAutoCommit(false);
			
			// SE PREPARA LA CONSULTA
			String consulta = "{ call insertar_venta( ?, ?, ? ) }";
			cst = c.prepareCall(consulta);
			
			// PARAMETROS DE SALIDA
			cst.registerOutParameter(1, Types.INTEGER); // codigo de cliente
			cst.registerOutParameter(2, Types.DATE); // fecha
			cst.registerOutParameter(3, Types.INTEGER); // codigo de venta
			
			// PARAMETROS DE ENTRADA
			cst.setInt(1, venta.getCodigoCliente());
	
			// LLAMADO A PROCEDIMIENTO
			if (cst.executeUpdate() == 0){
				
				// TOMO CODIGO VENTA GENERADO
				int codigoVenta = cst.getInt(3);
				
				// INSERTO ESE MISMO CODIGO EN CADA DETALLE VENTA
				boolean salir = false; // se activa cuando falla inserción en DetalleVenta
				Iterator<DetalleVenta> it = arregloDetalle.iterator(); // para iterar en while
				DetalleVentaBD detalleVentaBD = new DetalleVentaBD(); // para llamar método insertarDetalleVenta
				while (it.hasNext() && !salir){
					DetalleVenta detalleVenta = it.next();
					detalleVenta.setCodigoVenta(codigoVenta);
					respuesta = detalleVentaBD.insertarDetalleVenta(detalleVenta, c);
					if (!respuesta) { // si respuesta da false salgo del while.
						salir = true;
					}
				}
				// PREGUNTO SI SALE POR FIN DE ARREGLO O POR 'SALIR'
				if (!salir) { 
					c.commit();//Se confirma transacción.
					respuesta = true;
				} else {
					ConexionUtility.cancelarCommit(c); 
				}
			} else {
				ConexionUtility.cancelarCommit(c); //Se vuelve a estado inicial.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// CLAUSURA DE CONEXION
			ConexionUtility.cerrarConexion(c);
			ConexionUtility.cerrarCst(cst);
		}
		return respuesta;
	}

	public ArrayList<Venta> getVentas(){
		
		ArrayList<Venta> arregloVentas = new ArrayList<Venta>();
		Connection c = null;
		Statement statement = null;
		ResultSet rset = null;
		
		try {
		// ESTABLECIMIENTO DE CONEXIÓN
		c = ConexionUtility.getConexion();
		
		// CONSULTA
		String consulta = "select * from obtener_ventas";
			statement = c.createStatement();
			rset = statement.executeQuery(consulta);
			
			while (rset.next()){
				Venta venta = new Venta();
				venta.setCodigoVenta(rset.getInt(1));
				venta.setCodigoCliente(rset.getInt(2));
				venta.setFecha(rset.getTimestamp(3));
				arregloVentas.add(venta);
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
		
		return arregloVentas;
	}
	
	public ArrayList<DetalleVenta> getHistoricoVentas() {
		ArrayList<DetalleVenta> arregloDetalleVenta = new ArrayList<DetalleVenta>();
		Connection c = null;
		Statement statement = null;
		ResultSet rset = null;
		try {
			// ESTABLECIMIENTO DE CONEXIÓN
			c = ConexionUtility.getConexion();
			
			// CONSULTA
			String consulta = "select * from obtener_detalle_ventas";
			statement = c.createStatement();
			rset = statement.executeQuery(consulta);
			
			while (rset.next()){
				DetalleVenta detalleVenta = new DetalleVenta();
				detalleVenta.setCodigoVenta(rset.getInt("codigo_venta"));
				detalleVenta.setCodigoProducto(rset.getInt("codigo_producto"));
				detalleVenta.setCantidad(rset.getInt("cantidad"));
				detalleVenta.setDescuento(rset.getDouble("descuento"));
				arregloDetalleVenta.add(detalleVenta);
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
		
		return arregloDetalleVenta;
	}

}
