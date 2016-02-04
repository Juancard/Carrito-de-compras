package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class DetalleVentaBD {
	
	public DetalleVentaBD(){

	}
	
	public boolean insertarDetalleVenta(DetalleVenta detalleVenta, Connection c){
		CallableStatement cst = null;
		boolean respuesta = false;
		
		try {			
			// SE PREPARA LA CONSULTA
			String consulta = "{ call insertar_detalleventa( ?, ?, ?, ?, ? ) }";
			cst = c.prepareCall(consulta);
			
			// PARAMETROS DE SALIDA
			cst.registerOutParameter(1, Types.INTEGER); // codigo de venta
			cst.registerOutParameter(2, Types.INTEGER); // codigo de producto
			cst.registerOutParameter(3, Types.INTEGER); // cantidad
			cst.registerOutParameter(4, Types.NUMERIC);	// descuento
			cst.registerOutParameter(5, Types.NUMERIC);	// precio unitario


			// PARAMETROS DE ENTRADA
			cst.setInt(1, detalleVenta.getCodigoVenta());
			cst.setInt(2, detalleVenta.getCodigoProducto());
			cst.setInt(3, detalleVenta.getCantidad());
			cst.setBigDecimal(4, new java.math.BigDecimal(detalleVenta.getDescuento())); // Se castea double a numeric.
			cst.setBigDecimal(5, new java.math.BigDecimal(detalleVenta.getPrecioUnitario())); // Se castea double a numeric.
			
			// LLAMADO A PROCEDIMIENTO
			if (cst.executeUpdate() == 0){
				c.commit(); //Se confirma transacción.
				respuesta = true;
			} else {
				ConexionUtility.cancelarCommit(c); //Se vuelve a estado inicial.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// CLAUSURA DE CALL (no de la conexión)
			ConexionUtility.cerrarCst(cst);
		}
		return respuesta;
	}
}
