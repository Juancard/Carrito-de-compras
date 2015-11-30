package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaActualizarProducto extends JFrame implements ActionListener{
	
	private JLabel lblCodigo;
	private JLabel lblDescripcion;  
    private JLabel lblPrecio;     
    private JTextField txtCodigo;
    private JTextField txtDescripcion;   
    private JTextField txtPrecio;
    private JButton btnAceptar; 
    private JButton btnCancelar;
    
    private InterfazPrincipal interfaz;
    
    public VentanaActualizarProducto(InterfazPrincipal ip) {
        super();
        interfaz = ip;
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        this.setTitle("Actualizar Producto");                   
        this.setSize(310, 250);                               
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
		if (e.getActionCommand() == "ACEPTAR"){
				// Se toman los datos ingresados
    		String descripcion = txtDescripcion.getText();
    		String precioIngresado = txtPrecio.getText();
    		Double precioParseado;
			int codigo;
				// Chequeo validez de los datos
    		if( descripcion == null || descripcion.equals("")){
                JOptionPane.showMessageDialog( this, "La descripcion no puede quedar vacía", "Actualizar un producto", JOptionPane.ERROR_MESSAGE);
                return;
            }
    		try{
                precioParseado = Double.parseDouble(precioIngresado);
            } catch (Exception excepcion){
                JOptionPane.showMessageDialog( this, "El precio no es un valor válido", "Actualizar un producto", JOptionPane.ERROR_MESSAGE);
                return;
            }
    		if( precioParseado <= 0 ){
                JOptionPane.showMessageDialog( this, "El precio debe ser mayor a cero", "Actualizar un producto", JOptionPane.ERROR_MESSAGE);
                return;
            }
    			// Como no permito editar Código, no realizo validacion.
    		codigo = Integer.parseInt(txtCodigo.getText());
    			// Si todo salió bien se actualiza producto en BD.
    		if (interfaz.actualizarProductoBD(codigo, descripcion, precioParseado)){
    			JOptionPane.showMessageDialog(this,"Producto Actualizado");
    		}
    			//Cerrar ventana
	        dispose();
		} else {
    		if (e.getActionCommand().equals("CANCELAR")){
    			dispose();
    		}
    	}
	}  	
		
	public void setTxtCodigo(int codigoProducto) {
	    txtCodigo.setText(Integer.toString(codigoProducto));
	}

	public void setTxtDescripcion(String descripcion) {
	    txtDescripcion.setText(descripcion);
	}

	public void setTxtPrecio(double precio) {
	    txtPrecio.setText(Double.toString(precio));	
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
	private void accionComponentes() {
        btnAceptar.setActionCommand("ACEPTAR");
        btnAceptar.addActionListener(this);
        btnCancelar.setActionCommand("CANCELAR");
        btnCancelar.addActionListener(this);		
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
}
