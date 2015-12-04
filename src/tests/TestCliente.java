package tests;

import modelo.Cliente;
import junit.framework.Assert;

public class TestCliente {

	@org.junit.Test
	public void testClienteCodigo() {
		Cliente c = new Cliente();
		int codigoCliente = 1;		
		c.setCodigoCliente(codigoCliente);
		Assert.assertEquals(codigoCliente, c.getCodigoCliente());
	}
	@org.junit.Test
	public void testClienteNombre() {
		Cliente c = new Cliente();
		String nombre = "Raul Bioy";
		c.setNombre(nombre);
		Assert.assertEquals(nombre, c.getNombre());
	}
	@org.junit.Test
	public void testClienteConstructor() {
		int codigoCliente = 2;
		String nombre = "Maria Gimenez";
		Cliente c = new Cliente(codigoCliente, nombre);
			
		Assert.assertEquals(codigoCliente, c.getCodigoCliente());
		Assert.assertEquals(nombre, c.getNombre());
	}


}
