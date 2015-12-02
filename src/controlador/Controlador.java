package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import carrito.Producto;
import carrito.ProductoBD;
import vista.InterfazVista;

public class Controlador implements ActionListener {

	private InterfazVista vista;

	public Controlador(InterfazVista vista) {
		this.vista=vista;
	}

	public void actionPerformed(ActionEvent evento){
		String accion = evento.getActionCommand();
		if (accion.equals(InterfazVista.INSERTAR_PRODUCTO)){
			vista.abrirFormulario(accion,this);
			vista.setValoresDefectoProducto("AUTOGENERADO", "", "");
		}else if(accion.equals(InterfazVista.ACTUALIZAR_PRODUCTO)){
			//OBTENGO Y VALIDO EL ITEM QUE SE DESEA ACTUALIZAR.
			Producto p = null;
			try {
				p = (Producto) vista.getItemSeleccionado(accion);
			}catch(Exception exc){
				vista.errorValidacion("Debe Seleccionar un Producto", "Seleccionar Producto");
			}
			if (p != null){
				vista.abrirFormulario(accion, this);
				vista.setValoresDefectoProducto(Integer.toString(p.getCodigoProducto()),p.getDescripcion(),Double.toString(p.getPrecio()));
			}
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
		String descripcion = vista.getDescripcionProducto();
		String precio = vista.getPrecioProducto();
   		// VALIDO Y AGREGO PRODUCTO A BD
		if (validarProducto(vista.INSERTAR_PRODUCTO,descripcion,precio) 
				&& insertarProductoBD(descripcion,Double.parseDouble(precio))){
			vista.operacionCorrecta("Producto Agregado");
			vista.cerrarFormulario(vista.INSERTAR_PRODUCTO);
		}
		
	}
	
	private void confirmarActualizarProducto() {
			// Se toman los datos ingresados
		String descripcion = vista.getDescripcionProducto();
		String precio = vista.getPrecioProducto();
			// Chequeo validez de los datos
		if (this.validarProducto(vista.ACTUALIZAR_PRODUCTO, descripcion, precio)){
			// Como no permito editar Código, no realizo validacion.
			int codigo = Integer.parseInt(vista.getCodigoProducto());
			Double precioParseado = Double.parseDouble(precio);
			// Si todo salió bien se actualiza producto en BD.
			if (actualizarProductoBD(codigo, descripcion, precioParseado)){
				vista.operacionCorrecta("Producto Actualizado");
				vista.cerrarFormulario(vista.ACTUALIZAR_PRODUCTO);
			}
		}

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

	public boolean insertarProductoBD(String descripcion, Double precio){
		Producto p = new Producto();
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		ProductoBD productoBD = new ProductoBD();
		if (productoBD.insertarProducto(p)){
			vista.actualizarProductos(productoBD.getProductos());
			return true;
		} else return false;
	}

	private boolean validarProducto(String operacion, String descripcion, String precio){
		if( descripcion == null || descripcion.equals("")){
	        vista.errorValidacion("La descripcion no puede quedar vacía", operacion);
	        return false;
	    }
		Double precioParseado = null;
		try{
	        precioParseado = Double.parseDouble(precio);
	    } catch (Exception excepcion){
	        vista.errorValidacion("El precio no es un valor válido", operacion);
	        return false;
	    }
		if( precioParseado <= 0 ){
	        vista.errorValidacion("El precio debe ser mayor a cero", operacion);
	        return false;
	    }else return true;
	}
}
