package vista;

import java.util.ArrayList;

import carrito.Cliente;
import carrito.DetalleVenta;
import carrito.Producto;
import controlador.ControladorCarrito;
import controlador.ControladorCliente;
import controlador.ControladorProducto;

public interface InterfazVista {
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
	
	
	// SETEAR CONTROLADORES
	public void setControladorProducto(ControladorProducto controladorProducto);
	public void setControladorCliente(ControladorCliente controladorCliente);
	public void setControladorCarrito(ControladorCarrito controladorCarrito);
	
	// FORMULARIOS
	public void abrirFormularioCarrito(String operacion, ControladorCarrito c);
	public void abrirFormularioCliente(String operacion, ControladorCliente c);
	public void abrirFormularioProducto(String operacion, ControladorProducto c);

	public void cerrarFormulario(String nombre);
		
	public void errorOperacion(String causaError, String tituloError);
	public void operacionCorrecta(String operacion);
	
	public Object getItemSeleccionado(String operacion);
	public boolean isItemSeleccionado(String operacion);
	
	//METODOS DE PRODUCTO
	public void setValoresDefectoProducto(String codigo, String descripcion, String precio);
	public String getTextDescripcionProducto();
	public String getTextPrecioProducto();
	public String getTextCodigoProducto();
	
	//METODOS DE CLIENTE
	public void setValoresDefectoCliente(String codigo, String nombre);
	public String getTextNombreCliente();
	
	//METODOS DE CARRITO
	public void setValoresDefectoCarrito(String descuento, String cantidad);
	public String getTextDescuentoCarrito();
	public String getTextCantidadCarrito();
	public void setTotal(String total);
	
	
	//METODOS DE TABLAS
	public void setearCarrito(ArrayList<DetalleVenta> itemsCarrito);
	public void setearProductos(ArrayList<Producto> productos);
	public void setearClientes(ArrayList<Cliente> clientes);
	
	public void actualizarProductos(ArrayList<Producto> productos);
	public void actualizarCarrito();
	public void actualizarClientes(ArrayList<Cliente> nuevaLista);
}
