package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import carrito.CarritoCompras;
import carrito.Cliente;
import carrito.ClienteBD;
import carrito.DetalleVenta;
import carrito.Producto;
import carrito.ProductoBD;
import carrito.Venta;
import carrito.VentaBD;
import vista.InterfazVista;

public class Controlador implements ActionListener {

	private InterfazVista vista;
	private CarritoCompras carrito;

	public Controlador(InterfazVista vista) {
		this.vista=vista;
		setearTablas();
	}

	private void setearTablas() {
		// Genero el carrito de compras
		CarritoCompras carro = new CarritoCompras();
		this.carrito=carro;
		vista.setearCarrito(carrito.getCarrito());
		// Traigo de la base los productos
		ProductoBD productoBD = new ProductoBD();
		vista.setearProductos(productoBD.getProductos());
		// Traigo de la base los clientes
		ClienteBD clienteBD = new ClienteBD();
		vista.setearClientes(clienteBD.getClientes());
	}

	public void actionPerformed(ActionEvent evento){
		String accion = evento.getActionCommand();
		//INSERTAR PRODUCTO
		if (accion.equals(InterfazVista.INSERTAR_PRODUCTO)){
			vista.abrirFormulario(accion,this);
			vista.setValoresDefectoProducto("AUTOGENERADO", "", "");
		//ACTUALIZAR PRODUCTO
		}else if(accion.equals(InterfazVista.ACTUALIZAR_PRODUCTO)){
			if (vista.isItemSeleccionado(accion)){
				Producto p = (Producto) vista.getItemSeleccionado(accion);
				if (p != null){
					vista.abrirFormulario(accion, this);
					vista.setValoresDefectoProducto(Integer.toString(p.getCodigoProducto()),p.getDescripcion(),Double.toString(p.getPrecio()));
				}
			} else vista.errorOperacion("Debe Seleccionar un Producto", accion);
		//INSERTAR CLIENTE
		}else if(accion.equals(InterfazVista.INSERTAR_CLIENTE)){
			vista.abrirFormulario(accion,this);
			vista.setValoresDefectoCliente("AUTOGENERADO", "");
		//AGREGAR AL CARRITO
		}else if(accion.equals(vista.AGREGAR_CARRITO)){
			if (vista.isItemSeleccionado(accion)){
				vista.abrirFormulario(accion, this);
				vista.setValoresDefectoCarrito("0.00", "1");
			} else vista.errorOperacion("Debe Seleccionar un Producto", accion);
		// BORRAR ITEM
		} else if(accion.equals(vista.BORRAR_ITEM)){
			if (vista.isItemSeleccionado(accion)){
				DetalleVenta dv = (DetalleVenta) vista.getItemSeleccionado(accion);
				carrito.borrarItem(dv.getCodigoProducto());
				vista.actualizarCarrito();
				vista.setTotal(String.valueOf(carrito.getTotalCarrito()));
			} else vista.errorOperacion("Debe Seleccionar un Item del Carrito", accion);
		// LIMPIAR CARRITO
		} else if(accion.equals(vista.LIMPIAR_CARRITO)){
			carrito.borrarCarrito();
			vista.actualizarCarrito();
			vista.setTotal("0.00");
		// FINALIZAR COMPRA
		} else if (accion.equals(vista.FINALIZAR_COMPRA)){
			if (carrito.getCarrito().isEmpty()){
				vista.errorOperacion("Carrito Vacio: Seleccione un Producto de la Lista", accion);
			} else if(vista.isItemSeleccionado(accion)){
				Cliente c = (Cliente) vista.getItemSeleccionado(accion);
				AgregarVentaBD(c);
			} else vista.errorOperacion("Debe Seleccionar un Cliente", accion);
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
		} else if(accion.equals(vista.CONFIRMAR_INSERTAR_CLIENTE)){
			confirmarInsertarCliente();
		} else if(accion.equals(vista.CANCELAR_INSERTAR_CLIENTE)){
			vista.cerrarFormulario(vista.INSERTAR_CLIENTE);
		} else if(accion.equals(vista.CONFIRMAR_AGREGAR_CARRITO)){
			confirmarAgregarCarrito();
		} else if(accion.equals(vista.CANCELAR_AGREGAR_CARRITO)){
			vista.cerrarFormulario(vista.AGREGAR_CARRITO);
		}
	}
	
	private void AgregarVentaBD(Cliente c) {
		VentaBD ventaBD = new VentaBD();
		Venta venta = new Venta();
		venta.setCodigoCliente(c.getCodigoCliente());
		if (ventaBD.insertarVenta(venta, carrito.getCarrito())){
			vista.operacionCorrecta("¡Su compra ha sido realizada con éxito!");
		}
	}

	private void confirmarAgregarCarrito() {
		// Se toman los datos ingresados
		Producto p = (Producto) vista.getItemSeleccionado(vista.AGREGAR_CARRITO);
		String descuento = vista.getTextDescuentoCarrito();
		String cantidad = vista.getTextCantidadCarrito();
		if (this.validarItemCarrito(vista.AGREGAR_CARRITO, descuento, cantidad)){
			// Si validacion OK se genera un detalle venta
			DetalleVenta dv = new DetalleVenta();
			dv.setProducto(p);
			dv.setDescuento(Double.parseDouble(descuento));
			dv.setCantidad(Integer.parseInt(cantidad));
			// se agrega detalleVenta a carrito
			agregarItemCarrito(dv);
			vista.cerrarFormulario(vista.AGREGAR_CARRITO);
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

	private void confirmarInsertarCliente() {
			// Se toman los datos ingresados
		String nombre = vista.getTextNombreCliente();
			// Chequeo validez de los datos y
			// Si se agrega cliente a BD la operacion es correcta
		if (validarCliente(vista.INSERTAR_CLIENTE, nombre) && insertarClienteBD(nombre)) {
			vista.operacionCorrecta("Cliente Agregado");
			vista.cerrarFormulario(vista.INSERTAR_CLIENTE);
		}
	}
	

	private void agregarItemCarrito(DetalleVenta dv) {
		carrito.agregarItem(dv);
		vista.setTotal(String.format("%.2f",carrito.getTotalCarrito()));
		vista.actualizarCarrito();
	}
	
	public boolean insertarClienteBD(String nombre){
		Cliente c = new Cliente();
		c.setNombre(nombre);
		ClienteBD clienteBD = new ClienteBD();
		if (clienteBD.insertarCliente(c)){
			vista.actualizarClientes(clienteBD.getClientes());
			return true;
		}else return false;
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
	
	private boolean validarCliente(String operacion, String nombre){
		if( nombre == null || nombre.equals("")){
	        vista.errorOperacion("El nombre no puede quedar vacío", operacion);
	        return false;
	    }return true;
	}
	
	private boolean validarItemCarrito(String operacion, String descuento, String cantidad){
		int cantidadParseada;
		Double descuentoParseado;
		// Chequeo validez de los datos
		try{
			descuentoParseado = Double.parseDouble(descuento);
        } catch (Exception excepcion){
            vista.errorOperacion("El descuento no es un valor válido", operacion);
            return false;
        }
		if( descuentoParseado < 0 ){
            vista.errorOperacion("El descuento no puede ser menor a cero", operacion);
            return false;
        }
		try{
            cantidadParseada = Integer.parseInt(cantidad);
        } catch (Exception excepcion){
            vista.errorOperacion("La cantidad no es un valor válido", operacion);
            return false;
        }
		if( cantidadParseada <= 0 ){
            vista.errorOperacion("La cantidad debe ser mayor a cero", operacion);
            return false;
        }
		return true;
	}
}
