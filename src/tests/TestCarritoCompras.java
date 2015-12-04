package tests;

import junit.framework.Assert;
import modelo.CarritoCompras;
import modelo.DetalleVenta;
import modelo.Producto;

import org.junit.Test;

public class TestCarritoCompras {

	@Test
	public void testAgregarItem() {
		Producto p = new Producto();

		int codigoProducto = 1;
		p.setCodigoProducto(codigoProducto);	
		DetalleVenta dv = new DetalleVenta();
		dv.setProducto(p);
		
		
		CarritoCompras carrito = new CarritoCompras();
		carrito.agregarItem(dv);
		
		Assert.assertTrue(carrito.productoEnCarrito(codigoProducto));
		Assert.assertFalse(carrito.productoEnCarrito(codigoProducto + 1));
	}

	@Test
	public void testAgregarCantidad() {
		Producto p = new Producto();

		int codigoProducto = 2;
		int cantidad = 1;
		p.setCodigoProducto(codigoProducto);
		
		DetalleVenta dv = new DetalleVenta();
		dv.setProducto(p);
		dv.setCantidad(cantidad);
		
		CarritoCompras carrito = new CarritoCompras();
		carrito.agregarItem(dv);
		
		int cantidadAgregada = 2;
		int cantidadTotal = cantidad + cantidadAgregada;
		
		carrito.agregarCantidad(codigoProducto, cantidadAgregada);
		
		Assert.assertEquals(carrito.getDetalleVenta(codigoProducto).getCantidad(), cantidadTotal);
	}
	
	@Test
	public void testAgregarDescuento() {
		Producto p = new Producto();

		int codigoProducto = 3;
		double descuento = 1;
		p.setCodigoProducto(codigoProducto);
		
		DetalleVenta dv = new DetalleVenta();
		dv.setProducto(p);
		dv.setDescuento(descuento);
		
		CarritoCompras carrito = new CarritoCompras();
		carrito.agregarItem(dv);
		
		double descuentoAgregado = 2;
		double descuentoTotal = descuento + descuentoAgregado;
		
		carrito.agregarDescuento(codigoProducto, descuentoAgregado);
						
		Assert.assertEquals(carrito.getDetalleVenta(codigoProducto).getDescuento(), descuentoTotal, 0.01);
	}
	
	@Test
	public void testBorrarItem() {
		Producto p = new Producto();

		int codigoProducto = 7;
		p.setCodigoProducto(codigoProducto);
		
		DetalleVenta dv = new DetalleVenta();
		dv.setProducto(p);
		
		CarritoCompras carrito = new CarritoCompras();
		carrito.agregarItem(dv);
		
		carrito.borrarItem(codigoProducto);
		
		Assert.assertFalse(carrito.productoEnCarrito(codigoProducto));
	}
	
	@Test
	public void testBorrarCarrito() {
		Producto p = new Producto();
		
		int codigoProducto = 200;
		p.setCodigoProducto(codigoProducto);
		
		DetalleVenta dv = new DetalleVenta();
		dv.setProducto(p);
		
		CarritoCompras carrito = new CarritoCompras();
		carrito.agregarItem(dv);
		
		carrito.borrarCarrito();
		
		Assert.assertTrue(carrito.getCarrito().isEmpty());
	}

}
