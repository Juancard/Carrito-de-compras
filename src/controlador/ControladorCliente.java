package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import carrito.Cliente;
import carrito.ClienteBD;
import vista.InterfazVista;

public class ControladorCliente implements ActionListener {

	private InterfazVista vista;

	public ControladorCliente(InterfazVista v) {
		vista = v;
		setearControlador();
		setearTabla();
	}

	private void setearTabla() {
		// Traigo de la base los clientes
		ClienteBD clienteBD = new ClienteBD();
		vista.setearClientes(clienteBD.getClientes());
	}

	private void setearControlador() {
		vista.setControladorCliente(this);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		String accion = evento.getActionCommand();
		if(accion.equals(InterfazVista.INSERTAR_CLIENTE)){
			vista.abrirFormularioCliente(accion,this);
			vista.setValoresDefectoCliente("AUTOGENERADO", "");
		}
		if(accion.equals(vista.CONFIRMAR_INSERTAR_CLIENTE)){
			confirmarInsertarCliente();
		} else if(accion.equals(vista.CANCELAR_INSERTAR_CLIENTE)){
			vista.cerrarFormulario(vista.INSERTAR_CLIENTE);
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
	
	public boolean insertarClienteBD(String nombre){
		Cliente c = new Cliente();
		c.setNombre(nombre);
		ClienteBD clienteBD = new ClienteBD();
		if (clienteBD.insertarCliente(c)){
			vista.actualizarClientes(clienteBD.getClientes());
			return true;
		}else return false;
	}

	private boolean validarCliente(String operacion, String nombre){
		if( nombre == null || nombre.equals("")){
	        vista.errorOperacion("El nombre no puede quedar vacío", operacion);
	        return false;
	    }return true;
	}
}
