package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaInsertarCliente extends JFrame implements ActionListener{
    private JLabel lblNombre;  
    private JTextField txtNombre;   
    private JButton btnAceptar; 
    private JButton btnCancelar;
    
    private InterfazPrincipal interfaz;

    public VentanaInsertarCliente(InterfazPrincipal ip) {
        super();
        interfaz = ip;
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        this.setTitle("Insertar Cliente");                   
        this.setSize(310, 140);                               
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
    }

    private void inicializarComponentes() {    	
        	// Creo componentes
    	instanciarComponentes();
    		// Acciones de los botones
    	accionComponentes();
        	// Posicion en ventana
        posicionarComponentes();
        	// Adiciono los componentes a la ventana
        agregarComponentes();
    }

	public void actionPerformed(ActionEvent e) {
    	if (e.getActionCommand().equals("ACEPTAR")) {
    			// Se toman los datos ingresados
    		String nombre = txtNombre.getText();
    			// Chequeo validez de los datos
    		if( nombre == null || nombre.equals("")){
                JOptionPane.showMessageDialog( this, "El nombre no puede quedar vacío", "Agregar un cliente", JOptionPane.ERROR_MESSAGE);
                return;
            }
    			// Si todo salió bien se agrega cliente a BD.
			if (interfaz.insertarClienteBD(nombre)) {
				JOptionPane.showMessageDialog(this,"Cliente Agregado");
			}
				// Actualiza tabla de clientes
    		interfaz.actualizarTablaCliente();
	        	// Limpio campos de texto y salgo de la ventana.
	        limpiarYSalir();
	        
    	} else{
    		if (e.getActionCommand().equals("CANCELAR")){			
    	        	// Limpio campos de texto y salgo de la ventana.
    			limpiarYSalir();
    		}
    	}   	
    }
        
    private void limpiarYSalir(){
    	txtNombre.setText("");
        dispose();
    }
    private void agregarComponentes() {
        this.add(lblNombre);
        this.add(txtNombre);
        this.add(btnAceptar);
        this.add(btnCancelar);		
	}
	private void posicionarComponentes() {
        lblNombre.setBounds(25, 10, 200, 25);   //  (x, y, ancho, alto)
        txtNombre.setBounds(25, 30, 250, 25);
        btnAceptar.setBounds(25, 60, 100, 30);
        btnCancelar.setBounds(130, 60, 100, 30);		
	}
	private void accionComponentes() {
        btnAceptar.setActionCommand("ACEPTAR");
        btnAceptar.addActionListener(this);
        btnCancelar.setActionCommand("CANCELAR");
        btnCancelar.addActionListener(this);	
	}
	private void instanciarComponentes() {
        lblNombre = new JLabel("Inserte Nombre"); 
        txtNombre = new JTextField();
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");		
	}
}
