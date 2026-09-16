package com.untec.biblioteca.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.untec.biblioteca.model.Libro;
import com.untec.biblioteca.model.LibroDAO;
import com.untec.biblioteca.util.Validaciones;

@WebServlet("/libro")

public class LibroServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private LibroDAO libroDao;

    @Override
    public void init() {
        libroDao = new LibroDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verificar sesión
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String accion = request.getParameter("accion");

        try {

        	 // NUEVO LIBRO
            if ("nuevo".equals(accion)) {

                request.setAttribute("modoEdicion", false);

                request.getRequestDispatcher("/registroLibro.jsp")
                       .forward(request, response);

                return;
            }

            // EDITAR
            if ("editar".equals(accion)) {

                String idTexto = request.getParameter("id");
                int id = Integer.parseInt(idTexto);

                Libro libro = libroDao.readById(id);

                if (libro == null) {
                    request.setAttribute("error",
                            "El libro solicitado no existe.");

                    response.sendRedirect(request.getContextPath() + "/libro");
                    return;
                }

                request.setAttribute("libro", libro);
                request.setAttribute("modoEdicion", true);

                request.getRequestDispatcher("/registroLibro.jsp")
                        .forward(request, response);

                return;
            }

            // BUSCAR
            if ("buscar".equals(accion)) {

                String criterio = request.getParameter("criterio");

                if (criterio == null) {
                    criterio = "";
                }

                List<Libro> libros;

                if (criterio.trim().isEmpty()) {
                    libros = libroDao.readDisponibles();
                } else {
                    libros = libroDao.readByTitulo(criterio);
                
                if (libros.isEmpty()) {
                    libros = libroDao.readByAutor(criterio);
                }
            }
                request.setAttribute("libros", libros);
                request.setAttribute("criterio", criterio);

            } else {

                // LISTAR DISPONIBLES
                List<Libro> libros = libroDao.readDisponibles();

                request.setAttribute("libros", libros);
            }

            request.getRequestDispatcher("/libros.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {

            e.printStackTrace();

            request.setAttribute("error",
                    "El ID del libro no es válido.");

            request.getRequestDispatcher("/libros.jsp")
                    .forward(request, response);

        } catch (SQLException e) {

            e.printStackTrace();

            request.setAttribute("error",
                    "No fue posible procesar la solicitud.");

            request.getRequestDispatcher("/libros.jsp")
                    .forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // Verificar sesión
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        // Verificar que sea ADMIN
        String rol = (String) session.getAttribute("rol");

        if (!"ADMIN".equals(rol)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN,
                    "No tiene permisos para realizar esta operación.");
            return;
        }

        String accion = request.getParameter("accion");

        // Eliminar libro
        if ("eliminar".equals(accion)) {

            try {

                String idTexto = request.getParameter("id");
                int id = Integer.parseInt(idTexto);

                boolean eliminado = libroDao.delete(id);

                if (eliminado) {
                    response.sendRedirect(
                            request.getContextPath() + "/libro?mensaje=eliminado");
                } else {
                    response.sendRedirect(
                            request.getContextPath() + "/libro?mensaje=errorEliminar");
                }

            } catch (Exception e) {

                e.printStackTrace();

                response.sendRedirect(
                        request.getContextPath() + "/libro?mensaje=errorEliminar");
            }

            return;
        }
        
        // Cambiar al estado contrario
        if ("cambiarEstado".equals(accion)) {

            try {

                String idTexto = request.getParameter("id");
                int id = Integer.parseInt(idTexto);

                String prestadoTexto = request.getParameter("prestado");
                boolean prestado = Boolean.parseBoolean(prestadoTexto);

                // Cambiar el estado del libro
                libroDao.updateEstado(id, !prestado);

                response.sendRedirect(
                    request.getContextPath() + "/libro?mensaje=estadoCambiado"
                );

            } catch (Exception e) {

                e.printStackTrace();

                response.sendRedirect(
                    request.getContextPath() + "/libro?mensaje=errorEstado"
                );
            }

            return;
        }

        // OBTENER DATOS DEL FORMULARIO

        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String idioma = request.getParameter("idioma");
        String generoLiterario = request.getParameter("generoLiterario");
        String anoPublicacionTexto = request.getParameter("anoPublicacion");

        // VALIDAR TÍTULO

        if (!Validaciones.validarTitulo(titulo)) {

            request.setAttribute("error",
                    "El título ingresado no es válido.");

            request.getRequestDispatcher("/registroLibro.jsp")
                    .forward(request, response);

            return;
        }

        // VALIDAR AUTOR

        if (!Validaciones.validarAutor(autor)) {

            request.setAttribute("error",
                    "El autor ingresado no es válido.");

            request.getRequestDispatcher("/registroLibro.jsp")
                    .forward(request, response);

            return;
        }

        // VALIDAR IDIOMA

        if (!Validaciones.validarIdioma(idioma)) {

            request.setAttribute("error",
                    "El idioma ingresado no es válido.");

            request.getRequestDispatcher("/registroLibro.jsp")
                    .forward(request, response);

            return;
        }

        // VALIDAR GÉNERO

        if (!Validaciones.validarGeneroLiterario(generoLiterario)) {

            request.setAttribute("error",
                    "El género literario ingresado no es válido.");

            request.getRequestDispatcher("/registroLibro.jsp")
                    .forward(request, response);

            return;
        }


        try {

            int anoPublicacion =
                    Integer.parseInt(anoPublicacionTexto);

            // Al crear un libro, inicialmente queda disponible
            boolean prestado = false;

            Libro libro = new Libro();

            libro.setTitulo(titulo.trim());
            libro.setAutor(autor.trim());
            libro.setIdioma(idioma.trim());
            libro.setGeneroLiterario(generoLiterario.trim());
            libro.setAnoPublicacion(anoPublicacion);
            libro.setPrestado(prestado);


            // ACTUALIZAR

            if ("actualizar".equals(accion)) {

                String idTexto = request.getParameter("id");
                int id = Integer.parseInt(idTexto);

                libro.setId(id);

                boolean actualizado = libroDao.update(libro);

                if (!actualizado) {

                    request.setAttribute("error",
                            "No fue posible actualizar el libro.");

                    request.setAttribute("libro", libro);
                    request.setAttribute("modoEdicion", true);

                    request.getRequestDispatcher("/registroLibro.jsp")
                            .forward(request, response);

                    return;
                }

                response.sendRedirect(
                        request.getContextPath() + "/libro?mensaje=actualizado");

                return;
            }


            // CREAR

            boolean insertado = libroDao.create(libro);

            if (!insertado) {

                request.setAttribute("error",
                        "No fue posible registrar el libro.");

                request.getRequestDispatcher("/registroLibro.jsp")
                        .forward(request, response);

                return;
            }

            response.sendRedirect(
                    request.getContextPath() + "/libro?mensaje=creado");

        } catch (NumberFormatException e) {

            e.printStackTrace();

            request.setAttribute("error",
                    "El año de publicación debe ser un número inferior al 2027.");

            request.getRequestDispatcher("/registroLibro.jsp")
                    .forward(request, response);

        } catch (SQLException e) {

            e.printStackTrace();

            request.setAttribute("error",
                    "Ocurrió un error al guardar el libro.");

            request.getRequestDispatcher("/registroLibro.jsp")
                    .forward(request, response);
        }
    }
}
