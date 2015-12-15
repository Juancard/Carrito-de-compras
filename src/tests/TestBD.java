package tests;

import java.util.ArrayList;

import modelo.Cliente;
import modelo.ClienteBD;
import modelo.DetalleVenta;
import modelo.Producto;
import modelo.ProductoBD;
import modelo.Venta;
import modelo.VentaBD;
import junit.framework.Assert;


public class TestBD {
	
	/*
	 * ¡ATENCION!:
	 * 
	 * No se recomienda realizar JUnit Test Case sobre 
	 * Base de datos ya que genera cambios en la misma
	 * lo que contradice la política de este tipo de testeos.
	 * 
	 * Correr siguientes tests bajo precaución.
	 * 
	 * 
	 * 
	 * Nota: 
	 * 
	 * Testeos no comentados no generan cambios
	 * en base de datos pero tampoco garantizan el 
	 * correcto funcionamiento de clases testeadas
	 */
	
	/*
	 * Comienzo de testeos:
	 */
	
	
	/*
	//CLIENTE BD
	@org.junit.Test
	public void testClienteBDInsertar() {
		// Genero objeto Cliente y lo cargo.
		Cliente clienteCargar = new Cliente();
		clienteCargar.setNombre("Mandala Abdul");
		
		//Genero objeto ClienteBD
		ClienteBD clienteBD = new ClienteBD();
		
		Assert.assertEquals(true, clienteBD.insertarCliente(clienteCargar));

	}
	*/
	@org.junit.Test
	public void testClienteBDObtenerTodosClientes() {
		ClienteBD clienteBD = new ClienteBD();
		ArrayList<Cliente> arregloClientes = clienteBD.getClientes();
		Assert.assertNotNull(arregloClientes);
	}
	
	/*
	//PRODUCTO BD
	@org.junit.Test
	public void testProductoBDInsertar() {
		// Genero objeto Producto y lo cargo.
		Producto productoCargar = new Producto();
		productoCargar.setDescripcion("El Aleph");
		productoCargar.setPrecio(99.99);
		
		//Genero objeto ProductoBD
		ProductoBD productoBD = new ProductoBD();
		Assert.assertEquals(true, productoBD.insertarProducto(productoCargar));
	}
	*/
	/*
	@org.junit.Test
	public void testProductoBDActualizar() {
		// Genero objeto Producto y lo cargo.
		Producto productoCargar = new Producto();
		productoCargar.setCodigoProducto(1);
		productoCargar.setDescripcion("Rayuela - Edicion limitada");
		productoCargar.setPrecio(449.90);
		
		//Genero objeto ProductoBD
		ProductoBD productoBD = new ProductoBD();
		Assert.assertEquals(true, productoBD.actualizarProducto(productoCargar));
	}
	*/
	@org.junit.Test
	public void testProductoBDObtenerTodosProductos() {
		ProductoBD productoBD = new ProductoBD();
		ArrayList<Producto> arregloProductos = productoBD.getProductos();
		Assert.assertNotNull(arregloProductos);
	}
	@org.junit.Test
	public void testProductoBDObtenerProducto() {
		ProductoBD productoBD = new ProductoBD();
		int codigoProducto = 11;
		Assert.assertNotNull(productoBD.obtenerProducto(codigoProducto));
	}
	/*
	//Detalle Venta BD
	@org.junit.Test
	public void testDetalleVentaBDInsertar() {
		// Genero objeto DetalleVenta y lo cargo.
		DetalleVenta detalleVenta = new DetalleVenta();
		int codigoVenta = 5;
		int codigoProducto = 10;
		int cantidad = 2;
		double descuento = 5.5;
		double precioUnitario = 100.20;
		detalleVenta.setCodigoVenta(codigoVenta);
		detalleVenta.setCodigoProducto(codigoProducto);
		detalleVenta.setCantidad(cantidad);
		detalleVenta.setDescuento(descuento);
		detalleVenta.setPrecioUnitario(precioUnitario);
		
		//Genero objeto DetalleVentaBD y realizo prueba
		DetalleVentaBD detalleBD = new DetalleVentaBD();
		Assert.assertEquals(true, detalleBD.insertarDetalleVenta(detalleVenta));
	}
	*/
	/*
	@org.junit.Test
	public void testVentaBDInsertar() {
		// Genero objeto Venta y lo cargo.
		Venta venta = new Venta();
		int codigoCliente = 6;
		venta.setCodigoCliente(codigoCliente);
		
		//Genero objeto VentaBD
		VentaBD ventaBD = new VentaBD();
		Assert.assertEquals(true, ventaBD.insertarVenta(venta));
	}
	*/
	/*
	@org.junit.Test
	public void testVentaBDInsertar() {
		// Genero objeto Venta y lo cargo.
		Venta venta = new Venta();
		int codigoCliente = 2;
		venta.setCodigoCliente(codigoCliente);
		
		//Armo arreglo de DetalleVenta y lo cargo.
		ArrayList<DetalleVenta> arreglo = new ArrayList<DetalleVenta>();
		int cantProductosPedidos = 2;
		for (int i = 1; i<= cantProductosPedidos; i++){
			DetalleVenta detalleVenta = new DetalleVenta();
			detalleVenta.setCodigoProducto(i + 2);
			detalleVenta.setCantidad(1);
			detalleVenta.setDescuento(2);
			arreglo.add(detalleVenta);
		}
		
		//Genero objeto VentaBD
		VentaBD ventaBD = new VentaBD();
		Assert.assertEquals(true, ventaBD.insertarVenta(venta, arreglo));
	}
	*/
	@org.junit.Test
	public void testVentaBDObtenerVentas() {
		VentaBD ventaBD = new VentaBD();
		ArrayList<Venta> arregloVentas = ventaBD.getVentas();
		Assert.assertNotNull(arregloVentas);
	}
	
	@org.junit.Test
	public void testVentaBDObtenerHistoricoVentas() {
		VentaBD ventaBD = new VentaBD();
		ArrayList<DetalleVenta> arregloDetalleVenta = ventaBD.getHistoricoVentas();
		Assert.assertNotNull(arregloDetalleVenta);
	}

}
