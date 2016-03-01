package vista;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VentanaCliente extends JFrame{
	private JLabel lblCodigo;
    private JLabel lblNombre;  
    private JTextField txtCodigo;
    private JTextField txtNombre;   
    private JButton btnAceptar; 
    private JButton btnCancelar;
    
    public VentanaCliente(String titulo) {
        super();
    	this.setTitle(titulo);
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        this.setSize(310, 200);                               
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
    }

    private void inicializarComponentes() {    	
        	// Creo componentes
    	instanciarComponentes();
        	// Posicion en ventana
        posicionarComponentes();
        	// Adiciono los componentes a la ventana
        agregarComponentes();
    }

    private void agregarComponentes() {
    	this.add(lblCodigo);
    	this.add(txtCodigo);
        this.add(lblNombre);
        this.add(txtNombre);
        this.add(btnAceptar);
        this.add(btnCancelar);		
	}
	private void posicionarComponentes() {
        lblCodigo.setBounds(25, 10, 200, 25);   //  (x, y, ancho, alto)
        txtCodigo.setBounds(25, 30, 250, 25);
        lblNombre.setBounds(25, 60, 200, 25);
        txtNombre.setBounds(25, 80, 250, 25);
        btnAceptar.setBounds(25, 120, 100, 30);
        btnCancelar.setBounds(130, 120, 100, 30);		
	}

	private void instanciarComponentes() {
		lblCodigo = new JLabel("Código");
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
        lblNombre = new JLabel("Inserte Nombre"); 
        txtNombre = new JTextField();
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");		
	}

	public void setControlador(ActionListener c) {
        btnAceptar.addActionListener(c);
        btnCancelar.addActionListener(c);
	}
	
	public void setActionCommand(String aceptar, String cancelar){
        btnAceptar.setActionCommand(aceptar);
        btnCancelar.setActionCommand(cancelar);
	}
	
	public void setTextNombre(String nombre) {
		txtNombre.setText(nombre);
	}

	public void setTextCodigo(String codigo){
		txtCodigo.setText(codigo);
	}
	
	public String getTextNombre() {
		return txtNombre.getText();
	}
}
