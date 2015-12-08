package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class VentanaCliente extends JFrame{
    private JTextField txtCodigo;
    private JTextField txtNombre;   
    private JButton btnAceptar; 
    private JButton btnCancelar;
    
    public VentanaCliente(String titulo) {
        super();
    	this.setTitle(titulo);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        
        //Instancio componentes
		txtCodigo = new JTextField(20);
		txtCodigo.setEditable(false);
        txtNombre = new JTextField(20);
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
        
        // Seteo Paneles
		JPanel panelCampos = new JPanel(new BorderLayout());
		this.add(panelCampos, BorderLayout.CENTER);
		TitledBorder borde = new TitledBorder("Planilla Cliente");
		borde.setTitleFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,16));
		borde.setTitleColor(Color.getHSBColor(20, 20, 0));
		panelCampos.setBorder(borde);
		
		JPanel panelNombre = new JPanel(new BorderLayout());
		panelCampos.add(panelNombre, BorderLayout.SOUTH);

		panelNombre.add(txtNombre, BorderLayout.CENTER);
		panelNombre.setBorder(new TitledBorder("Nombre"));
		
		JPanel panelCodigo = new JPanel(new BorderLayout());
		panelCampos.add(panelCodigo, BorderLayout.NORTH);

		panelCodigo.add(txtCodigo, BorderLayout.CENTER);
		panelCodigo.setBorder(new TitledBorder("Codigo"));
		
		JPanel panelBotonera = new JPanel();
		this.add(panelBotonera, BorderLayout.SOUTH);
		
		panelBotonera.add(btnAceptar);
		panelBotonera.add(btnCancelar);
        
        // Empaqueto y acomodo en pantalla
    	this.pack();
        this.setLocationRelativeTo(null);
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
