package vista;

import java.awt.event.*;

import javax.swing.*;

import controlador.Controlador;

public class VentanaInsertarProducto extends JFrame /*implements ActionListener*/ {

    private JLabel lblDescripcion;  
    private JLabel lblPrecio;           
    private JTextField txtDescripcion; 
    private JTextField txtPrecio;
    private JButton btnAceptar; 
    private JButton btnCancelar;
    
    private InterfazVista interfazVista;

    public VentanaInsertarProducto(InterfazVista iv) {
        super();
        interfazVista = iv;
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        this.setTitle(interfazVista.INSERTAR_PRODUCTO);                   
        this.setSize(310, 210);                               
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
        lblDescripcion = new JLabel("Inserte Descripcion"); 
        txtDescripcion = new JTextField();
        lblPrecio = new JLabel("Inserte Precio");
        txtPrecio = new JTextField();
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
	}

    private void posicionarComponentes() {
        lblDescripcion.setBounds(25, 10, 200, 25);   //  (x, y, ancho, alto)
        txtDescripcion.setBounds(25, 30, 250, 25);
        lblPrecio.setBounds(25, 60, 200, 25);
        txtPrecio.setBounds(25, 80, 250, 25);
        btnAceptar.setBounds(25, 120, 100, 30);
        btnCancelar.setBounds(130, 120, 100, 30);		
	}
	private void agregarComponentes() {
        this.add(lblDescripcion);
        this.add(txtDescripcion);
        this.add(lblPrecio);
        this.add(txtPrecio);
        this.add(btnAceptar);
        this.add(btnCancelar);
	}

	public void setControlador(Controlador c) {
        btnAceptar.setActionCommand(interfazVista.CONFIRMAR_INSERTAR_PRODUCTO);
        btnAceptar.addActionListener(c);
        btnCancelar.setActionCommand(interfazVista.CANCELAR_INSERTAR_PRODUCTO);
        btnCancelar.addActionListener(c);		
	}

	public String getTxtDescripcion() {
		return txtDescripcion.getText();	
	}

	public String getTxtPrecio() {
		return txtPrecio.getText();
	}

	public void cerrar() {
    	txtDescripcion.setText("");
        txtPrecio.setText("");
        dispose();
	}

}
