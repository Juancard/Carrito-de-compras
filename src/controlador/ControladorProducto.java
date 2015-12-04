package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Producto;
import modelo.ProductoBD;
import vista.InterfazVista;

public class ControladorProducto implements ActionListener {

	private InterfazVista vista;

	public ControladorProducto(InterfazVista v) {
		vista = v;
		setearControlador();
		setearTabla();
	}

	private void setearTabla() {
		// Traigo de la base los productos
		ProductoBD productoBD = new ProductoBD();
		vista.setearProductos(productoBD.getProductos());
	}

	private void setearControlador() {
		vista.setControladorProducto(this);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		String accion = evento.getActionCommand();
		//INSERTAR PRODUCTO
		if (accion.equals(InterfazVista.INSERTAR_PRODUCTO)){
			vista.abrirFormularioProducto(accion,this);
			vista.setValoresDefectoProducto("AUTOGENERADO", "", "");
		//ACTUALIZAR PRODUCTO
		}else if(accion.equals(InterfazVista.ACTUALIZAR_PRODUCTO)){
			if (vista.isItemSeleccionado(accion)){
				Producto p = (Producto) vista.getItemSeleccionado(accion);
				if (p != null){
					vista.abrirFormularioProducto(accion, this);
					vista.setValoresDefectoProducto(Integer.toString(p.getCodigoProducto()),p.getDescripcion(),Double.toString(p.getPrecio()));
				}
			} else vista.errorOperacion("Debe Seleccionar un Producto", accion);
		}
		
		//ACCIONES DE FORMULARIOS
		if (accion.equals(InterfazVista.CONFIRMAR_INSERTAR_PRODUCTO)){
			confirmarInsertarProducto();
		} else if(accion.equals(InterfazVista.CANCELAR_INSERTAR_PRODUCTO)){
	    	vista.cerrarFormulario(vista.INSERTAR_PRODUCTO);
		} else if(accion.equals(InterfazVista.CONFIRMAR_ACTUALIZAR_PRODUCTO)){
			confirmarActualizarProducto();
		} else if(accion.equals(InterfazVista.CANCELAR_ACTUALIZAR_PRODUCTO)){
			vista.cerrarFormulario(vista.ACTUALIZAR_PRODUCTO);
		}	
	}
	
	private void confirmarInsertarProducto() {
		String descripcion = vista.getTextDescripcionProducto();
		String precio = vista.getTextPrecioProducto();
   		// VALIDO Y AGREGO PRODUCTO A BD
		if (validarProducto(vista.INSERTAR_PRODUCTO,descripcion,precio) 
				&& insertarProductoBD(descripcion,Double.parseDouble(precio))){
			vista.operacionCorrecta("Producto Agregado");
			vista.cerrarFormulario(vista.INSERTAR_PRODUCTO);
		}
		
	}
	
	private void confirmarActualizarProducto() {
			// Se toman los datos ingresados
		String descripcion = vista.getTextDescripcionProducto();
		String precio = vista.getTextPrecioProducto();
			// Chequeo validez de los datos
		if (this.validarProducto(vista.ACTUALIZAR_PRODUCTO, descripcion, precio)){
			// Como no permito editar Código, no realizo validacion.
			int codigo = Integer.parseInt(vista.getTextCodigoProducto());
			Double precioParseado = Double.parseDouble(precio);
			// Si todo salió bien se actualiza producto en BD.
			if (actualizarProductoBD(codigo, descripcion, precioParseado)){
				vista.operacionCorrecta("Producto Actualizado");
				vista.cerrarFormulario(vista.ACTUALIZAR_PRODUCTO);
			}
		}
	}
	
	
	private boolean insertarProductoBD(String descripcion, Double precio){
		Producto p = new Producto();
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		ProductoBD productoBD = new ProductoBD();
		if (productoBD.insertarProducto(p)){
			vista.actualizarProductos(productoBD.getProductos());
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
			vista.actualizarProductos(productoBD.getProductos());
			return true;
		} else return false;
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
