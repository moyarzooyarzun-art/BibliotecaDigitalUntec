package com.untec.biblioteca.model;

public class Libro {
	
	//Atributos
	private int id;
	private String titulo;
	private String autor;
	private String idioma;
	private String generoLiterario;
	private int anoPublicacion;
	private boolean prestado;
	
	public Libro() {
				
	}

	public Libro(int id, String titulo, String autor, String idioma, String generoLiterario, int anoPublicacion,
			boolean prestado) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.idioma = idioma;
		this.generoLiterario = generoLiterario;
		this.anoPublicacion = anoPublicacion;
		this.prestado = prestado;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setGeneroLiterario(String generoLiterario) {
		this.generoLiterario = generoLiterario;
	}
	
	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public int getAnoPublicacion() {
		return anoPublicacion;
	}

	public void setAnoPublicacion(int anoPublicacion) {
		this.anoPublicacion = anoPublicacion;
	}

	public boolean isPrestado() {
		return prestado;
	}

	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}

	public String getGeneroLiterario() {
		return generoLiterario;
	}

		
}
