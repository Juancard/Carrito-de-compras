package vista;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import controlador.ControladorCarrito;
import controlador.ControladorCliente;
import controlador.ControladorProducto;
import controlador.ControladorReporte;

import javax.swing.border.LineBorder;

public class VistaConLayout extends JFrame implements InterfazVista{
	private JTable tblCliente;
	private JTable tblCarrito;
	private JTable tblProducto;
	
	private JButton btnActualizarProducto;
	private JButton btnInsertarProducto;
	private JButton btnAgregarCarrito;
	private JButton btnInsertarCliente;
	private JButton btnFinalizarCompra;
	private JButton btnLimpiarCarrito;
	private JButton btnBorrarItem;
	private JButton btnHistoricoVentas;
	
	private JTextField txtTotal;

	public VistaConLayout() {
		setTitle("Carrito de Compras v2.0.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(700,400));
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		getContentPane().add(panelPrincipal);
		
		JPanel panelCentro = new JPanel(new BorderLayout());
		panelPrincipal.add(panelCentro, BorderLayout.CENTER);
		
		// PANEL PRODUCTO
		JPanel panelProducto = new JPanel(new BorderLayout());
		panelProducto.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64)), "Productos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelProducto.setPreferredSize(new Dimension(500,300));
		panelCentro.add(panelProducto, BorderLayout.CENTER);

		JPanel botonesProducto = new JPanel(new BorderLayout());		
		panelProducto.add(botonesProducto,BorderLayout.NORTH);
		
		btnInsertarProducto = new JButton(INSERTAR_PRODUCTO);
		botonesProducto.add(btnInsertarProducto, BorderLayout.CENTER);
		
		btnActualizarProducto = new JButton(ACTUALIZAR_PRODUCTO);		
		botonesProducto.add(btnActualizarProducto, BorderLayout.EAST);
		
		tblProducto = new JTable();
		JScrollPane scrollPaneTablaProducto = new JScrollPane(tblProducto);
		panelProducto.add(scrollPaneTablaProducto, BorderLayout.CENTER);
		
		JPanel panelEste = new JPanel(new BorderLayout());
		panelPrincipal.add(panelEste, BorderLayout.EAST);
		
		//PANEL CLIENTE
		JPanel panelCliente = new JPanel(new BorderLayout());
		panelCliente.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64)), "Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCliente.setPreferredSize(new Dimension(300,300));
		panelEste.add(panelCliente, BorderLayout.CENTER);	
		
		btnInsertarCliente = new JButton(INSERTAR_CLIENTE);
		panelCliente.add(btnInsertarCliente, BorderLayout.NORTH);
		
		tblCliente = new JTable();
		JScrollPane scrollPaneTablaCliente = new JScrollPane(tblCliente);
		panelCliente.add(scrollPaneTablaCliente, BorderLayout.CENTER);
		tblCliente.setFillsViewportHeight(true);
		
		btnAgregarCarrito = new JButton(AGREGAR_CARRITO);
		JPanel panelAgregarCarrito = new JPanel(new BorderLayout());
		panelAgregarCarrito.add(btnAgregarCarrito, BorderLayout.CENTER);
		panelAgregarCarrito.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64)), "Agregar Item", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCentro.add(panelAgregarCarrito,BorderLayout.SOUTH);
		
		// PANEL CARRITO
		JPanel panelCarrito = new JPanel(new BorderLayout());
		panelPrincipal.add(panelCarrito, BorderLayout.SOUTH);	
		
		JPanel panelCarritoCentro = new JPanel(new BorderLayout());
		panelCarrito.add(panelCarritoCentro,BorderLayout.CENTER);
		panelCarritoCentro.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64)), "Carrito", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JPanel panelBotonesCarrito = new JPanel(new BorderLayout());
		panelCarritoCentro.add(panelBotonesCarrito, BorderLayout.SOUTH);
		
		btnBorrarItem = new JButton(BORRAR_ITEM);
		panelBotonesCarrito.add(btnBorrarItem, BorderLayout.WEST);
		
		btnLimpiarCarrito = new JButton(LIMPIAR_CARRITO);
		panelBotonesCarrito.add(btnLimpiarCarrito, BorderLayout.EAST);
		
		btnFinalizarCompra = new JButton(FINALIZAR_COMPRA);
		panelBotonesCarrito.add(btnFinalizarCompra, BorderLayout.CENTER);

		tblCarrito = new JTable();
		JScrollPane scrollPaneTablaCarrito = new JScrollPane(tblCarrito);
		panelCarritoCentro.setPreferredSize(new Dimension(300,170));
		panelCarritoCentro.add(scrollPaneTablaCarrito,BorderLayout.CENTER);

		JPanel panelCarritoSur = new JPanel(new BorderLayout());
		panelCarrito.add(panelCarritoSur, BorderLayout.SOUTH);
		panelCarritoSur.setBorder(BorderFactory.createEtchedBorder());
		txtTotal = new JTextField("0.00");
		txtTotal.setEditable(false);
		JLabel lblTotal = new JLabel("Total del Carrito: ");
		panelCarritoSur.add(txtTotal, BorderLayout.CENTER);
		panelCarritoSur.add(lblTotal, BorderLayout.WEST);
				
		// PANEL REPORTES
		JPanel panelReportes = new JPanel(new BorderLayout());
		panelEste.add(panelReportes,BorderLayout.SOUTH);
		panelReportes.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64)), "Reportes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		btnHistoricoVentas = new JButton(HISTORICO_VENTAS);
		panelReportes.add(btnHistoricoVentas, BorderLayout.CENTER);
		
		this.pack();
		setLocationRelativeTo(null); // para ubicar la ventana en el centro de la pantalla.
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
		btnAgregarCarrito.addActionListener(controladorCarrito);
		btnBorrarItem.addActionListener(controladorCarrito);
		btnLimpiarCarrito.addActionListener(controladorCarrito);
		btnFinalizarCompra.addActionListener(controladorCarrito);
	}
	
	@Override
	public void setControladorReporte(ControladorReporte controladorReporte) {
		btnHistoricoVentas.addActionListener(controladorReporte);
	}
	
	@Override
	public void setBotonActionCommand() {
		btnInsertarProducto.setActionCommand(INSERTAR_PRODUCTO);
		btnActualizarProducto.setActionCommand(ACTUALIZAR_PRODUCTO);
		btnInsertarCliente.setActionCommand(INSERTAR_CLIENTE);
		btnAgregarCarrito.setActionCommand(AGREGAR_CARRITO);
		btnBorrarItem.setActionCommand(BORRAR_ITEM);
		btnLimpiarCarrito.setActionCommand(LIMPIAR_CARRITO);
		btnFinalizarCompra.setActionCommand(FINALIZAR_COMPRA);
		btnHistoricoVentas.setActionCommand(HISTORICO_VENTAS);
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
		} else if(operacion.equals(BORRAR_ITEM)){
			int filaSeleccionada = tblCarrito.getSelectedRow();
			return tblCarrito.getValueAt(filaSeleccionada, -1);
		}else if(operacion.equals(FINALIZAR_COMPRA)){
			int filaSeleccionada = tblCliente.getSelectedRow();
			return tblCliente.getValueAt(filaSeleccionada, -1);
		}else return null;
	}

	// METODOS DE SETEAR Y ACTUALIZAR TABLAS
	public void setearCarrito(AbstractTableModel modeloTabla) {
		tblCarrito.setModel(modeloTabla);
		this.setearMaximoTamanoColumnas(tblCarrito, modeloTabla);
	}

	public void setearProductos(AbstractTableModel modeloTabla) {
		tblProducto.setModel(modeloTabla);
		this.setearMaximoTamanoColumnas(tblProducto, modeloTabla);
	}

	public void setearClientes(AbstractTableModel modeloTabla) {
		tblCliente.setModel(modeloTabla);
		this.setearMaximoTamanoColumnas(tblCliente, modeloTabla);
	}	
	
	private void setearMaximoTamanoColumnas(JTable tabla, AbstractTableModel modelo){
		TableColumnModel columnModel = tabla.getColumnModel();
		int cantCol = modelo.getColumnCount();
		if (modelo.getRowCount()>0){
			for (int i=0; i<cantCol; i++){
				Object o = modelo.getValueAt(0, i);
				if (o instanceof String){
					columnModel.getColumn(i).setMinWidth(200);
				}
			}
		}else{ // ALERTA - PARCHE
			for (int i=0; i<cantCol; i++){
				if (columnModel.getColumn(i).getHeaderValue().toString().equalsIgnoreCase("Descripcion")){
					columnModel.getColumn(i).setMinWidth(200);
				}
			}		
		}
	}
}
