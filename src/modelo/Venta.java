package modelo;
import java.sql.Timestamp;

public class Venta {

    private int codigoVenta;
    private int codigoCliente;
    private Timestamp fecha;
    private Cliente cliente;

	public Venta() {
    }

    public Venta(int codigoVenta, int codigoCliente, Timestamp fecha) {
        this.codigoVenta = codigoVenta;
        this.codigoCliente = codigoCliente;
        this.fecha = fecha;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
