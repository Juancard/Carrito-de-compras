package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Producto;
import modelo.ProductoBD;
import vista.InterfazVista;
import vista.TablaModel;
import vista.VentanaProducto;

public class ControladorProducto implements ActionListener {

	private InterfazVista vista;
	private VentanaProducto ventanaProducto;
	private TablaModel<Producto> tablaProductos;

	public ControladorProducto(InterfazVista v) {
		vista = v;
		setearControlador();
		setearTabla();
	}

	private void setearTabla() {
		// Traigo de la base los productos
		ProductoBD productoBD = new ProductoBD();
		tablaProductos = new TablaModel<Producto>("modelo.Producto",productoBD.getProductos());
		vista.setearProductos(tablaProductos);
	}

	private void setearControlador() {
		vista.setControladorProducto(this);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		String accion = evento.getActionCommand();
		//INSERTAR PRODUCTO
		if (accion.equals(InterfazVista.INSERTAR_PRODUCTO)){
			abrirFormulario(accion);
		//ACTUALIZAR PRODUCTO
		}else if(accion.equals(InterfazVista.ACTUALIZAR_PRODUCTO)){
			if (vista.isItemSeleccionado(accion)){
				abrirFormulario(accion);
			} else vista.errorOperacion("Debe Seleccionar un Producto", accion);
		}
		
		//ACCIONES DE FORMULARIOS
		if (accion.equals(InterfazVista.CONFIRMAR_INSERTAR_PRODUCTO)){
			confirmarInsertarProducto();
		} else if(accion.equals(InterfazVista.CANCELAR_INSERTAR_PRODUCTO)){
	    	cerrarFormulario();
		} else if(accion.equals(InterfazVista.CONFIRMAR_ACTUALIZAR_PRODUCTO)){
			confirmarActualizarProducto();
		} else if(accion.equals(InterfazVista.CANCELAR_ACTUALIZAR_PRODUCTO)){
			cerrarFormulario();
		}	
	}

	private void abrirFormulario(String operacion) {
		if (operacion.equals(vista.INSERTAR_PRODUCTO)){
			ventanaProducto = new VentanaProducto(operacion);
			ventanaProducto.setActionCommand(vista.CONFIRMAR_INSERTAR_PRODUCTO,vista.CANCELAR_INSERTAR_PRODUCTO);
			ventanaProducto.setControlador(this);
			setValoresDefecto("AUTOGENERADO","","");
			ventanaProducto.setVisible(true);
		}else if(operacion.equals(vista.ACTUALIZAR_PRODUCTO)){
			ventanaProducto = new VentanaProducto(vista.ACTUALIZAR_PRODUCTO);
			ventanaProducto.setControlador(this);
			ventanaProducto.setActionCommand(vista.CONFIRMAR_ACTUALIZAR_PRODUCTO,vista.CANCELAR_ACTUALIZAR_PRODUCTO);
			// Pido item seleccionado y lo cargo en formulario
			Producto p = (Producto) vista.getItemSeleccionado(operacion);
			if (p != null){
				setValoresDefecto(Integer.toString(p.getCodigoProducto()),p.getDescripcion(),Double.toString(p.getPrecio()));
			}
			ventanaProducto.setVisible(true);
		}
	}
	
	private void cerrarFormulario() {
		ventanaProducto.dispose();
	}

	private void setValoresDefecto(String codigo, String descripcion, String precio) {
		ventanaProducto.setTxtCodigo(codigo);
		ventanaProducto.setTxtDescripcion(descripcion);
		ventanaProducto.setTxtPrecio(precio);
	}

	private void confirmarInsertarProducto() {
		String descripcion = ventanaProducto.getTxtDescripcion();
		String precio = ventanaProducto.getTxtPrecio();
   		// VALIDO Y AGREGO PRODUCTO A BD
		if (validarProducto(vista.INSERTAR_PRODUCTO,descripcion,precio) 
				&& insertarProductoBD(descripcion,Double.parseDouble(precio))){
			vista.operacionCorrecta("Producto Agregado");
			cerrarFormulario();
		}
		
	}
	
	private void confirmarActualizarProducto() {
			// Se toman los datos ingresados
		String descripcion = ventanaProducto.getTxtDescripcion();
		String precio = ventanaProducto.getTxtPrecio();
			// Chequeo validez de los datos
		if (this.validarProducto(vista.ACTUALIZAR_PRODUCTO, descripcion, precio)){
			// Como no permito editar Código, no realizo validacion.
			int codigo = Integer.parseInt(ventanaProducto.getTxtCodigo());
			Double precioParseado = Double.parseDouble(precio);
			// Si todo salió bien se actualiza producto en BD.
			if (actualizarProductoBD(codigo, descripcion, precioParseado)){
				vista.operacionCorrecta("Producto Actualizado");
				cerrarFormulario();
			}
		}
	}
	
	private boolean insertarProductoBD(String descripcion, Double precio){
		Producto p = new Producto();
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		ProductoBD productoBD = new ProductoBD();
		if (productoBD.insertarProducto(p)){
			actualizarTabla();
			return true;
		} else return false;
	}

	private boolean actualizarProductoBD(int codigo, String descripcion, Double precio) {
		Producto p = new Producto();
		p.setCodigoProducto(codigo);
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		ProductoBD productoBD = new ProductoBD();
		if (productoBD.actualizarProducto(p)){
			actualizarTabla();
			return true;
		} else return false;
	}
	
	private void actualizarTabla() {
		ProductoBD productoBD = new ProductoBD();
		tablaProductos.actualizarTabla(productoBD.getProductos());
	}

	private boolean validarProducto(String operacion, String descripcion, String precio){
		if( descripcion == null || descripcion.equals("")){
	        vista.errorOperacion("La descripcion no puede quedar vacía", operacion);
	        return false;
	    }
		Double precioParseado = null;
		try{
	        precioParseado = Double.parseDouble(precio);
	    } catch (Exception excepcion){
	        vista.errorOperacion("El precio no es un valor válido", operacion);
	        return false;
	    }
		if( precioParseado <= 0 ){
	        vista.errorOperacion("El precio debe ser mayor a cero", operacion);
	        return false;
	    }else return true;
	}
}
