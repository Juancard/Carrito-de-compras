package vista;

import controlador.Controlador;

public class IniciarCarrito {
	public static void main(String[] args) {
		InterfazPrincipal ip = new InterfazPrincipal();
		Controlador c = new Controlador(ip);
		ip.setControlador(c);
		ip.setVisible(true);
	}
}
