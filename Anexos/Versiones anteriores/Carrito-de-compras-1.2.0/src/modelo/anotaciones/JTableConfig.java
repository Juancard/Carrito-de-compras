package modelo.anotaciones;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Anotación utilizada para cambiar la configuración de la JTable
 * 
 * Atributos:
 * Nombre: Cambia el nombre de la columna por el especificado
 */

@Documented //Para que anotacion sea tenida en cuenta en JavaDoc
@Retention(RetentionPolicy.RUNTIME)  //puede ser accedida por otra clase en runtime (permite reflection)
@Target(ElementType.FIELD) //alcance de anotación

public @interface JTableConfig {
	String nombre() default ""; //parametro opcional
}
