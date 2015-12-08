package vista;

import javax.swing.table.AbstractTableModel;

import controlador.ControladorCarrito;
import controlador.ControladorCliente;
import controlador.ControladorProducto;
import controlador.ControladorReporte;

public interface InterfazVista{
	static final String INSERTAR_PRODUCTO = "Insertar Producto";
	static final String CONFIRMAR_INSERTAR_PRODUCTO = "Confirmar Insertar Producto";
	static final String CANCELAR_INSERTAR_PRODUCTO = "Cancelar Insertar Producto";
	
	static final String ACTUALIZAR_PRODUCTO = "Actualizar Producto";
	static final String CONFIRMAR_ACTUALIZAR_PRODUCTO = "Confirmar Actualizar Producto";
	static final String CANCELAR_ACTUALIZAR_PRODUCTO = "Cancelar Actualizar Producto";
	
	static final String INSERTAR_CLIENTE = "Insertar Cliente";
	static final String CONFIRMAR_INSERTAR_CLIENTE = "Confirmar Insertar Cliente";
	static final String CANCELAR_INSERTAR_CLIENTE = "Cancelar Insertar Cliente";
	
	static final String AGREGAR_CARRITO = "Agregar al Carrito";
	static final String CONFIRMAR_AGREGAR_CARRITO = "Confirmar Agregar al Carrito";
	static final String CANCELAR_AGREGAR_CARRITO = "Cancelar Agregar al Carrito";
	
	static final String BORRAR_ITEM = "Borrar Item";
	static final String LIMPIAR_CARRITO = "Limpiar Carrito";
	static final String FINALIZAR_COMPRA = "Finalizar Compra";
	
	static final String HISTORICO_VENTAS = "Historico de Ventas";
	
	
	// SETEAR CONTROLADORES
	public void setControladorProducto(ControladorProducto controladorProducto);
	public void setControladorCliente(ControladorCliente controladorCliente);
	public void setControladorCarrito(ControladorCarrito controladorCarrito);
	public void setControladorReporte(ControladorReporte controladorReporte);
	
	public void setBotonActionCommand();
		
	// MENSAJES
	public void errorOperacion(String causaError, String tituloError);
	public void operacionCorrecta(String operacion);
	
	// ETIQUETA DE TOTAL
	public void setTotal(String total);
	
	//ITEMS SELECCIONADOS
	public Object getItemSeleccionado(String operacion);
	public boolean isItemSeleccionado(String operacion);
		
	//METODOS DE TABLAS
	public void setearCarrito(AbstractTableModel tablaCarrito);
	public void setearProductos(AbstractTableModel productos);
	public void setearClientes(AbstractTableModel tablaClientes);
}
