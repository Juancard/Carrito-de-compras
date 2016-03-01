# Carrito de compras


El proyecto consiste en una aplicación Java tipo carrito compras que se conecta a una base de datos relacional para efectivizar ventas a clientes.

Dicha aplicación cuenta con una interfaz gráfica de usuario y distintas funcionalidades como la de insertar y actualizar productos en venta y clientes al sistema, aplicar descuentos a pedidos y añadir y eliminar productos en el carrito.

La aplicación también es capaz de emitir reportes sobre las ventas llevadas a cabo en la empresa a lo largo de su actividad.

En cuanto al código fuente del software presentado cumple con el patrón de arquitectura modelo-vista-controlador para facilitar futuras implementaciones de cada capa. También cuenta con testeos de las clases del modelo utilizados durante el desarrollo.

![interface_usuario](http://i66.tinypic.com/33dhywz.png)

-----------

## Instalación

1. Restaurar en PostgreSQL la base de datos "carrito.sql" ubicada [Aquí](https://github.com/Juancard/Carrito-de-compras/tree/master/bd) 
   
   Con las siguientes credenciales de usuario:
   ```bash
	User: postgres
	Pass: postgres
   ```
   
    Desde terminal:
    ```bash
	$ psql
	$ create database carrito;
	$ \q
	$ psql -U postgres -d carrito -f [ruta/a/bd]/carrito.sql
    ```

2. Importa el código fuente del proyecto ubicada [Aquí](https://github.com/Juancard/Carrito-de-compras/tree/master/src)

	Nota: Necesario JDK7 o superior.

3. Descarga y agrega al proyecto los siguientes jars necesarios para correr la aplicación:

  - Para base de datos: postgresql-9.4-1202.jdbc4.
  - Para generar reportes: commons-beanutils-1.9.0; commons-collections-3.2.1; commons-digester-2.1; commons-logging-1.1.1; groovy-all-2.4.3; itext-2.1.7.js4; jasperreports-6.2.0; jfreechart-1.0.19; poi-3.10.1.
 
   Ejemplo de configuración en Eclipse IDE:
   - Click derecho en tu proyecto CarritoCompras y seleccionar 
     Build Path->Configure Build Path.
   - En la ventana emergente seleccionar la pestaña Libraries.
   - Click en Add external Jars y seleccionar los JARs necesarios
   - Dar click a Ok.


-----------

## Introducción

El presente proyecto consiste en un sistema de ventas tipo carrito de compras para la realización de transacciones comerciales. 
Dicho software brindará al usuario la posibilidad de seleccionar de una serie de productos a la venta los ítems aquellos que se desea adquirir y de esta manera confeccionar el pedido de un cliente en particular también seleccionado por el usuario.

Asimismo, tanto los clientes del sistema como los productos en venta pueden ser actualizados cuando se lo solicite así como también pueden ser añadidos al sistema nuevos productos y clientes.

Para esto, el sistema cuenta con una base de datos relacional asociada que almacena los datos necesarios para la realización de dichas tareas. 

También podrá el usuario contar con una interfaz gráfica para un uso simple y de fácil comprensión con el cual interactuar de manera eficiente y clara.

Asimismo, el usuario podrá realizar solicitudes de reportes sobre las ventas realizadas desde el comienzo de las actividades de la organización pudiendo acceder a datos estadísticos como los productos vendidos o las sumas abonadas por los clientes.

Además, el código fuente se encontrará dividido en 3 capas: modelo, vista y controlador. De esta manera se separa la lógica de negocios de su interfaz gráfica lo cual permite la reutilización de código y escalabilidad.

Por último, se proveerá de una serie de testeos realizados sobre el código fuente del proyecto que fueron implementadas durante el proceso de desarrollo del software en base a lo recomendado por la  metodología de programación ágil.

El código fuente del proyecto ha sido almacenado en repositorios web a lo largo de su desarrollo y se ha llevado a cabo un control de versiones las cuales se han publicado en servicios web a medida que se han completado las distintas etapas del proyecto.

--------

## Infraestructura y arquitectura tecnológica

El proyecto ha sido desarrollado utilizando como dispositivo de cabecera una notebook Lenovo G480 con 4 Gb de memoria y procesador Intel I3. Pantalla de 14 pulgadas.

El Sistema Operativo utilizado durante el desarrollo ha sido Windows 8.1 de 64bits, aunque también ha sido testeado en Windows 7 y algunas distribuciones de Linux como Debian 7 y Ubuntu 11.

Se desarrolla en lenguaje JAVA utilizando la plataforma Eclipse Kepler versión 2015 con el paquete JDK SE 7.

El sistema gestor de base de datos seleccionado para el proyecto fue PostgreSQL 9.4 utilizando pgAdmin III como interfaz gráfica. La conexión a dicha base de datos se realiza mediante los drivers JDBC para PostgreSQL.

La interfaz gráfica fue desarrollada utilizando la biblioteca gráfica de Swing con el apoyo de WindowBuilder, un plug in facilitado por la plataforma Eclipse.

El diagrama de clases se confecciona con la herramienta ObjectAid directamente desde el Eclipse. Una versión alternativa y simplificada de dicho diagrama es diseñado con la aplicación Dia. El diagrama de casos de uso fue realizado mediante Microsoft Visio y los diagramas de secuencia fueron diseñados con la herramienta Dia.

El código fuente de la aplicación será almacenado en repositorios Git publicados en GitHub herramienta con la cual también se llevará el control de versiones de la aplicación. 

Los reportes suministrados serán confeccionados utilizando el motor de reportes Jasper Reports y diseñados a partir de la aplicación de escritorio iReports 5.6.0. También se añaden al proyecto de Eclipse las librerías mínimas necesarias para la confección de reportes JasperReports.

Finalmente, el desarrollo de clases de testeos del modelo se llevará a cabo a través de JUnit test cases. 

-------

## Definición detallada del proyecto

### Antecedentes:

Aún existen en la actualidad organizaciones donde las actividades transaccionales o de venta se llevan a cabo de manera manual o sin el soporte de un sistema que facilite la labor de los empleados. 

Si bien de un tiempo a esta parte se ha crecido mucho en cuanto a la automatización de las tareas organizacionales, aún queda mucho por desarrollar y es de importancia que se continúe por esta senda.



### Objetivos o Própositos:

Como propósito general se busca generar nuevas soluciones de software para la automatización de actividades llevadas a cabo en organizaciones así como obtener conocimientos sobre tecnologías utilizadas en la actualidad y sus posibles implementaciones.

En particular, lo que se busca con este proyecto es lograr sistematizar las actividades de venta de una organización mediante la implementación de un software informático orientado a las transacciones comúnmente conocido como carrito de compras.

Otros objetivos son:

* Administrar y almacenar datos del sistema de manera fiable y segura a partir de la integración al proyecto de un sistema gerencial de bases de datos (SGBD).

* Implementar una interfaz gráfica para el sistema de manera tal que el usuario pueda interactuar con ella de manera clara, concisa y eficiente otorgando todas las funcionalidades necesarias y requeridas para la realización de sus actividades.

* Realizar validaciones sobre los datos ingresados por el usuario para evitar el ingreso al sistema de datos no pertinentes.

* Realizar conexiones seguras de la aplicación cliente hacia el SGBD mediante el uso de conexiones JDBC que realicen llamadas a stored procedures o vistas almacenadas en la base de datos.

* Confeccionar test de casos de uso como JUnit para un proceso de desarrollo de software afín a una metodología de programación ágil. 

* Permitir al usuario insertar y actualizar nuevos productos y clientes al sistema.

* Llevar una lista con los productos que el usuario desea adquirir permitiendo inserción, eliminación y actualización de cualquiera de los ítems agregados.

* Suministrar reportes con información detallada sobre las ventas realizadas en el curso de actividades de la organización.

* Permitir al usuario establecer descuentos a clientes sobre los productos agregados al carrito bajo parámetros establecidos por él mismo.

* Separar el código fuente de la aplicación siguiendo el patrón MVC.



### Beneficios:

De esta manera el software logra:

* Facilitar y agilizar las tareas de venta de productos realizada por el sector de ventas de una organización.

* Mantener en resguardo, de manera independiente y organizada los datos del sistema en una base de datos relacional.

* Generar una interacción simple y clara entre el usuario del sistema y la interfaz gráfica de la aplicación cliente.

* Evitar el ingreso al sistema de datos no válidos que puedan alterar los resultados de la transacción.

* Facilitar futuras evoluciones en el comportamiento del sistema mediante la mantención del código fuente a partir de la implementación de casos de prueba.



### Alcance:

Cualquier organización que realice actividades transaccionales donde existan productos en demanda con un valor determinado y clientes dispuestos a su adquisición. 

Estas organizaciones pueden ser un negocio particular con atención al cliente o en general cualquier empresa pequeña o mediana con objetivos comerciales.



### Requerimientos:

Para la implementación del proyecto se requerirá un computador encargado de almacenar la base de datos y un conjunto de computadores clientes que se conectarán a dicho computador servidor y realizaran las consultas que requieran, ya sea agregar nuevas ventas confirmadas, actualizar clientes y demás.

Las especificaciones de los computadores, tanto de servidor como cliente, quedan sujetas a la organización y lo que estime apropiado.

Dicha conexión entre computadores requerirá de la existencia de una red LAN con velocidad de conexión sujeta a las necesidades de la organización.



### Limitaciones:

El sistema no permitirá:

* Editar datos de ventas ya confirmadas.

* Confirmar pedidos a clientes que no se encuentren en la base de datos de la organización.

* Confirmar pedidos que contengan productos que no se encuentren en la base de datos de la organización.

* Editar el código unívoco de identificación establecido para un cliente o producto.

* Confeccionar pedidos a más de un cliente a la vez en una misma sesión. 

* Ingresar datos que no sean válidos según lo establecido por dominio.



### Líneas Futuras:

A futuro se podrá extender las funcionalidades del sistema para que permita:

* Llevar un control de stock sobre los productos en venta.

* Obtener un informe sobre las ventas realizadas por el sistema durante un período especificado por el usuario.

* Destinar un carrito de compras independiente para cada cliente del sistema.

* Llevar una lista con los productos que cada cliente desearía solicitar a futuro.

* Agregar a cada cliente atributos como domicilio, teléfono, E-mail y código postal.

* Agregar imágenes descriptivas del producto en venta.

* Llevar un sistema de descuentos mediante el uso de cupones de descuento.

* Definir usuarios con distintos permisos sobre el uso de la aplicación.

* Implementar un sistema de logueo y registro de usuarios.

--------

## Duración

Se trabaja sobre proyecto a horario completo en días libres y aprovechando tiempo disponible por la mañana y noche para días de cursada universitaria.

Para comenzar se instala el software necesario del cual no se disponía previamente, luego se trabaja sobre las tablas y stored procedures de la base de datos comenzando por tareas simples de inserción y actualización para luego aumentar progresivamente la complejidad.

Una vez realizado lo anterior se comienza con el desarrollo en JAVA mediante Eclipse creando una clase por cada tabla generada y sus respectivos testeos JUnit.

Más tarde se desarrollan las clases necesarias para la conexión de la aplicación con la base de datos. Para esto fue necesario documentarse sobre su implementación e instalar los drivers necesarios.

Posteriormente, se desarrolla una interfaz gráfica de usuario que contiene los botones y tablas necesarios para proveer las funcionalidades mínimas implementadas del sistema. Para esto fue necesario documentarse sobre su desarrollo e implementación ya que no se contaban con conocimientos previos de la materia.

Finalizada dicha vista se procede a trabajar sobre el código ya escrito para mejorar la eficiencia de la aplicación. Para esto se dividió lo desarrollado en tres capas: modelo, vista y controlador. 

Una vez limpio el código comienza la implementación de reportes Java a través de JasperReports lo cual requirió un breve lapso previo de aprendizaje sobre la herramienta y su puesta a punto en el proyecto.

Periódicamente se realizan back-ups tanto del código JAVA como de la base de datos del sistema en sistemas de repositorios git como GitHub, donde también se lleva a cabo el control de versiones del proyecto.

Diariamente se lleva una bitácora con información actualizada del estado del proyecto, tareas realizadas, problemáticas encontradas, soluciones a dichas problemáticas y tareas pendientes de revisión o a realizar a futuro.

----------------------------

## Diagramas


### Diagrama de clases:


![uml_carrito_v2 0 0](https://cloud.githubusercontent.com/assets/9201382/11824762/6af8f062-a35a-11e5-846c-614bc550ebc2.gif)



### Casos de uso:


![caso_de_uso_v2 0 0](https://cloud.githubusercontent.com/assets/9201382/11824722/3b4c805e-a35a-11e5-83d1-2a7c3d3e4914.png)



### Diagramas de secuencia:


![secuencia_ins_prod](https://cloud.githubusercontent.com/assets/9201382/11824787/8bf02a10-a35a-11e5-8229-a2f7831956cf.png)


![secuencia_act_prod](https://cloud.githubusercontent.com/assets/9201382/11824788/8e9c19a4-a35a-11e5-818f-ff7492125fc6.png)


![secuencia_ins_cliente](https://cloud.githubusercontent.com/assets/9201382/11824793/9675bf7c-a35a-11e5-839b-a5d47a0c4b2c.png)


![secuencia_agregar_item](https://cloud.githubusercontent.com/assets/9201382/11824806/9f967452-a35a-11e5-8154-0bc6373bedf4.png)


![secuencia_borrar_item](https://cloud.githubusercontent.com/assets/9201382/11824821/ab0f0a60-a35a-11e5-8cf3-6293b73f51ed.png)


![secuencia_limpiar_carrito](https://cloud.githubusercontent.com/assets/9201382/11824824/ad9d864e-a35a-11e5-8de7-b9af1c177208.png)


![secuencia_finalizar_compra](https://cloud.githubusercontent.com/assets/9201382/11824831/b45e777c-a35a-11e5-88dc-c218508630e8.png)


![secuencia_historico_venta](https://cloud.githubusercontent.com/assets/9201382/11824834/b7245882-a35a-11e5-95c6-7a549ec534bb.png)


------------

## Resultados esperados a la culminación del proyecto

### Medición personal del éxito alcanzado

A nivel personal se ha conseguido:

* Consolidar conceptos, metodologías y conocimientos obtenidos a lo largo de la carrera universitaria a partir de una puesta en práctica íntegra.

* Formar una primera experiencia en puesta a punto de proyectos de software.

* Utilizar herramientas, arquitecturas, técnicas y metodologías actuales de análisis, diseño, desarrollo e implementación de software.

* Realizar un desarrollo de código centrado esencialmente en una lógica de programación orientada a objetos.

* Poner en práctica de manera informal técnicas de programación extrema como casos de prueba y desarrollo incremental.



### Valoración de los beneficios del proyecto:

A partir de la realización de este proyecto se ha logrado:

* Sistematizar actividades o tareas llevadas a cabo en el mundo empresarial mediante un programa informático de manera tal de proporcionar nuevas soluciones de software para problemáticas existentes en dicho contexto.

* Llevar a cabo conexiones entre una aplicación y su base de datos gestionada por un SGBD lo cual permite una administración eficiente y segura de los datos almacenados.

* Desarrollar una interfaz gráfica de usuario que logre proveer las funcionalidades esperadas del proyecto de una manera interactiva y de fácil comprensión para el usuario de la aplicación.

* Disponer de casos de prueba lo cual proporciona mayor seguridad sobre futuras ediciones o adiciones de código a la aplicación.

* Implementar solicitudes de reportes del usuario a la aplicación para proveer de información estadística y detallada de las actividades de la organización.
