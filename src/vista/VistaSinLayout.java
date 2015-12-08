package vista;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.SwingConstants;

import controlador.ControladorCarrito;
import controlador.ControladorCliente;
import controlador.ControladorProducto;
import controlador.ControladorReporte;

import java.awt.Insets;

public class VistaSinLayout extends JFrame implements InterfazVista{

	private JTable tblProducto;
	private JTable tblCliente;
	private JTable tblCarrito;
	
	private JTextField txtTotal;

	private JButton btnInsertarProducto;
	private JButton btnActualizarProducto;
	private JButton btnInsertarCliente;
	private JButton btnAgregarAlCarrito;
	private JButton btnBorrarItem;
	private JButton btnLimpiarCarrito;
	private JButton btnFinalizarCompra;

	public VistaSinLayout() {
		setBounds(175, 175, 700, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle("Carrito de Compras");
		setResizable(false);
		
		// TABLAS
		
		//TABLA PRODUCTOS		
		JScrollPane scrollPaneTablaProducto = new JScrollPane();
		scrollPaneTablaProducto.setBounds(24, 58, 388, 170);
		getContentPane().add(scrollPaneTablaProducto);
		tblProducto = new JTable();
		scrollPaneTablaProducto.setViewportView(tblProducto);
		
		
		//TABLA CLIENTES		
		JScrollPane scrollPaneTablaCliente = new JScrollPane();
		scrollPaneTablaCliente.setBounds(438, 58, 220, 211);
		getContentPane().add(scrollPaneTablaCliente);
		tblCliente = new JTable();
		scrollPaneTablaCliente.setViewportView(tblCliente);
		
		
		//TABLA CARRITO COMPRAS	
		JScrollPane scrollPaneTablaCarrito = new JScrollPane();
		scrollPaneTablaCarrito.setBounds(21, 300, 499, 121);
		getContentPane().add(scrollPaneTablaCarrito);
		tblCarrito = new JTable();
		scrollPaneTablaCarrito.setViewportView(tblCarrito);

		
		// TOTAL DEL CARRITO
		txtTotal = new JTextField("0.00");
		txtTotal.setEditable(false);
		txtTotal.setBounds(470, 432, 50, 20);
		getContentPane().add(txtTotal);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setBounds(410, 432, 50, 20);
		getContentPane().add(lblTotal);

		
		// BOTONES
		
		//BOTON INSERTAR PRODUCTO
		btnInsertarProducto = new JButton(this.INSERTAR_PRODUCTO);		
		btnInsertarProducto.setBounds(21, 26, 201, 21);		
		getContentPane().add(btnInsertarProducto);

		//BOTON ACTUALIZAR PRODUCTO
		btnActualizarProducto = new JButton(this.ACTUALIZAR_PRODUCTO);		
		btnActualizarProducto.setBounds(230, 26, 182, 21);
		getContentPane().add(btnActualizarProducto);
		
		//BOTON INSERTAR CLIENTE
		btnInsertarCliente = new JButton(INSERTAR_CLIENTE);
		btnInsertarCliente.setBounds(438, 26, 220, 21);
		getContentPane().add(btnInsertarCliente);

		// BOTON AGREGAR AL CARRITO
		btnAgregarAlCarrito = new JButton(AGREGAR_CARRITO);
		btnAgregarAlCarrito.setBounds(21, 233, 391, 36);
		getContentPane().add(btnAgregarAlCarrito);
		
		// BOTON BORRAR ITEM
		btnBorrarItem = new JButton(this.BORRAR_ITEM);
		btnBorrarItem.setBounds(530, 304, 128, 21);
		getContentPane().add(btnBorrarItem);
		
		// BOTON LIMPIAR CARRITO
		btnLimpiarCarrito = new JButton(LIMPIAR_CARRITO);
		btnLimpiarCarrito.setBounds(530, 328, 128, 21);
		getContentPane().add(btnLimpiarCarrito);
		
		//BOTON FINALIZAR COMPRA
		btnFinalizarCompra = new JButton(FINALIZAR_COMPRA);
		btnFinalizarCompra.setMargin(new Insets(2, 10, 2, 10));
		btnFinalizarCompra.setBounds(530, 360, 128, 61);
		getContentPane().add(btnFinalizarCompra);	
	}
	
	// METODOS	

	// SETEAR CONTROLADORES
	@Override
	public void setControladorProducto(ControladorProducto controladorProducto) {
		btnInsertarProducto.addActionListener(controladorProducto);
		btnActualizarProducto.addActionListener(controladorProducto);
	}
	
	@Override
	public void setControladorCliente(ControladorCliente controladorCliente) {
		btnInsertarCliente.addActionListener(controladorCliente);
	}
	
	@Override
	public void setControladorCarrito(ControladorCarrito controladorCarrito) {
		btnAgregarAlCarrito.addActionListener(controladorCarrito);
		btnBorrarItem.addActionListener(controladorCarrito);
		btnLimpiarCarrito.addActionListener(controladorCarrito);
		btnFinalizarCompra.addActionListener(controladorCarrito);
	}

	@Override
	public void setBotonActionCommand() {
		btnInsertarProducto.setActionCommand(INSERTAR_PRODUCTO);
		btnActualizarProducto.setActionCommand(ACTUALIZAR_PRODUCTO);
		btnInsertarCliente.setActionCommand(INSERTAR_CLIENTE);
		btnAgregarAlCarrito.setActionCommand(AGREGAR_CARRITO);
		btnBorrarItem.setActionCommand(BORRAR_ITEM);
		btnLimpiarCarrito.setActionCommand(LIMPIAR_CARRITO);
		btnFinalizarCompra.setActionCommand(FINALIZAR_COMPRA);
	}
	
	public void setTotal(String total){
		txtTotal.setText(total);
	}
	
	// METODOS DE MENSAJES DE DIALOGO
	public void errorOperacion(String causaError, String tituloError) {
        JOptionPane.showMessageDialog(null, causaError, tituloError, JOptionPane.ERROR_MESSAGE);
	}
	
	public void operacionCorrecta(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	//METODOS PRIVADOS PARA OBTENER FILAS SELECCIONADAS
	public boolean isItemSeleccionado(String operacion) {
		if (operacion.equals(ACTUALIZAR_PRODUCTO) || operacion.equals(AGREGAR_CARRITO)){
			return -1 != tblProducto.getSelectedRow(); //-1 cuando no hay filas seleccionadas
		}else if (operacion.equals(BORRAR_ITEM)){
			return -1 != tblCarrito.getSelectedRow(); 
		}else if (operacion.equals(FINALIZAR_COMPRA)){
			return -1 != tblCliente.getSelectedRow(); 
		}else return false;
	}
	
	public Object getItemSeleccionado(String operacion){
		if (operacion.equals(ACTUALIZAR_PRODUCTO) || operacion.equals(AGREGAR_CARRITO)){
			int filaSeleccionada = tblProducto.getSelectedRow();
			return tblProducto.getValueAt(filaSeleccionada, -1); // "-1" trae objeto entero.
		} else if(operacion == this.BORRAR_ITEM){
			int filaSeleccionada = tblCarrito.getSelectedRow();
			return tblCarrito.getValueAt(filaSeleccionada, -1);
		}else if(operacion.equals(this.FINALIZAR_COMPRA)){
			int filaSeleccionada = tblCliente.getSelectedRow();
			return tblCliente.getValueAt(filaSeleccionada, -1);
		}else return null;
	}

	// METODOS DE SETEAR Y ACTUALIZAR TABLAS
	public void setearCarrito(AbstractTableModel tabla) {
		tblCarrito.setModel(tabla);
		TableColumnModel columnModelCarrito = tblCarrito.getColumnModel();
		columnModelCarrito.getColumn(0).setPreferredWidth(35);
		columnModelCarrito.getColumn(1).setPreferredWidth(140);
		columnModelCarrito.getColumn(2).setPreferredWidth(40);
		columnModelCarrito.getColumn(3).setPreferredWidth(20);
		columnModelCarrito.getColumn(4).setPreferredWidth(35);
		columnModelCarrito.getColumn(5).setPreferredWidth(20);
	}

	public void setearProductos(AbstractTableModel tabla) {
		tblProducto.setModel(tabla);
		TableColumnModel columnModelProducto = tblProducto.getColumnModel();
		columnModelProducto.getColumn(0).setPreferredWidth(20);
		columnModelProducto.getColumn(1).setPreferredWidth(200);
		columnModelProducto.getColumn(2).setPreferredWidth(50);
	}

	public void setearClientes(AbstractTableModel tabla) {
		tblCliente.setModel(tabla);
		TableColumnModel columnModelCliente = tblCliente.getColumnModel();
		columnModelCliente.getColumn(0).setPreferredWidth(20);
		columnModelCliente.getColumn(1).setPreferredWidth(100);
	}

	@Override
	public void setControladorReporte(ControladorReporte controladorReporte) {
		// TODO Auto-generated method stub
		
	}	
}