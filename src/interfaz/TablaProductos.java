package interfaz;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import carrito.*;

public class TablaProductos extends AbstractTableModel{

	private String[] nombreColumnas;
	//private ProductoBD productoBD;
	private ArrayList<Producto> arregloProductos;
	
	public TablaProductos(ArrayList<Producto> a){
		super();
		//this.productoBD = productoBD;
		arregloProductos = a;//productoBD.getProductos();
		String[] columnas = { "Código", "Descripción", "Precio" };
		nombreColumnas = columnas;
	}
	
	public int getColumnCount() {
		return nombreColumnas.length;
	}

	public int getRowCount() {
		return arregloProductos.size();
	}

	public Object getValueAt(int fila, int columna) {
		Object datoProducto = null;
        Producto producto = arregloProductos.get(fila);

        if( columna == -1 )
            datoProducto = producto;
        else if( columna == 0 )
            datoProducto = producto.getCodigoProducto();
        else if( columna == 1 )
            datoProducto = producto.getDescripcion();
        else if( columna == 2 )
            datoProducto = producto.getPrecio();

        return datoProducto;
	}
	
	public String getColumnName(int columna){
        return nombreColumnas[columna];
    }
	
	public boolean isCellEditable(int fila, int columna){
        return false;
    }

	public void actualizarTabla(ArrayList<Producto> p){
		//arregloProductos = productoBD.getProductos();
		arregloProductos = p;
		fireTableDataChanged();
	}

}
