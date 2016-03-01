package vista;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import modelo.DetalleVenta;

public class TablaCarrito extends AbstractTableModel{

	private String[] nombreColumnas;
	private ArrayList<DetalleVenta> lista;
	
	public TablaCarrito(ArrayList<DetalleVenta> lista){
		super();
		this.lista=lista;
		String[] columnas = {"Cod. Prod.","Descripcion","Precio Unit.","Cantidad","Desc. Total","Subtotal"};
		nombreColumnas = columnas;
	}
	
	public int getColumnCount() {
		return nombreColumnas.length;
	}

	public int getRowCount() {
		return lista.size();
	}

	public Object getValueAt(int fila, int columna) {
		Object objeto = null;
		DetalleVenta dv = lista.get(fila);
		
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

}
