package interfaz;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import carrito.CarritoCompras;
import carrito.DetalleVenta;

public class TablaCarrito extends AbstractTableModel{

	private String[] nombreColumnas;
	private CarritoCompras carrito;
	
	public TablaCarrito(CarritoCompras carrito){
		super();
		this.carrito = carrito;
		String[] columnas = {"Cod. Prod.","Descripcion","Precio Unit.","Cantidad","Desc. Total","Subtotal"};
		nombreColumnas = columnas;
	}
	
	public int getColumnCount() {
		return nombreColumnas.length;
	}

	public int getRowCount() {
		return carrito.getCarrito().size();
	}

	public Object getValueAt(int fila, int columna) {
		Object objeto = null;
		
		Object[] arregloObjetos = carrito.getCarrito().toArray();
		DetalleVenta dv = (DetalleVenta)arregloObjetos[fila];
		
		if (columna == -1)
            objeto = dv;
        else if (columna == 0)
            objeto = dv.getCodigoProducto();
        else if (columna == 1)
        	objeto = dv.getProducto().getDescripcion();
        else if (columna == 2)
            objeto = dv.getProducto().getPrecio();
        else if (columna == 3)
            objeto = dv.getCantidad();
        else if (columna == 4)
            objeto = dv.getDescuento();
        else if (columna == 5)
        	objeto = dv.getSubtotal();

		return objeto;
	}
	
	public String getColumnName(int columna){
        return nombreColumnas[columna];
    }
	
	public boolean isCellEditable(int fila, int columna){
        return false;
    }
	
	public void actualizarTabla(){
		fireTableDataChanged();
	}

}
