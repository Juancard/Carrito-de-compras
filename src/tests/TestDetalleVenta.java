package tests;

import modelo.DetalleVenta;
import modelo.Producto;
import junit.framework.Assert;

public class TestDetalleVenta {

	@org.junit.Test
	public void testDetalleVentaCodigoVenta() {
		DetalleVenta dv = new DetalleVenta();	
		int codigoVenta = 3;
		dv.setCodigoVenta(codigoVenta);
		Assert.assertEquals(codigoVenta, dv.getCodigoVenta());	
	}
	@org.junit.Test
	public void testDetalleVentaCodigoProducto() {
		int codigoProducto = 21;
		DetalleVenta dv = new DetalleVenta();
		dv.setCodigoProducto(codigoProducto);
		Assert.assertEquals(codigoProducto, dv.getCodigoProducto());
	}
	@org.junit.Test
	public void testDetalleVentaCantidad() {
		DetalleVenta dv = new DetalleVenta();

		int cantidad = 5;

		dv.setCantidad(cantidad);
		Assert.assertEquals(cantidad, dv.getCantidad());
	}
	@org.junit.Test
	public void testDetalleVentaAgregarCantidad() {
		DetalleVenta dv = new DetalleVenta();

		int cantidad = 5;

		dv.agregarCantidad(cantidad);
		Assert.assertEquals(cantidad, dv.getCantidad());
	}
	@org.junit.Test
	public void testDetalleVentaDescuento() {
			
		DetalleVenta dv = new DetalleVenta();

		double descuento = 10;
		
		dv.setDescuento(descuento);
		Assert.assertEquals(descuento, dv.getDescuento(),0.01);		
	}
	@org.junit.Test
	public void testDetalleVentaPrecioUnitario() {
			
		DetalleVenta dv = new DetalleVenta();

		double precioUnitario = 100.00;
		
		dv.setPrecioUnitario(precioUnitario);
		Assert.assertEquals(precioUnitario, dv.getPrecioUnitario(),0.01);		
	}
	@org.junit.Test
	public void testDetalleVentaAgregarDescuento() {
			
		DetalleVenta dv = new DetalleVenta();

		double descuento = 0.10;
		
		dv.agregarDescuento(descuento);
		Assert.assertEquals(descuento, dv.getDescuento(),0.01);		
	}
	@org.junit.Test
	public void testDetalleVentaProducto() {
		DetalleVenta dv = new DetalleVenta();
		
		Producto p = new Producto();
		int codigoProducto = 1;
		p.setCodigoProducto(codigoProducto);
		
		dv.setProducto(p);
		Assert.assertEquals(codigoProducto, dv.getProducto().getCodigoProducto());
	}
	public void testDetalleVentaSubtotal() {
		
		int codigoVenta = 10; 
		int codigoProducto = 11;
		int cantidad = 2;
		double descuento = 20;
		double precio = 100;
		double subtotal = (precio * cantidad) - descuento;
		
		DetalleVenta dv = new DetalleVenta(codigoVenta, codigoProducto, cantidad, descuento);
		
		Producto p = new Producto();
		p.setCodigoProducto(codigoProducto);
		p.setPrecio(precio);
		
		dv.setProducto(p);
		
		Assert.assertEquals(subtotal, dv.getSubtotal(),0.01);	
	}
	public void testDetalleVentaConstructor() {
		
		int codigoVenta = 10;
		int codigoProducto = 11;
		int cantidad = 1;
		double descuento = 4.33;
		
		DetalleVenta dv = new DetalleVenta(codigoVenta, codigoProducto, cantidad, descuento);
				
		Assert.assertEquals(codigoVenta, dv.getCodigoVenta());
		Assert.assertEquals(codigoProducto, dv.getCodigoProducto());
		Assert.assertEquals(cantidad, dv.getCantidad());
		Assert.assertEquals(descuento, dv.getDescuento(),0.01);		
	}
	
}
