package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import carrito.DetalleVenta;

public class VentanaAgregarCarrito extends JFrame implements ActionListener{

	private JLabel lblDescuento;
	private JTextField txtDescuento;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JButton btnAceptar;
	private JButton btnCancelar;
	
    private InterfazPrincipal interfaz;
    private DetalleVenta detalle;

	public VentanaAgregarCarrito(InterfazPrincipal ip, DetalleVenta dv){
		super();
		interfaz = ip;
		this.detalle = dv;
        configurarVentana();
        inicializarComponentes();
	}

	private void configurarVentana() {
        this.setTitle("Agregar a Carrito");                   
        this.setSize(235, 170);                               
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
    }

    private void inicializarComponentes() {
    		//Instancio componentes
    	instanciarComponentes();
    		// Acciones de los botones
    	accionComponentes();
        	// Posicion en ventana
    	posicionarComponentes();    	
        	//Agrego Componentes
    	agregarComponentes();        
	}
	    
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ACEPTAR")) {
		    		
			// Se toman los datos ingresados
			String descuento = txtDescuento.getText();
			String cantidad = txtCantidad.getText();
			int cantidadParseada;
			Double descuentoParseado;
		    	
    		// Chequeo validez de los datos
    		try{
    			descuentoParseado = Double.parseDouble(descuento);
            } catch (Exception excepcion){
	            JOptionPane.showMessageDialog( this, "El descuento no es un valor válido", "Agregar a carrito", JOptionPane.ERROR_MESSAGE);
	            return;
            }
    		if( descuentoParseado < 0 ){
                JOptionPane.showMessageDialog( this, "El descuento no puede ser menor a cero", "Agregar a carrito", JOptionPane.ERROR_MESSAGE);
                return;
            }
    		try{
                cantidadParseada = Integer.parseInt(cantidad);
            } catch (Exception excepcion){
                JOptionPane.showMessageDialog( this, "La cantidad no es un valor válido", "Agregar a carrito", JOptionPane.ERROR_MESSAGE);
                return;
            }
    		if( cantidadParseada <= 0 ){
                JOptionPane.showMessageDialog( this, "La cantidad debe ser mayor a cero", "Agregar a carrito", JOptionPane.ERROR_MESSAGE);
                return;
            }
					
    		// Si todo salió bien se agrega producto a carrito.
    		detalle.setDescuento(descuentoParseado);
    		detalle.setCantidad(cantidadParseada);
    		interfaz.agregarItemCarrito(detalle);
     		
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
    	lblDescuento = new JLabel("Descuento total aplicado: ");
    	txtDescuento = new JTextField("0.0");
    	lblCantidad = new JLabel("Cantidad solicitada: ");
    	txtCantidad = new JTextField("1");
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
	private void accionComponentes() {
        btnAceptar.setActionCommand("ACEPTAR");
        btnAceptar.addActionListener(this);
        btnCancelar.setActionCommand("CANCELAR");
        btnCancelar.addActionListener(this);  	
	}	
    private void limpiarYSalir(){
    	txtDescuento.setText("");
        txtCantidad.setText("");
        dispose();
    }


}
