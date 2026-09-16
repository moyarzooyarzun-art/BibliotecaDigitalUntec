<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
    
<!DOCTYPE html>
<html lang="es-CL">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/components/css/estilos.css">
      
<title>Biblioteca Digital UNTEC</title>

</head>
<body>

	<!-- NAVBAR -->
	<%@ include file="components/navbar.jspf" %>
	
	 <!-- CONTENIDO PRINCIPAL -->
    <div class="container mt-5">

        <div class="text-center">
                	             	  
            <h1 class="display-5 fw-bold">Biblioteca Digital</h1>
	
	   <p class="lead">
                Sistema de gestión y consulta de libros
            </p>

		<c:choose>

    <c:when test="${empty sessionScope.usuario}">
        <!-- USUARIO NO AUTENTICADO -->
        <p>
            ¡Bienvenido a la Biblioteca Digital UNTEC! 
            Inicia sesión para comenzar a buscar y consultar nuestros libros.
        </p>

        <a href="${pageContext.request.contextPath}/login"
           class="btn btn-primary">
            Ingresar a la biblioteca
        </a>
    </c:when>

    <c:otherwise>
        <!-- USUARIO AUTENTICADO -->
        <p>
            Bienvenido, ${sessionScope.usuario.username}.
        </p>

        <p>
            Ahora puedes buscar los libros que desees.
        </p>

        <a href="${pageContext.request.contextPath}/libro"
           class="btn btn-primary">
            Buscar libros
        </a>
    </c:otherwise>

</c:choose>

        </div>

    </div>


    <!-- FOOTER -->

    <%@ include file="components/footer.jspf"%>


    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js">
    </script>

</body>

</html>