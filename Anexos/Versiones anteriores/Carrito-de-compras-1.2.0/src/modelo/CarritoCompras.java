package modelo;

import java.util.ArrayList;

public class CarritoCompras {

	public final int NO_ENCONTRADO = -1; // IndexOf devuelve -1 cuando no encuentra elemento en arreglo.
	
	private ArrayList<DetalleVenta> arregloCarrito;
	
	public CarritoCompras(){
		arregloCarrito = new ArrayList<DetalleVenta>();
	}
	
	public void agregarItem(DetalleVenta dv){
		int codigoProducto = dv.getProducto().getCodigoProducto();
		if (this.productoEnCarrito(codigoProducto)){
			this.agregarCantidad(codigoProducto, dv.getCantidad());
			this.agregarDescuento(codigoProducto, dv.getDescuento());
		} else{
			dv.setCodigoProducto(codigoProducto);
			arregloCarrito.add(dv);
		}
	}
	
	public ArrayList<DetalleVenta> getCarrito(){
		return arregloCarrito;
	}
	
	public void borrarCarrito(){
		arregloCarrito.clear();
	}
	
	public void borrarItem(int codigoProducto){
		int index = getIndex(codigoProducto);
		if (index != NO_ENCONTRADO){
			arregloCarrito.remove(index);
		}
	}
	
	public boolean productoEnCarrito(int codigoProducto){
		return getIndex(codigoProducto) != NO_ENCONTRADO; 
	}
	
	public void agregarCantidad(int codigoProducto, int cantidad){
		DetalleVenta dv = getDetalleVenta(codigoProducto);
		if (dv != null)
			dv.agregarCantidad(cantidad);
	}
	
	public void agregarDescuento(int codigoProducto, double descuento){
		DetalleVenta dv = getDetalleVenta(codigoProducto);
		if (dv != null)
			dv.agregarDescuento(descuento);
	}
	
	
	public double getTotalCarrito(){
		double total = 0;
		for (DetalleVenta dv : arregloCarrito){
			total += dv.getSubtotal();
		}
		return total;
	}
	
	public DetalleVenta getDetalleVenta(int codigoProducto){
		DetalleVenta dv = null;
		int index = getIndex(codigoProducto);
		if (index != NO_ENCONTRADO){
			dv = arregloCarrito.get(index);
		}	
		return dv;
	}

		
	private int getIndex(int codigoProducto){
		DetalleVenta dv = new DetalleVenta();
		dv.setCodigoProducto(codigoProducto);
		return arregloCarrito.indexOf(dv);
	}
	
}
