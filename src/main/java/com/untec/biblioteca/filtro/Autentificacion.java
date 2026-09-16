package com.untec.biblioteca.filtro;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import jakarta.servlet.annotation.WebFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")

public class Autentificacion implements Filter {
	
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        // LOGIN
        boolean loginRequest = uri.equals(contextPath + "/login");

        // PÁGINA DE INICIO PÚBLICA
        boolean indexRequest = uri.equals(contextPath + "/") || uri.equals(contextPath + "/index.jsp");
        
        boolean contactRequest = uri.equals(contextPath + "/contacto.jsp");

        // RECURSOS ESTÁTICOS
        boolean recursosPublicos = uri.contains("/css/") || uri.contains("/js/") || uri.contains("/img/");

        // SESIÓN
        HttpSession session = httpRequest.getSession(false);
        
        boolean autenticado = session != null && session.getAttribute("usuario") != null;

        // PÁGINAS PÚBLICAS        
        if (loginRequest || indexRequest || recursosPublicos || contactRequest) {
            chain.doFilter(request,response);
            return;
        }
 
        // USUARIO NO AUTENTICADO
        if (!autenticado) {
            httpResponse.sendRedirect(contextPath + "/login");
            return;
        }


        // AUTORIZACIÓN
        String rol = (String) session.getAttribute("rol");
        String accion = httpRequest.getParameter("accion");

        // OPERACIONES EXCLUSIVAS DE ADMIN
        boolean operacionAdministrativa = 
                uri.endsWith(
                        "/registroLibro.jsp"
                )
                ||
                "editar".equals(
                        accion
                )
                ||
                "actualizar".equals(
                        accion
                )
                ||
                "eliminar".equals(
                        accion
                );

        boolean operacionesEscritura =  "eliminar".equals(accion);
        
        
        // USER NO PUEDE ADMINISTRAR
        if ("USER".equals(rol) && operacionAdministrativa) {
            httpResponse.sendRedirect(contextPath + "/usuario?mensaje=sinPermiso");
            return;
        }
        
        
        if ("WRITER".equals(rol) && operacionesEscritura) {
        	httpResponse.sendRedirect(contextPath + "/usuario?mensaje=sinPermiso");
            return;
        }

        // CONTINUAR
         chain.doFilter(request,response);
    }


}
