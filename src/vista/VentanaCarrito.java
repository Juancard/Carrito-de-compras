package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.ControladorCarrito;

public class VentanaCarrito extends JFrame{

	private JLabel lblDescuento;
	private JTextField txtDescuento;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	public VentanaCarrito(String titulo){
		super();
        this.setTitle(titulo);                   
        configurarVentana();
        inicializarComponentes();
	}

	private void configurarVentana() {
        this.setSize(235, 170);                               
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
    }

    private void inicializarComponentes() {
    		//Instancio componentes
    	instanciarComponentes();
        	// Posicion en ventana
    	posicionarComponentes();    	
        	//Agrego Componentes
    	agregarComponentes();        
	}

	private void instanciarComponentes() {
    	lblDescuento = new JLabel("Descuento total aplicado: ");
    	txtDescuento = new JTextField();
    	lblCantidad = new JLabel("Cantidad solicitada: ");
    	txtCantidad = new JTextField();
    	btnAceptar = new JButton("Aceptar");
    	btnCancelar = new JButton("Cancelar");
	}
	private void agregarComponentes() {
        this.add(lblDescuento);
        this.add(txtDescuento);
        this.add(lblCantidad);
        this.add(txtCantidad);        
        this.add(btnAceptar);
        this.add(btnCancelar);		
	}
	private void posicionarComponentes() {
        lblDescuento.setBounds(18, 20, 150, 25);   //  (x, y, ancho, alto)
        txtDescuento.setBounds(165, 20, 50, 25);
        lblCantidad.setBounds(50, 50, 120, 25);
        txtCantidad.setBounds(165, 50, 50, 25);
        btnAceptar.setBounds(13, 90, 100, 30);
        btnCancelar.setBounds(118, 90, 100, 30);		
	}

	public void setTextDescuento(String descuento) {
		txtDescuento.setText(descuento);
	}

	public void setTextCantidad(String cantidad){
		txtCantidad.setText(cantidad);
	}
	
	public String getTextDescuento() {
		return txtDescuento.getText();
	}
	public String getTextCantidad() {
		return txtCantidad.getText();
	}
	
	public void setActionCommand(String aceptar, String cancelar){
        btnAceptar.setActionCommand(aceptar);
        btnCancelar.setActionCommand(cancelar);
	}
	
	public void setControlador(ControladorCarrito c) {
        btnAceptar.addActionListener(c);
        btnCancelar.addActionListener(c);
	}
}
