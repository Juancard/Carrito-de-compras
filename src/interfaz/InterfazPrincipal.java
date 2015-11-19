package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JOptionPane;
import javax.swing.JButton;

import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumnModel;

import carrito.CarritoCompras;
import carrito.Cliente;
import carrito.ClienteBD;
import carrito.DetalleVenta;
import carrito.Producto;
import carrito.ProductoBD;
import carrito.Venta;
import carrito.VentaBD;
import javax.swing.SwingConstants;
import java.awt.Insets;

public class InterfazPrincipal extends JFrame{
		
	private VentaBD ventaBD;
	private ProductoBD productoBD;
	private ClienteBD clienteBD;
	private CarritoCompras carritoCompras;
	
	VentanaInsertarCliente ventanaInsertarCliente;
	VentanaInsertarProducto ventanaInsertarProducto;
	VentanaActualizarProducto ventanaActualizarProducto;
	VentanaAgregarCarrito ventanaAgregarCarrito;
	
	private TablaModel<Producto> tablaProductos;
	private JTable tblProducto;
	private TablaModel<Cliente> tablaClientes;
	private JTable tblCliente;
	private TablaCarrito tablaCarrito;
	private JTable tblCarrito;
	private JTextField txtTotal;

	/**
	 * Menu de Interfaz.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazPrincipal window = new InterfazPrincipal();
					window.setVisible(true);  
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Arma la aplicación
	 */
	public InterfazPrincipal() {
		inicializarClases();
		initialize();
	}

	private void inicializarClases() {
		productoBD = new ProductoBD();
		clienteBD = new ClienteBD();
		ventaBD = new VentaBD();
		carritoCompras = new CarritoCompras();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(175, 175, 700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle("Carrito de Compras");
		setResizable(false);
		
		// TABLAS
		
		//TABLA PRODUCTOS		
		JScrollPane scrollPaneTablaProducto = new JScrollPane();
		scrollPaneTablaProducto.setBounds(24, 58, 388, 170);
		getContentPane().add(scrollPaneTablaProducto);
		
		tablaProductos = new TablaModel<Producto>("carrito.Producto",productoBD.getProductos());
		tblProducto = new JTable(tablaProductos);
		scrollPaneTablaProducto.setViewportView(tblProducto);
		
		TableColumnModel columnModelProducto = tblProducto.getColumnModel();
		columnModelProducto.getColumn(0).setPreferredWidth(20);
		columnModelProducto.getColumn(1).setPreferredWidth(200);
		columnModelProducto.getColumn(2).setPreferredWidth(50);
		
		
		//TABLA CLIENTES		
		JScrollPane scrollPaneTablaCliente = new JScrollPane();
		scrollPaneTablaCliente.setBounds(438, 58, 220, 211);
		getContentPane().add(scrollPaneTablaCliente);
		
		tablaClientes = new TablaModel<Cliente>("carrito.Cliente",clienteBD.getClientes());
		tblCliente = new JTable(tablaClientes);
		scrollPaneTablaCliente.setViewportView(tblCliente);
				
		TableColumnModel columnModelCliente = tblCliente.getColumnModel();
		columnModelCliente.getColumn(0).setPreferredWidth(20);
		columnModelCliente.getColumn(1).setPreferredWidth(100);
		
		
		//TABLA CARRITO COMPRAS	
		JScrollPane scrollPaneTablaCarrito = new JScrollPane();
		scrollPaneTablaCarrito.setBounds(21, 300, 499, 121);
		getContentPane().add(scrollPaneTablaCarrito);
		
		tablaCarrito = new TablaCarrito(carritoCompras.getCarrito());
		tblCarrito = new JTable(tablaCarrito);
		scrollPaneTablaCarrito.setViewportView(tblCarrito);
		
		TableColumnModel columnModelCarrito = tblCarrito.getColumnModel();
		columnModelCarrito.getColumn(0).setPreferredWidth(35);
		columnModelCarrito.getColumn(1).setPreferredWidth(140);
		columnModelCarrito.getColumn(2).setPreferredWidth(40);
		columnModelCarrito.getColumn(3).setPreferredWidth(20);
		columnModelCarrito.getColumn(4).setPreferredWidth(35);
		columnModelCarrito.getColumn(5).setPreferredWidth(20);
		
		
		// TOTAL DEL CARRITO
		txtTotal = new JTextField("0.0");
		txtTotal.setEditable(false);
		txtTotal.setBounds(470, 432, 50, 20);
		getContentPane().add(txtTotal);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setBounds(410, 432, 50, 20);
		getContentPane().add(lblTotal);

		
		// BOTONES
		
		//BOTON INSERTAR PRODUCTO
		JButton btnInsertarProducto = new JButton("Insertar Producto");		
		btnInsertarProducto.setBounds(21, 26, 201, 21);		
		getContentPane().add(btnInsertarProducto);
		btnInsertarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaInsertarProducto = new VentanaInsertarProducto(InterfazPrincipal.this);
				ventanaInsertarProducto.setVisible(true);
			}
		});

		//BOTON ACTUALIZAR PRODUCTO
		JButton btnActualizarProducto = new JButton("Actualizar Producto");
		btnActualizarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto p = getFilaProductoSeleccionada();	
				if (p != null){
					ventanaActualizarProducto = new VentanaActualizarProducto(InterfazPrincipal.this);
					ventanaActualizarProducto.setTxtCodigo(p.getCodigoProducto());
					ventanaActualizarProducto.setTxtDescripcion(p.getDescripcion());
					ventanaActualizarProducto.setTxtPrecio(p.getPrecio());
					ventanaActualizarProducto.setVisible(true);
				}
			}
		});
		btnActualizarProducto.setBounds(230, 26, 182, 21);
		getContentPane().add(btnActualizarProducto);
		
		//BOTON INSERTAR CLIENTE
		JButton btnInsertarCliente = new JButton("Insertar Cliente");
		btnInsertarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaInsertarCliente = new VentanaInsertarCliente(InterfazPrincipal.this);
				ventanaInsertarCliente.setVisible(true);
			}
		});
		btnInsertarCliente.setBounds(438, 26, 220, 21);
		getContentPane().add(btnInsertarCliente);

		// BOTON AGREGAR AL CARRITO
		JButton btnAgregarAlCarrito = new JButton("Agregar al carrito");
		btnAgregarAlCarrito.setBounds(21, 233, 391, 36);
		getContentPane().add(btnAgregarAlCarrito);
		btnAgregarAlCarrito.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Producto p = getFilaProductoSeleccionada();
				if (p != null){
					DetalleVenta dv = new DetalleVenta();
					dv.setProducto(p);
					ventanaAgregarCarrito = new VentanaAgregarCarrito(InterfazPrincipal.this, dv);
					ventanaAgregarCarrito.setVisible(true);
				}
			}
		});
		
		
		// BOTON BORRAR ITEM
		JButton btnBorrarItem = new JButton("Borrar Item");
		btnBorrarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DetalleVenta dv = getItemCarritoSeleccionado();
				if (dv != null){
					carritoCompras.borrarItem(dv.getCodigoProducto());
					InterfazPrincipal.this.setTotal();
					tablaCarrito.actualizarTabla();
				}
			}
		});
		btnBorrarItem.setBounds(530, 304, 128, 21);
		getContentPane().add(btnBorrarItem);
		
		// BOTON LIMPIAR CARRITO
		JButton btnLimpiarCarrito = new JButton("Limpiar Carrito");
		btnLimpiarCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				carritoCompras.borrarCarrito();
				InterfazPrincipal.this.setTotal();
				tablaCarrito.actualizarTabla();
			}
		});
		btnLimpiarCarrito.setBounds(530, 328, 128, 21);
		getContentPane().add(btnLimpiarCarrito);
		
		//BOTON FINALIZAR COMPRA
		JButton btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.setMargin(new Insets(2, 10, 2, 10));
		btnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (carritoCompras.getCarrito().isEmpty()){
					JOptionPane.showMessageDialog(InterfazPrincipal.this, 
							"Carrito Vacio: Seleccione un Producto de la Lista", 
							"Finalizar Compra", 
							JOptionPane.ERROR_MESSAGE);
				} else{
					Cliente c = getClienteSeleccionado();
					if (c != null){
						agregarVentaBD(c.getCodigoCliente());
					}
				}
			}
		});
		btnFinalizarCompra.setBounds(530, 360, 128, 61);
		getContentPane().add(btnFinalizarCompra);
		
	}
	
	// METODOS
	
	// MÉTODOS PARA INTERACTUAR CON PRODUCTO
	public boolean insertarProductoBD(String descripcion, Double precio){
		Producto p = new Producto();
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		if (productoBD.insertarProducto(p)){
			tablaProductos.actualizarTabla(productoBD.getProductos());
			return true;
		} else return false;
	}
			
	public boolean actualizarProductoBD(int codigo, String descripcion, Double precio) {
		Producto p = new Producto();
		p.setCodigoProducto(codigo);
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		if (productoBD.actualizarProducto(p)){
			tablaProductos.actualizarTabla(productoBD.getProductos());
			return true;
		} else return false;
	}
	
	// MÉTODOS PARA INTERACTUAR CON CLIENTE
	public boolean insertarClienteBD(String nombre){
		Cliente c = new Cliente();
		c.setNombre(nombre);
		if (clienteBD.insertarCliente(c)){
			tablaClientes.actualizarTabla(clienteBD.getClientes());
			return true;
		}else return false;
	}
		
	// MÉTODOS PARA INTERACTUAR CON CARRITO
	public void agregarItemCarrito(DetalleVenta dv){
		carritoCompras.agregarItem(dv);
		this.setTotal();
		tablaCarrito.actualizarTabla();
	}

	public void setTotal(){
		txtTotal.setText(String.valueOf(carritoCompras.getTotalCarrito()));
	}
	
	// AGREGAR VENTA A BASE DE DATOS
	public void agregarVentaBD (int codigoCliente){
		Venta venta = new Venta();
		venta.setCodigoCliente(codigoCliente);
		if (ventaBD.insertarVenta(venta, carritoCompras.getCarrito())){
			JOptionPane.showMessageDialog(InterfazPrincipal.this, "¡Su compra ha sido realizada con éxito!");
		}
	}
	
	//METODOS PRIVADOS PARA OBTENER FILAS SELECCIONADAS
	private Producto getFilaProductoSeleccionada(){
		Producto p = null;
		try{
			int filaSeleccionada = tblProducto.getSelectedRow();
			p = (Producto)tblProducto.getValueAt(filaSeleccionada, -1); // "-1" trae objeto entero.

		} catch(Exception exc){
			JOptionPane.showMessageDialog(InterfazPrincipal.this, "Debe Seleccionar un Producto", "Seleccionar Producto", JOptionPane.ERROR_MESSAGE);
		}	
		return p;
	}
	private DetalleVenta getItemCarritoSeleccionado(){
		DetalleVenta dv = null;
		try{
			int filaSeleccionada = tblCarrito.getSelectedRow();
			dv = (DetalleVenta)tblCarrito.getValueAt(filaSeleccionada, -1); 

		} catch(Exception exc){
			JOptionPane.showMessageDialog(InterfazPrincipal.this, "Debe Seleccionar un Item del Carrito", "Borrar Item", JOptionPane.ERROR_MESSAGE);
		}	
		return dv;
	}
	
	private Cliente getClienteSeleccionado(){
		Cliente cliente = null;
		try{
			int filaSeleccionada = tblCliente.getSelectedRow();
			cliente = (Cliente)tblCliente.getValueAt(filaSeleccionada, -1); 

		} catch(Exception exc){
			JOptionPane.showMessageDialog(InterfazPrincipal.this, "Debe Seleccionar un Cliente", "Finalizar Compra", JOptionPane.ERROR_MESSAGE);
		}	
		return cliente;
	}

}
