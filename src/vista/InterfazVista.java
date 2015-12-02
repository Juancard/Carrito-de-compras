package vista;

import java.util.ArrayList;
import java.util.List;

import carrito.Producto;
import controlador.Controlador;

public interface InterfazVista {
	static final String INSERTAR_PRODUCTO = "Insertar Producto";
	static final String CONFIRMAR_INSERTAR_PRODUCTO = "Confirmar Insertar Producto";
	static final String CANCELAR_INSERTAR_PRODUCTO = "Cancelar Insertar Producto";
	
	static final String ACTUALIZAR_PRODUCTO = "Actualizar Producto";
	static final String CONFIRMAR_ACTUALIZAR_PRODUCTO = "Confirmar Actualizar Producto";
	static final String CANCELAR_ACTUALIZAR_PRODUCTO = "Cancelar Actualizar Producto";

	public void setControlador(Controlador c);
	
	public void abrirFormulario(String nombre, Controlador c);
	public void cerrarFormulario(String nombre);
	public void actualizarProductos(ArrayList<Producto> productos);
	
	public void errorValidacion(String causaError, String tituloError);
	public void operacionCorrecta(String operacion);
	
	public Object getItemSeleccionado(String operacion);
	public void setValoresDefectoProducto(String codigo, String descripcion, String precio);
	
	public String getDescripcionProducto();
	public String getPrecioProducto();
	public String getCodigoProducto();
}
