package carrito;

public class Producto {
	
    private int codigoProducto;
    private String descripcion;
    private double precio;

    public Producto() {
    }

    public Producto(int codigoProducto, String descripcion, double precio) {
        this.codigoProducto = codigoProducto;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
