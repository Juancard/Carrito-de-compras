package modelo;


public class DetalleVenta {
	private int codigoVenta;
	private int codigoProducto;
	private int cantidad = 0;
	private double descuento = 0;
	private double precioUnitario;

	private Producto producto;
    //private Venta venta;	
	
	public DetalleVenta(){
		
	}
	
	public DetalleVenta(int codigoVenta, int codigoProducto, int cantidad, double descuento){
		this.codigoVenta = codigoVenta;
		this.codigoProducto = codigoProducto;
		this.cantidad = cantidad;
		this.descuento = descuento;
	}
	
	public int getCodigoVenta() {
		return codigoVenta;
	}
	public void setCodigoVenta(int codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
    public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public double getPrecioUnitario() {
		return this.precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario=precioUnitario;
	}
	public void agregarCantidad(int cantidad){
		this.cantidad += cantidad;
	}

	public void agregarDescuento(double descuento) {
		this.descuento += descuento;
	}
	
	public double getSubtotal(){
		double precio = producto.getPrecio();
		return cantidad * precioUnitario - descuento;
	}
	
	@Override
	public boolean equals (Object o){
		if (o != null && o instanceof DetalleVenta){
			boolean productoIgual = this.codigoProducto == ((DetalleVenta) o).getCodigoProducto();
			boolean ventaIgual = this.codigoVenta == ((DetalleVenta) o).getCodigoVenta();
			return productoIgual && ventaIgual;
		}
		else return false;
	}



}
