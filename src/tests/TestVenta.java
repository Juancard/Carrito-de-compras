package tests;

import java.sql.Timestamp;
import java.util.Date;

import modelo.Cliente;
import modelo.Venta;
import junit.framework.Assert;

public class TestVenta {

	@org.junit.Test
	public void testVentaCodigoVenta() {
		Venta v = new Venta();
		int codigoVenta = 1;
		v.setCodigoVenta(codigoVenta);
		Assert.assertEquals(codigoVenta, v.getCodigoVenta());
	}
	@org.junit.Test
	public void testVentaCodigoCliente() {
		Venta v = new Venta();
		int codigoCliente = 2;
		v.setCodigoCliente(codigoCliente);
		Assert.assertEquals(codigoCliente, v.getCodigoCliente());
	}
	public void testVentaFecha() {
		Venta v = new Venta();
		
		//Para recuperar fecha de hoy:
		// 1) Creo instancia de clase Date
		Date d = new Date();
	    // 2) getTime() devuelve la fecha de hoy en milisegundos
		long milisegundos = d.getTime();
		// 3) Paso la variable resultante al constructor de Timestamps:
		Timestamp fecha = new Timestamp(milisegundos);
		// Para visualizar el resultado:
		// System.out.println("Fecha Actual: " + fecha);
		
		v.setFecha(fecha);
		Assert.assertEquals(fecha, v.getFecha());
	}	
	@org.junit.Test
	public void testVentaCliente() {
					
		int codigoCliente = 12;
		Cliente cliente = new Cliente();
		cliente.setCodigoCliente(codigoCliente);
		
		Venta v = new Venta();
		v.setCliente(cliente);
		
		Assert.assertEquals(codigoCliente, v.getCliente().getCodigoCliente());
	}
	@org.junit.Test
	public void testVentaConstructor() {
					
		int codigoVenta = 1;
		int codigoCliente = 2;
		
		Date d = new Date();
		long milisegundos = d.getTime();
		Timestamp fecha = new Timestamp(milisegundos);
		
		Venta v = new Venta(codigoVenta, codigoCliente, fecha);
		
		Assert.assertEquals(codigoVenta, v.getCodigoVenta());
		Assert.assertEquals(codigoCliente, v.getCodigoCliente());
		Assert.assertEquals(fecha, v.getFecha());
	}
	
}
