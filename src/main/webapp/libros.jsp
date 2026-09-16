<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es-CL">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1">

    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
        rel="stylesheet">
        
   <link rel="stylesheet"
      href="${pageContext.request.contextPath}/components/css/estilos.css">
   <link rel="stylesheet" 
   href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <title>Libros - Biblioteca Digital UNTEC</title>

</head>

<body>

    <%@ include file="components/navbar.jspf" %>


    <div class="container mt-5 mb-5">

        <div class="d-flex justify-content-between align-items-center mb-4">

            <div>

                <h1 class="fw-bold">
                    Libros disponibles
                </h1>

                <p class="text-muted">
                    Consulta los libros disponibles en la biblioteca.
                </p>

            </div>

            <c:if test="${sessionScope.rol == 'ADMIN'}">

                <a href="${pageContext.request.contextPath}/libro?accion=nuevo"
                   class="btn btn-primary">

                    + Agregar libro

                </a>

            </c:if>

        </div>


        <c:if test="${not empty error}">

            <div class="alert alert-danger">
                ${error}
            </div>

        </c:if>
	
	<!-- MENSAJES DE ÉXITO -->

<c:if test="${param.mensaje == 'creado'}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <i class="bi bi-check-circle"></i>
        ¡El libro fue registrado correctamente!
        <button type="button"
                class="btn-close"
                data-bs-dismiss="alert"
                aria-label="Cerrar">
        </button>
    </div>
</c:if>

<c:if test="${param.mensaje == 'actualizado'}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <i class="bi bi-check-circle"></i>
        ¡El libro fue actualizado correctamente!
        <button type="button"
                class="btn-close"
                data-bs-dismiss="alert"
                aria-label="Cerrar">
        </button>
    </div>
</c:if>

<c:if test="${param.mensaje == 'eliminado'}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <i class="bi bi-check-circle"></i>
        ¡El libro fue eliminado correctamente!
        <button type="button"
                class="btn-close"
                data-bs-dismiss="alert"
                aria-label="Cerrar">
        </button>
    </div>
</c:if>

<c:if test="${param.mensaje == 'estadoCambiado'}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <i class="bi bi-check-circle"></i>
        ¡El estado del libro fue cambiado correctamente!
        <button type="button"
                class="btn-close"
                data-bs-dismiss="alert"
                aria-label="Cerrar">
        </button>
    </div>
</c:if>


<!-- MENSAJES DE ERROR -->

<c:if test="${param.mensaje == 'errorEliminar'}">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <i class="bi bi-exclamation-triangle"></i>
        No fue posible eliminar el libro.
        <button type="button"
                class="btn-close"
                data-bs-dismiss="alert"
                aria-label="Cerrar">
        </button>
    </div>
</c:if>

<c:if test="${param.mensaje == 'errorEstado'}">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <i class="bi bi-exclamation-triangle"></i>
        No fue posible cambiar el estado del libro.
        <button type="button"
                class="btn-close"
                data-bs-dismiss="alert"
                aria-label="Cerrar">
        </button>
    </div>
</c:if>

<c:if test="${not empty error}">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <i class="bi bi-exclamation-triangle"></i>
        ${error}
        <button type="button"
                class="btn-close"
                data-bs-dismiss="alert"
                aria-label="Cerrar">
        </button>
    </div>
</c:if>
		<!-- BARRA DE BÚSQUEDA -->
	<form action="${pageContext.request.contextPath}/libro" 
	method="get" class="row g-2 mb-4">
	
   <input type="hidden" name="accion" value="buscar">
    
    <div class="col-md-9 col-sm-8">
        <input type="text" 
               name="criterio" 
               class="form-control" 
               placeholder="Buscar por título o autor..." 
               value="${criterio}">
    </div>
    
    <div class="col-md-3 col-sm-4 d-flex gap-2">
        <button type="submit" class="btn btn-primary w-100">
            <i class="bi bi-search"></i> Buscar
        </button>
        
        <c:if test="${not empty criterio}">
            <a href="${pageContext.request.contextPath}/libro" class="btn btn-outline-secondary" title="Limpiar búsqueda">
                Limpiar
            </a>
        </c:if>
    </div>
</form>
        <div class="card shadow-sm">

            <div class="card-body">

                <div class="table-responsive">

                    <table class="table table-striped table-hover align-middle">

                        <thead class="table-warning">

                            <tr>

                                <th>ID</th>
                                <th>Título</th>
                                <th>Autor</th>
                                <th>Idioma</th>
                                <th>Género</th>
                                <th>Año</th>
                                <th>Estado</th>

                 <c:if test="${sessionScope.rol == 'ADMIN'}">
                 <th>Acciones</th>
                    </c:if>
                        </tr>
                        </thead>
                 <tbody>
                 <c:choose>
                 <c:when test="${not empty libros}">
                 <c:forEach var="libro" items="${libros}">
                 <tr>
                 <td>
                    ${libro.id}
                     </td>

                 <td>
                    ${libro.titulo}
                     </td>
                                       
                 <td>
                    ${libro.autor}
                     </td>
                                            
                   <td>
                  	 ${libro.idioma}
                       </td>

                    <td>
                        ${libro.generoLiterario}
                           </td>

                       <td>
                           ${libro.anoPublicacion}
                              </td>

                         <td>

                         <c:choose>
                        <c:when test="${libro.prestado}">
                         <span class="badge bg-danger">
                                     Prestado
                                 </span>
                                        </c:when>
                                                 
                                          <c:otherwise>
                                          <span class="badge bg-success">
                                               Disponible
                                                </span>
                                              </c:otherwise>
                                                </c:choose>
                                            </td>

                                         <c:if test="${sessionScope.rol == 'ADMIN'}">
                                             <td>
                                             <a
                                             href="${pageContext.request.contextPath}/libro?accion=editar&id=${libro.id}"
                                             class="btn btn-sm btn-warning">
                                             <i class="bi bi-pencil">
                                             </i>
                                                 </a>
                                    <form
    								action="${pageContext.request.contextPath}/libro"
    								method="post"
   									class="d-inline">

  									<input type="hidden"
    							    name="accion"
           							value="cambiarEstado">

   									<input type="hidden"
          							 name="id"
          							 value="${libro.id}">

    								<input type="hidden"
           							name="prestado"
           							value="${libro.prestado}">

    								<button type="submit"
           							class="btn btn-sm btn-secondary"
            						title="Cambiar estado">

        							<i class="bi bi-arrow-repeat"></i>

    								</button>
									</form>
									        
                                            <form
                                             action="${pageContext.request.contextPath}/libro"
                                             method="post"
                                             class="d-inline">

                                             <input
                                              type="hidden"
                                              name="accion"
                                              value="eliminar">

                                              <input
                                              type="hidden"
                                              name="id"
                                              value="${libro.id}">

                                             <button
                                              type="submit"
                                              class="btn btn-sm btn-danger"
                                              onclick="return confirm('¿Está seguro de eliminar este libro?');">
											 <i class="bi bi-trash"></i>			
                                                 </button>
                                                    </form>
                                                </td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                <tr>
                                <td
                                 colspan="8"
                                 class="text-center text-muted py-4">
                                    No hay libros disponibles actualmente.
                                    </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="components/footer.jspf" %>


    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js">
    </script>
</body>
</html>