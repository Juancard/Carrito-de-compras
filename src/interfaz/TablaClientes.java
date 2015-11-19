package interfaz;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import carrito.Cliente;
import carrito.ClienteBD;

public class TablaClientes extends AbstractTableModel{

	private ClienteBD clienteBD;
	private String[] nombreColumnas;
	private ArrayList<Cliente> arregloClientes;

	public TablaClientes(ClienteBD clienteBD) {
		super();
		this.clienteBD = clienteBD;
		this.arregloClientes = clienteBD.getClientes();
		String[] columnas = {"Código","Nombre"};
		this.nombreColumnas = columnas;
	}

	public int getColumnCount() {
		return nombreColumnas.length;
	}

	public int getRowCount() {
		return arregloClientes.size();
	}

	public Object getValueAt(int fila, int columna) {
		Object datoProducto = null;
        Cliente cliente = arregloClientes.get(fila);

        if( columna == -1 )
            datoProducto = cliente;
        else if( columna == 0 )
            datoProducto = cliente.getCodigoCliente();
        else if( columna == 1 )
            datoProducto = cliente.getNombre();

        return datoProducto;
	}
	

	public String getColumnName(int columna){
        return nombreColumnas[columna];
    }
	
	public boolean isCellEditable(int fila, int columna){
        return false;
    }
	
	public void actualizarTabla(){
		arregloClientes = clienteBD.getClientes();
		fireTableDataChanged();
	}

}
