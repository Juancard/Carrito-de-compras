package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlador.ControladorProducto;

public class VentanaProducto extends JFrame{
	
	JLabel lblCodigo;
	JLabel lblDescripcion;  
    JLabel lblPrecio;     
    JTextField txtCodigo;
    JTextField txtDescripcion;   
    JTextField txtPrecio;
    JButton btnAceptar; 
    JButton btnCancelar;
        
    public VentanaProducto(String operacion) {
        super();
        this.setTitle(operacion);
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        this.setSize(310, 250);                               
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
	
	private void instanciarComponentes() {
    	lblCodigo = new JLabel("Código");
    	txtCodigo = new JTextField();
    	txtCodigo.setEditable(false);
	    lblDescripcion = new JLabel("Descripcion"); 
	    txtDescripcion = new JTextField();
	    lblPrecio = new JLabel("Precio");
	    txtPrecio = new JTextField();
	    btnAceptar = new JButton("Aceptar");
	    btnCancelar = new JButton("Cancelar");   
	}
	private void posicionarComponentes() {
        lblCodigo.setBounds(25, 10, 200, 25);   //  (x, y, ancho, alto)
        txtCodigo.setBounds(25, 30, 250, 25);
        lblDescripcion.setBounds(25, 60, 200, 25);
        txtDescripcion.setBounds(25, 80, 250, 25);
        lblPrecio.setBounds(25, 110, 200, 25);
        txtPrecio.setBounds(25, 130, 250, 25);
        btnAceptar.setBounds(25, 170, 100, 30);
        btnCancelar.setBounds(130, 170, 100, 30);
	}
	private void agregarComponentes() {
        this.add(lblCodigo);
        this.add(txtCodigo);
        this.add(lblDescripcion);
        this.add(txtDescripcion);
        this.add(lblPrecio);
        this.add(txtPrecio);
        this.add(btnAceptar);
        this.add(btnCancelar);		
	}

	public void setControlador(ControladorProducto c) {
        btnAceptar.addActionListener(c);
        btnCancelar.addActionListener(c);	
	}
}
