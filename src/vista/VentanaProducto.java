package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controlador.ControladorProducto;

public class VentanaProducto extends JFrame{
    
	private JTextField txtCodigo;
	private JTextField txtDescripcion;   
	private JTextField txtPrecio;
	private JButton btnAceptar; 
	private JButton btnCancelar;
        
    public VentanaProducto(String operacion) {
        super();
    	this.setTitle(operacion);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        
        //Instancio componentes
		txtCodigo = new JTextField(20);
		txtCodigo.setEditable(false);
        txtDescripcion = new JTextField(20);
	    txtPrecio = new JTextField();
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
        
        // Seteo Paneles
		JPanel panelCampos = new JPanel(new BorderLayout());
		this.add(panelCampos, BorderLayout.CENTER);
		TitledBorder borde = new TitledBorder("Planilla Producto");
		borde.setTitleFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,16));
		borde.setTitleColor(Color.getHSBColor(20, 20, 0));
		panelCampos.setBorder(borde);
		
		JPanel panelCodigo = new JPanel(new BorderLayout());
		panelCampos.add(panelCodigo, BorderLayout.NORTH);
		panelCodigo.add(txtCodigo, BorderLayout.CENTER);
		panelCodigo.setBorder(new TitledBorder("Codigo"));
		
		JPanel panelNombre = new JPanel(new BorderLayout());
		panelCampos.add(panelNombre, BorderLayout.CENTER);
		panelNombre.add(txtDescripcion, BorderLayout.CENTER);
		panelNombre.setBorder(new TitledBorder("Descripcion"));
		
		JPanel panelPrecio = new JPanel(new BorderLayout());
		panelCampos.add(panelPrecio, BorderLayout.SOUTH);

		panelPrecio.add(txtPrecio, BorderLayout.CENTER);
		panelPrecio.setBorder(new TitledBorder("Precio"));
		
		JPanel panelBotonera = new JPanel();
		this.add(panelBotonera, BorderLayout.SOUTH);
		
		panelBotonera.add(btnAceptar);
		panelBotonera.add(btnCancelar);
        
        // Empaqueto y acomodo en pantalla
    	this.pack();
        this.setLocationRelativeTo(null);
    }
  
	public void setControlador(ControladorProducto c) {
        btnAceptar.addActionListener(c);
        btnCancelar.addActionListener(c);	
	}
	
    public String getTxtCodigo() {
		return txtCodigo.getText();
	}

	public void setTxtCodigo(String txtCodigo) {
		this.txtCodigo.setText(txtCodigo);;
	}

	public String getTxtDescripcion() {
		return txtDescripcion.getText();
	}

	public void setTxtDescripcion(String txtDescripcion) {
		this.txtDescripcion.setText(txtDescripcion);;
	}

	public String getTxtPrecio() {
		return txtPrecio.getText();
	}

	public void setTxtPrecio(String txtPrecio) {
		this.txtPrecio.setText(txtPrecio);
	}

	public void setActionCommand(String aceptar,
			String cancelar) {
		this.btnAceptar.setActionCommand(aceptar);
		this.btnCancelar.setActionCommand(cancelar);
	}
}
