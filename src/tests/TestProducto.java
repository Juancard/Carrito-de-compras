package tests;

import modelo.Producto;
import junit.framework.Assert;

public class TestProducto {

	@org.junit.Test
	public void testProductoCodigo() {
		Producto p = new Producto();
		int codigoProducto = 1;
		p.setCodigoProducto(codigoProducto);
		Assert.assertEquals(codigoProducto, p.getCodigoProducto());
	}
	@org.junit.Test
	public void testProductoDescripcion() {
		Producto p = new Producto();
		String descripcion = "Ensayo sobre ciegos";
		p.setDescripcion(descripcion);
		Assert.assertEquals(descripcion, p.getDescripcion());
	}
	@org.junit.Test
	public void testProductoPrecio() {
		Producto p = new Producto();
		double precio = 80.00;
		p.setPrecio(precio);
		Assert.assertEquals(precio, p.getPrecio());
	}
	@org.junit.Test
	public void testProductoConstructor() {
		int codigoProducto = 2;
		String descripcion = "El tunel";
		double precio = 75.55;
		Producto p = new Producto(codigoProducto, descripcion, precio);
		Assert.assertEquals(codigoProducto, p.getCodigoProducto());
		Assert.assertEquals(descripcion, p.getDescripcion());
		Assert.assertEquals(precio, p.getPrecio());
	}

}
