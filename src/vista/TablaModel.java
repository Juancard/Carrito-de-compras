package vista;
/*
 * Implementación de modelos de tablas utilizando Reflection
 * Bibliografia utilizada: "Introduccion al uso de JPA 2 genérico" de Guillermo Cherencio.
 * 
 */
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import modelo.anotaciones.JTableConfig;
	
@SuppressWarnings("unchecked") // Permitir casteo a genérico
public class TablaModel<T> extends AbstractTableModel{

	private ArrayList<T> lista;
	private String nombreColumnas[];
	private Method getters[];
	

	public TablaModel(String nombreGenerico, ArrayList<T> arreglo){
		lista = arreglo;
		reflexion(nombreGenerico); // hago reflection
	}
	
	public int getColumnCount() {
		return nombreColumnas.length;
	}

	public int getRowCount() {
		return lista.size();
	}
	
	public Object getValueAt(int fila, int columna) {
		T objetoLista = lista.get(fila);
		if (columna == -1){
			return objetoLista;
		} else{
			try {
				if (getters[columna] != null){
					return getters[columna].invoke(objetoLista);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	public String getColumnName(int columna){
        return nombreColumnas[columna];
    }
	
	public boolean isCellEditable(int fila, int columna){
        return false;
    }
	
	public void actualizarTabla(ArrayList<T> nuevaLista){
		if (nuevaLista != null){
			lista = nuevaLista;
		}
		fireTableDataChanged();
	}
	
	// METODO PRIVADO QUE REALIZA REFLECTION
	
	private void reflexion(String nombreGenerico) {
		T objeto = null;
		try {
			objeto = (T) Class.forName(nombreGenerico).newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		Class clase = objeto.getClass();
		int cantColumnas = clase.getDeclaredFields().length;
		nombreColumnas = new String[cantColumnas];
		getters = new Method[cantColumnas];
		int i = 0;
		for (Field f : clase.getDeclaredFields()){
			nombreColumnas[i] = f.getName();
			getters[i] = null;
			for (Annotation a : f.getAnnotations()){
				if (a.toString().startsWith("@modelo.anotaciones.JTableConfig(")){
					nombreColumnas[i] = ((JTableConfig) a).nombre();
				}
			}
			for (Method m : clase.getDeclaredMethods()){
				if (m.getName().equalsIgnoreCase("get"+f.getName())){
					getters[i] = m;
				}
			}
			i++;
		} 			
	}

}
