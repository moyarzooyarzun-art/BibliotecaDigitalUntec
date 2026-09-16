<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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

    <title>Login - Biblioteca Digital UNTEC</title>
</head>

<body>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card login-card">
                    <div class="card-header text-center bg-secondary text-white login-header">
                        <h3>Biblioteca Digital UNTEC</h3>
                    </div>

                    <div class="card-body">

                        <h5 class="text-center mb-4">
                            Iniciar sesión
                        </h5>

                        <%-- Mostrar mensaje de error --%>
                        <% if (request.getAttribute("error") != null) { %>

                            <div class="alert alert-danger">
                                <%= request.getAttribute("error") %>
                            </div>

                        <% } %>

                        <form action="${pageContext.request.contextPath}/login"
                              method="post">

                            <div class="mb-3">

                                <label for="username" class="form-label">
                                    Usuario
                                </label>

                                <input type="text"
                                       class="form-control"
                                       id="username"
                                       name="username"
                                       required>

                            </div>

                            <div class="mb-3">

                                <label for="password" class="form-label">
                                    Contraseña
                                </label>

                                <input type="password"
                                       class="form-control"
                                       id="password"
                                       name="password"
                                       required>

                            </div>

                            <div class="d-grid">

                                <button type="submit"
                                        class="btn btn-ingresar">
                                    Ingresar
                                </button>

                            </div>

                        </form>

                    </div>

                    <div class="card-footer text-center text-muted">
                        Biblioteca Digital UNTEC
                    </div>

                </div>

            </div>

        </div>

    </div>

    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js">
    </script>

</body>
</html>