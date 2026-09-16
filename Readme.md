# Biblioteca Digital UNTEC

## 1. Descripción del proyecto

Biblioteca Digital UNTEC es una aplicación web desarrollada en Java que permite gestionar y consultar información bibliográfica. El sistema incorpora autenticación de usuarios y funcionalidades diferenciadas según el rol de acceso.

El proyecto fue desarrollado utilizando tecnologías orientadas al desarrollo web, incluyendo Java, JSP, Servlets, Maven y MySQL.

## 2. Tecnologías utilizadas

* Java 21
* Jakarta Servlet
* JSP
* Maven
* Apache Tomcat v 10.1
* Eclipse Enterprise Edition
* MySQL
* HTML5 y CSS
* Bootstrap

## 3. Requisitos

Para ejecutar el proyecto se requiere:

* JDK 21 o superior.
* Apache Tomcat compatible con Jakarta Servlet.
* MySQL Server.
* Un entorno de desarrollo compatible con proyectos Maven.
* Base de datos configurada para el sistema.

## 4. Instalación y configuración

1. Descargar o clonar el proyecto.
2. Abrir el proyecto en el entorno de desarrollo.
3. Verificar la configuración de conexión a la base de datos en la clase correspondiente.
4. Crear y configurar la base de datos MySQL utilizada por el sistema.
5. Ejecutar el proyecto mediante Apache Tomcat.
6. Acceder a la aplicación desde el navegador utilizando la dirección proporcionada por el servidor.

## 5. Instrucciones de uso

### Inicio de sesión

Al ingresar a la aplicación, el usuario debe autenticarse mediante sus credenciales.

El sistema valida la sesión antes de permitir el acceso a las funcionalidades protegidas.

### Consulta de libros

Una vez iniciada la sesión, el usuario puede acceder al listado de libros disponibles y consultar información como:

* Título.
* Autor.
* Idioma.
* Género literario.
* Año de publicación.
* Estado de disponibilidad.

El sistema permite realizar búsquedas de libros según los criterios disponibles.

### Gestión de libros

Los usuarios que poseen permisos administrativos pueden registrar nuevos libros en el sistema y realizar las operaciones correspondientes de gestión.

### Cierre de sesión

Para finalizar la sesión, el usuario debe utilizar la opción **Cerrar sesión** disponible en la navegación de la aplicación.

## 6. Estructura general del proyecto

El proyecto utiliza una estructura basada en la separación de responsabilidades:

* **Model:** contiene las clases que representan las entidades del sistema.
* **DAO:** gestiona las operaciones de acceso a datos.
* **Servlets:** controlan las solicitudes y la lógica de navegación.
* **JSP:** contienen las vistas de la aplicación.
* **Filter:** controla la autenticación y el acceso a recursos protegidos.
* **Web resources:** contienen archivos de estilos y otros recursos utilizados por la interfaz.

## 7.Instrucciones de Despliegue Tomcat
1.Exportar el archivo BibliotecaUntec.war desde Eclipse.
2.Iniciar servidor Apache Tomcat.
3.Acceder al administrador de Tomcat http://localhost:8080/manager/html y desplegar el archivo .war
4.Acceder a la aplicación a través de la URL http://localhost:8080/BibliotecaUNTEC/

## 8. Consideraciones

La aplicación requiere que el servidor MySQL se encuentre activo y que los parámetros de conexión configurados en el proyecto sean correctos.

Asimismo, es necesario que Apache Tomcat se encuentre correctamente configurado para ejecutar la aplicación web.

## 9. Autor

Proyecto académico desarrollado para fines educativos en el contexto de formación en desarrollo de aplicaciones web.
