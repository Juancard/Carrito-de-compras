package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import carrito.CarritoCompras;
import carrito.Cliente;
import carrito.DetalleVenta;
import carrito.Producto;
import carrito.Venta;
import carrito.VentaBD;
import vista.InterfazVista;

public class ControladorCarrito implements ActionListener{

	private InterfazVista vista;
	private CarritoCompras carrito;

	public ControladorCarrito(InterfazVista v) {
		this.vista=v;
		setearControlador();
		setearTablas();
	}
	
	private void setearControlador() {
		vista.setControladorCarrito(this);
	}
	private void setearTablas() {
		// Genero el carrito de compras
		CarritoCompras carro = new CarritoCompras();
		this.carrito=carro;
		vista.setearCarrito(carrito.getCarrito());
	}


	@Override
	public void actionPerformed(ActionEvent evento) {
		String accion = evento.getActionCommand();
		//AGREGAR AL CARRITO
		if(accion.equals(vista.AGREGAR_CARRITO)){
			if (vista.isItemSeleccionado(accion)){
				vista.abrirFormularioCarrito(accion, this);
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
		} else if (accion.equals(vista.FINALIZAR_COMPRA)){
			if (carrito.getCarrito().isEmpty()){
				vista.errorOperacion("Carrito Vacio: Seleccione un Producto de la Lista", accion);
			} else if(vista.isItemSeleccionado(accion)){
				Cliente c = (Cliente) vista.getItemSeleccionado(accion);
				AgregarVentaBD(c);
			} else vista.errorOperacion("Debe Seleccionar un Cliente", accion);
		}
		
		//ACCIONES DE FORMULARIOS
		if(accion.equals(vista.CONFIRMAR_AGREGAR_CARRITO)){
			confirmarAgregarCarrito();
		} else if(accion.equals(vista.CANCELAR_AGREGAR_CARRITO)){
			vista.cerrarFormulario(vista.AGREGAR_CARRITO);
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
	
	private void agregarItemCarrito(DetalleVenta dv) {
		carrito.agregarItem(dv);
		vista.setTotal(String.format("%.2f",carrito.getTotalCarrito()));
		vista.actualizarCarrito();
	}
	
	private void AgregarVentaBD(Cliente c) {
		VentaBD ventaBD = new VentaBD();
		Venta venta = new Venta();
		venta.setCodigoCliente(c.getCodigoCliente());
		if (ventaBD.insertarVenta(venta, carrito.getCarrito())){
			vista.operacionCorrecta("¡Su compra ha sido realizada con éxito!");
		}
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
