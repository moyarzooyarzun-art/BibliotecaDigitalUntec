package com.untec.biblioteca.util;


public class Validaciones {

	 /**
     * Validar título del libro
     *
     * @param titulo
     * @return
     */
    public static boolean validarTitulo(String titulo) {

        if (titulo == null || titulo.trim().isEmpty()) {
            return false;
        }

        String valor = titulo.trim();

        if (valor.length() < 2 || valor.length() > 100) {
            return false;
        }

        return true;
    }


    /**
     * Validar autor
     *
     * @param autor
     * @return
     */
    public static boolean validarAutor(String autor) {

        if (autor == null || autor.trim().isEmpty()) {
            return false;
        }

        String valor = autor.trim();

        if (valor.length() < 2 || valor.length() > 100) {
            return false;
        }

        return true;
    }


    /**
     * Validar idioma
     *
     * @param idioma
     * @return
     */
    public static boolean validarIdioma(String idioma) {

        if (idioma == null || idioma.trim().isEmpty()) {
            return false;
        }

        String valor = idioma.trim();

        if (valor.length() < 2 || valor.length() > 30) {
            return false;
        }

        return true;
    }


    /**
     * Validar género literario
     *
     * @param generoLiterario
     * @return
     */
    public static boolean validarGeneroLiterario(String generoLiterario) {

        if (generoLiterario == null || generoLiterario.trim().isEmpty()) {
            return false;
        }

        String valor = generoLiterario.trim();

        if (valor.length() < 2 || valor.length() > 50) {
            return false;
        }

        return true;
    }

}
