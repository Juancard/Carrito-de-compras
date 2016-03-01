package vista;

import controlador.ControladorCarrito;
import controlador.ControladorCliente;
import controlador.ControladorProducto;

public class IniciarCarrito {
	public static void main(String[] args) {
		VistaPrincipal vp = new VistaPrincipal();
		setControladores( (InterfazVista) vp);
		vp.setVisible(true);
	}
	
	private static void setControladores(InterfazVista vp){
		ControladorProducto controladorProducto = new ControladorProducto(vp);
		ControladorCliente controladorCliente = new ControladorCliente(vp);
		ControladorCarrito controladorCarrito = new ControladorCarrito(vp);
	}
}
