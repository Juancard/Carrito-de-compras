package interfaz;

import java.awt.event.*;

import javax.swing.*;

public class VentanaInsertarProducto extends JFrame implements ActionListener {

    private JLabel lblDescripcion;  
    private JLabel lblPrecio;           
    private JTextField txtDescripcion;   
    private JTextField txtPrecio;
    private JButton btnAceptar; 
    private JButton btnCancelar;
    
    private InterfazPrincipal interfaz;

    public VentanaInsertarProducto(InterfazPrincipal ip) {
        super();
        interfaz = ip;
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        this.setTitle("Insertar Producto");                   
        this.setSize(310, 210);                               
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
    }

    private void inicializarComponentes() {
    		// Creo componentes
    	instanciarComponentes();
        	// Acciones de componentes
    	accionComponentes();
        	// Posicion en ventana
        posicionarComponentes();
        	// Adiciono los componentes a la ventana
        agregarComponentes();
    }


	public void actionPerformed(ActionEvent e) {
    	if (e.getActionCommand().equals("ACEPTAR")) {
    			// Se toman los datos ingresados
    		String descripcion = txtDescripcion.getText();
    		String precioIngresado = txtPrecio.getText();
    		Double precioParseado;
    	   		// Chequeo validez de los datos
    		if( descripcion == null || descripcion.equals("")){
                JOptionPane.showMessageDialog( this, "La descripcion no puede quedar vacía", "Agregar un producto", JOptionPane.ERROR_MESSAGE);
                return;
            }	
    		try{
                precioParseado = Double.parseDouble(precioIngresado);
            } catch (Exception excepcion){
                JOptionPane.showMessageDialog( this, "El precio no es un valor válido", "Agregar un producto", JOptionPane.ERROR_MESSAGE);
                return;
            }
    		if( precioParseado <= 0 ){
                JOptionPane.showMessageDialog( this, "El precio debe ser mayor a cero", "Agregar un producto", JOptionPane.ERROR_MESSAGE);
                return;
            }
    			// Si todo salió bien se agrega producto a BD.
    		if (interfaz.insertarProductoBD(descripcion, precioParseado)){
    			JOptionPane.showMessageDialog(this,"Producto Agregado");
    		}
    			// Actualiza tabla de productos
    		interfaz.actualizarTablaProducto();
	        	// Limpio campos de texto y salgo de la ventana.
	        limpiarYSalir();
    	} else{
    		if (e.getActionCommand().equals("CANCELAR")){
    	        // Limpio campos de texto y salgo de la ventana.
    			limpiarYSalir();
    		}
    	}   	
    }

	private void instanciarComponentes() {
        lblDescripcion = new JLabel("Inserte Descripcion"); 
        txtDescripcion = new JTextField();
        lblPrecio = new JLabel("Inserte Precio");
        txtPrecio = new JTextField();
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
	}
	private void accionComponentes() {
        btnAceptar.setActionCommand("ACEPTAR");
        btnAceptar.addActionListener(this);
        btnCancelar.setActionCommand("CANCELAR");
        btnCancelar.addActionListener(this);		
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
    private void limpiarYSalir(){
    	txtDescripcion.setText("");
        txtPrecio.setText("");
        dispose();
    }
}
