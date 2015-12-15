package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.CarritoCompras;
import modelo.Cliente;
import modelo.DetalleVenta;
import modelo.Producto;
import modelo.Venta;
import modelo.VentaBD;
import vista.InterfazVista;
import vista.TablaCarrito;
import vista.VentanaCarrito;

public class ControladorCarrito implements ActionListener{

	private InterfazVista vista;
	private CarritoCompras carrito;
	private TablaCarrito tablaCarrito;
	private VentanaCarrito ventanaCarrito;

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
		tablaCarrito = new TablaCarrito(carrito.getCarrito());
		vista.setearCarrito(tablaCarrito);
	}


	@Override
	public void actionPerformed(ActionEvent evento) {
		String accion = evento.getActionCommand();
		//AGREGAR AL CARRITO
		if(accion.equals(vista.AGREGAR_CARRITO)){
			if (vista.isItemSeleccionado(accion)){
				abrirFormulario(accion);
			} else vista.errorOperacion("Debe Seleccionar un Producto", accion);
		// BORRAR ITEM
		} else if(accion.equals(vista.BORRAR_ITEM)){
			if (vista.isItemSeleccionado(accion)){
				DetalleVenta dv = (DetalleVenta) vista.getItemSeleccionado(accion);
				carrito.borrarItem(dv.getCodigoProducto());
				tablaCarrito.fireTableDataChanged();
				vista.setTotal(String.valueOf(carrito.getTotalCarrito()));
			} else vista.errorOperacion("Debe Seleccionar un Item del Carrito", accion);
		// LIMPIAR CARRITO
		} else if(accion.equals(vista.LIMPIAR_CARRITO)){
			limpiarCarrito();
		// FINALIZAR COMPRA
		} else if (accion.equals(vista.FINALIZAR_COMPRA)){
			if (carrito.getCarrito().isEmpty()){
				vista.errorOperacion("Carrito Vacio: Seleccione un producto de la lista", accion);
			} else if(vista.isItemSeleccionado(accion)){
				Cliente c = (Cliente) vista.getItemSeleccionado(accion);
				AgregarVentaBD(c);
				limpiarCarrito();
			} else vista.errorOperacion("Debe Seleccionar un Cliente", accion);
		}
		
		//ACCIONES DE FORMULARIOS
		if(accion.equals(vista.CONFIRMAR_AGREGAR_CARRITO)){
			confirmarAgregarCarrito();
		} else if(accion.equals(vista.CANCELAR_AGREGAR_CARRITO)){
			cerrarFormulario();
		}
	}

	private void abrirFormulario(String accion) {
		if (accion.equals(vista.AGREGAR_CARRITO)){
			ventanaCarrito = new VentanaCarrito(accion);
			ventanaCarrito.setControlador(this);
			ventanaCarrito.setActionCommand(vista.CONFIRMAR_AGREGAR_CARRITO, vista.CANCELAR_AGREGAR_CARRITO);
			Producto p = (Producto) vista.getItemSeleccionado(accion);
			String codigo = Integer.toString(p.getCodigoProducto());
			setValoresDefecto(codigo,p.getDescripcion(),"0.00","1");
			ventanaCarrito.setVisible(true);
		}
	}

	private void cerrarFormulario() {
		ventanaCarrito.dispose();
	}

	private void setValoresDefecto(String codigo, String descripcion, String descuento, String cantidad) {
		ventanaCarrito.setTextCodigo(codigo);
		ventanaCarrito.setTextDescripcion(descripcion);
		ventanaCarrito.setTextDescuento(descuento);
		ventanaCarrito.setTextCantidad(cantidad);
	}

	private void confirmarAgregarCarrito() {
		// Se toman los datos ingresados
		Producto p = (Producto) vista.getItemSeleccionado(vista.AGREGAR_CARRITO);
		String descuento = ventanaCarrito.getTextDescuento();
		String cantidad = ventanaCarrito.getTextCantidad();
		if (this.validarItemCarrito(vista.AGREGAR_CARRITO, descuento, cantidad)){
			// Si validacion OK se genera un detalle venta
			DetalleVenta dv = new DetalleVenta();
			dv.setProducto(p);
			dv.setDescuento(Double.parseDouble(descuento));
			dv.setCantidad(Integer.parseInt(cantidad));
			dv.setPrecioUnitario(p.getPrecio());
			// se agrega detalleVenta a carrito
			agregarItemCarrito(dv);
			cerrarFormulario();
		}
	}
	
	private void agregarItemCarrito(DetalleVenta dv) {
		carrito.agregarItem(dv);
		vista.setTotal(String.format("%.2f",carrito.getTotalCarrito()));
		tablaCarrito.fireTableDataChanged();
	}
	
	private void AgregarVentaBD(Cliente c) {
		VentaBD ventaBD = new VentaBD();
		Venta venta = new Venta();
		venta.setCodigoCliente(c.getCodigoCliente());
		if (ventaBD.insertarVenta(venta, carrito.getCarrito())){
			vista.operacionCorrecta("¡Su compra ha sido realizada con éxito!");
		}
	}
	
	private void limpiarCarrito(){
		carrito.borrarCarrito();
		tablaCarrito.fireTableDataChanged();
		vista.setTotal("0.00");
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
