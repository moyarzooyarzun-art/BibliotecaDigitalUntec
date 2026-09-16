<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>    
    
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
        rel="stylesheet">
        
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/components/css/estilos.css">
        
<title>
 <c:choose>
      <c:when test="${modoEdicion}"> Editar libro
         </c:when>
  <c:otherwise> 
  	  Registrar libro
        </c:otherwise>
        </c:choose>
        
      Biblioteca Digital UNTEC
    </title>
</head>

<body class="bg-light">

<!-- NAVBAR -->
	<%@ include file="components/navbar.jspf" %>

 <!-- CONTENIDO -->
  <div class="container mt-5 mb-5">
  <div class="row justify-content-center">
  <div class="col-md-8 col-lg-7">
  <div class="card shadow">
   
   <!-- ENCABEZADO -->
  <div class="card-header bg-dark text-white text-center py-3">
       <h3 class="mb-0">
          <c:choose>
           <c:when test="${modoEdicion}">Editar libro</c:when>
                   <c:otherwise>Registrar nuevo libro</c:otherwise>
                            </c:choose>
                        </h3>
                    </div>
   
   <!-- CUERPO -->
   <div class="card-body p-4">
   
    <!-- MENSAJE DE ERROR -->
   <c:if test="${not empty error}">
   <div class="alert alert-danger">
    ${error}
   </div>
   </c:if> 
   
    <!-- FORMULARIO -->
    <form
    action="${pageContext.request.contextPath}/libro"
    method="post">
    
     <!-- ACCIÓN -->
     <input
     type="hidden"
     name="accion"
     value="${modoEdicion ? 'actualizar' : 'crear'}">
     
     <!-- ID PARA EDICIÓN -->
     <c:if test="${modoEdicion}">
     <input
     type="hidden"
     name="id"
     value="${libro.id}">
         </c:if>

     <!-- TÍTULO -->
     <div class="mb-3">
     <label
      for="titulo"
      class="form-label fw-bold">
             Título
            </label>
       <input
       type="text"
       class="form-control"
       id="titulo"
       name="titulo"
       value="${libro.titulo}"
       placeholder="Ingrese el título del libro"
       required>
              </div>


       <!-- AUTOR -->

       <div class="mb-3">
       <label
        for="autor"
        class="form-label fw-bold">
               Autor
             </label>
       <input
        type="text"
        class="form-control"
        id="autor"
        name="autor"
        value="${libro.autor}"
        placeholder="Ingrese el autor del libro"
        required>
             </div>

        <!-- IDIOMA -->
                 <div class="mb-3">

                                <label
                                    for="idioma"
                                    class="form-label fw-bold">

                                    Idioma

                                </label>

                                <select
                                    class="form-select"
                                    id="idioma"
                                    name="idioma"
                                    required>

                                    <option value="">
                                        Seleccione un idioma
                                    </option>

                                    <option value="Español"
                                        ${libro.idioma == 'Español' ? 'selected' : ''}>
                                        Español
                                    </option>

                                    <option value="Inglés"
                                        ${libro.idioma == 'Inglés' ? 'selected' : ''}>
                                        Inglés
                                    </option>

                                    <option value="Francés"
                                        ${libro.idioma == 'Francés' ? 'selected' : ''}>
                                        Francés
                                    </option>

                                    <option value="Alemán"
                                        ${libro.idioma == 'Alemán' ? 'selected' : ''}>
                                        Alemán
                                    </option>

                                    <option value="Italiano"
                                        ${libro.idioma == 'Italiano' ? 'selected' : ''}>
                                        Italiano
                                    </option>

                                    <option value="Portugués"
                                        ${libro.idioma == 'Portugués' ? 'selected' : ''}>
                                        Portugués
                                    </option>

                                </select>

                            </div>


                            <!-- GÉNERO -->

                            <div class="mb-3">

                                <label
                                    for="generoLiterario"
                                    class="form-label fw-bold">

                                    Género literario

                                </label>

                                <select
                                    class="form-select"
                                    id="generoLiterario"
                                    name="generoLiterario"
                                    required>

                                    <option value="">
                                        Seleccione un género
                                    </option>

                                    <option value="Novela"
                                        ${libro.generoLiterario == 'Novela' ? 'selected' : ''}>
                                        Novela
                                    </option>

                                    <option value="Cuento"
                                        ${libro.generoLiterario == 'Cuento' ? 'selected' : ''}>
                                        Cuento
                                    </option>

                                    <option value="Poesía"
                                        ${libro.generoLiterario == 'Poesía' ? 'selected' : ''}>
                                        Poesía
                                    </option>

                                    <option value="Drama"
                                        ${libro.generoLiterario == 'Drama' ? 'selected' : ''}>
                                        Drama
                                    </option>

                                    <option value="Ciencia ficción"
                                        ${libro.generoLiterario == 'Ciencia ficción' ? 'selected' : ''}>
                                        Ciencia ficción
                                    </option>

                                    <option value="Fantasía"
                                        ${libro.generoLiterario == 'Fantasía' ? 'selected' : ''}>
                                        Fantasía
                                    </option>

                                    <option value="Misterio"
                                        ${libro.generoLiterario == 'Misterio' ? 'selected' : ''}>
                                        Misterio
                                    </option>

                                    <option value="Romance"
                                        ${libro.generoLiterario == 'Romance' ? 'selected' : ''}>
                                        Romance
                                    </option>

                                    <option value="Historia"
                                        ${libro.generoLiterario == 'Historia' ? 'selected' : ''}>
                                        Historia
                                    </option>

                                    <option value="Biografía"
                                        ${libro.generoLiterario == 'Biografía' ? 'selected' : ''}>
                                        Biografía
                                    </option>
                                    
                                    <option value="No ficción"
                                        ${libro.generoLiterario == 'No ficción' ? 'selected' : ''}>
                                        No ficción
                                    </option>

                                    <option value="Otro"
                                        ${libro.generoLiterario == 'Otro' ? 'selected' : ''}>
                                        Otro
                                    </option>

                                </select>

                            </div>


                            <!-- AÑO -->

                            <div class="mb-4">

                                <label
                                    for="anoPublicacion"
                                    class="form-label fw-bold">

                                    Año de publicación

                                </label>

                                <input
                                    type="number"
                                    class="form-control"
                                    id="anoPublicacion"
                                    name="anoPublicacion"

                                    value="${libro.anoPublicacion}"

                                    min="1000"
                                    max="2100"

                                    placeholder="Ejemplo: 2024"
                                    required>

                            </div>


                            <!-- BOTONES -->

                            <div class="d-flex justify-content-between">

                                <a
                                    href="${pageContext.request.contextPath}/libro"
                                    class="btn btn-secondary">

                                    Cancelar

                                </a>


                                <button
                                    type="submit"
                                    class="btn btn-primary">

                                    <c:choose>

                                        <c:when test="${modoEdicion}">
                                            Actualizar libro
                                        </c:when>

                                        <c:otherwise>
                                            Guardar libro
                                        </c:otherwise>

                                    </c:choose>

                                </button>

                            </div>


                        </form>

                    </div>


                    <!-- FOOTER DE LA TARJETA -->

                    <div class="card-footer text-center text-muted">

                        Biblioteca Digital UNTEC

                    </div>

                </div>

            </div>

        </div>

    </div>


    <!-- FOOTER -->

    <%@ include file="components/footer.jspf" %>


    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js">
    </script>

</body>

</html>
  