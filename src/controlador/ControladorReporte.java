package controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import modelo.ConexionUtility;
import vista.InterfazVista;

public class ControladorReporte implements ActionListener {

	private InterfazVista vista;

	public ControladorReporte(InterfazVista v) {
		this.vista = v;
		vista.setControladorReporte(this);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		String accion = evento.getActionCommand();
		if (accion.equals(vista.HISTORICO_VENTAS)){
			String rutaProyecto = System.getProperty("user.dir");
	        String rutaArchivo = rutaProyecto + "/reporte/historicoVentas.jasper";
	        HashMap hm = new HashMap();
	        Connection conexion = ConexionUtility.getConexion();
	        try {
	            JasperPrint print = JasperFillManager.fillReport(rutaArchivo, hm, conexion);
	            abrirReporte(accion,print);
	            ConexionUtility.cerrarConexion(conexion);
	        } catch (JRException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
	}

	private void abrirReporte(String accion, JasperPrint print) {
		if (accion.equals(vista.HISTORICO_VENTAS)){
            JFrame ventana = new JFrame("Reporte - "+accion);
            ventana.getContentPane().add(new JRViewer(print), BorderLayout.CENTER);
            ventana.setMinimumSize(new Dimension(900, 500));
            ventana.setLocationRelativeTo(null);
            ventana.setDefaultCloseOperation(ventana.DISPOSE_ON_CLOSE);
            ventana.pack();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
		}
	}
}
