package vista;

import controlador.ControladorCarrito;
import controlador.ControladorCliente;
import controlador.ControladorProducto;
import controlador.ControladorReporte;

public class IniciarCarrito {
	public static void main(String[] args) {
		VistaConLayout v = new VistaConLayout();
		//VistaSinLayout v = new VistaSinLayout(); // VISTA ADICIONAL
		setControladores( (InterfazVista) v);
		v.setVisible(true);
	}
	
	private static void setControladores(InterfazVista v){
		ControladorProducto controladorProducto = new ControladorProducto(v);
		ControladorCliente controladorCliente = new ControladorCliente(v);
		ControladorCarrito controladorCarrito = new ControladorCarrito(v);
		ControladorReporte controladorReporte = new ControladorReporte(v);
	}
}
