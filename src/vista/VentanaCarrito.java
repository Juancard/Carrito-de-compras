package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controlador.ControladorCarrito;

public class VentanaCarrito extends JFrame{

	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JTextField txtDescuento;
	private JTextField txtCantidad;
	private JButton btnAceptar;
	private JButton btnCancelar;

	
	public VentanaCarrito(String titulo){
        super();
    	this.setTitle(titulo);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        
        //Instancio componentes
    	txtCodigo = new JTextField(20);
    	txtCodigo.setEditable(false);
    	txtDescripcion = new JTextField(20);
    	txtDescripcion.setEditable(false);
		txtDescuento = new JTextField(20);
        txtCantidad = new JTextField(20);
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
        
        // Seteo Paneles
		JPanel panelCampos = new JPanel(new BorderLayout());
		this.add(panelCampos, BorderLayout.CENTER);
		TitledBorder borde = new TitledBorder("Planilla Carrito");
		borde.setTitleFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,16));
		borde.setTitleColor(Color.getHSBColor(20, 20, 0));
		panelCampos.setBorder(borde);
		
		JPanel panelProducto = new JPanel(new BorderLayout());
		panelCampos.add(panelProducto, BorderLayout.NORTH);
		
		JPanel panelCodigo = new JPanel(new BorderLayout());
		panelProducto.add(panelCodigo, BorderLayout.NORTH);
		panelCodigo.add(txtCodigo, BorderLayout.CENTER);
		panelCodigo.setBorder(new TitledBorder("Codigo"));
		
		JPanel panelNombre = new JPanel(new BorderLayout());
		panelProducto.add(panelNombre, BorderLayout.CENTER);
		panelNombre.add(txtDescripcion, BorderLayout.CENTER);
		panelNombre.setBorder(new TitledBorder("Descripcion"));
		
		JPanel panelCantidad = new JPanel(new BorderLayout());
		panelCampos.add(panelCantidad, BorderLayout.CENTER);
		panelCantidad.add(txtCantidad, BorderLayout.CENTER);
		panelCantidad.setBorder(new TitledBorder("Cantidad"));
		
		JPanel panelDescuento = new JPanel(new BorderLayout());
		panelCampos.add(panelDescuento, BorderLayout.SOUTH);
		panelDescuento.add(txtDescuento, BorderLayout.CENTER);
		panelDescuento.setBorder(new TitledBorder("Descuento parcial"));
		
		JPanel panelBotonera = new JPanel();
		this.add(panelBotonera, BorderLayout.SOUTH);
		
		panelBotonera.add(btnAceptar);
		panelBotonera.add(btnCancelar);
        
        // Empaqueto y acomodo en pantalla
    	this.pack();
        this.setLocationRelativeTo(null);
	}
	
	public void setTextCodigo(String codigo) {
		this.txtCodigo.setText(codigo);
	}

	public void setTextDescripcion(String txtDescripcion) {
		this.txtDescripcion.setText(txtDescripcion);
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
