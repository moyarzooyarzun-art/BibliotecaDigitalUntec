package com.untec.biblioteca.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
	
		
		//Definir un objeto que nos permita conectar con la BBDD
		private static final Conexion CONEXION = Conexion.getEstado();
		
		//Definir un objeto que nos permita preparar las consultas y luego ejecutarlas.
		private PreparedStatement ps;
		
		//Definir un objeto que nos permita recibir los datos obtenidos desde un consula (SELECT).
		private ResultSet rs;
		
		//Definir las consultas que se desean implementar
		
		/**
		 * Consulta para insertar
		 */
		private static final String INSERT_SQL = 
				"INSERT INTO libros (titulo, autor, idioma, generoLiterario, anoPublicacion, prestado) VALUES (?,?,?,?,?,?)";
		
		public boolean create(Libro libro) throws SQLException {
			
			try {
				
				ps = CONEXION.getConexion().prepareStatement(INSERT_SQL);
				ps.setString(1, libro.getTitulo());
				ps.setString(2, libro.getAutor());
				ps.setString(3, libro.getIdioma());
				ps.setString(4, libro.getGeneroLiterario());
				ps.setInt(5, libro.getAnoPublicacion());
				ps.setBoolean(6, libro.isPrestado());
				
				int filas = ps.executeUpdate();
				return filas > 0;
				
			} finally {
				
				CONEXION.cerrarConexion();
			}
		}
			 private Libro convertirLibro(ResultSet rs) throws SQLException {

			        Libro l = new Libro();

			        l.setId(rs.getInt("id"));
			        l.setTitulo(rs.getString("titulo"));
			        l.setAutor(rs.getString("autor"));
			        l.setIdioma(rs.getString("idioma"));
			        l.setGeneroLiterario(rs.getString("generoLiterario"));
			        l.setAnoPublicacion(rs.getInt("anoPublicacion"));
			        l.setPrestado(rs.getBoolean("prestado"));

			        return l;
			    }
			
		private static final String SELECT_ALL_SQL = 
				"SELECT id, titulo, autor, idioma, generoLiterario, anoPublicacion, prestado FROM libros ORDER BY id ASC";
		
		public List<Libro> readAll() throws SQLException {
			
			try {
				
				List<Libro> libros = new ArrayList<Libro>();
				ps = CONEXION.getConexion().prepareStatement(SELECT_ALL_SQL);
				rs = ps.executeQuery();
				
				while (rs.next()) {
					Libro l = convertirLibro(rs);
					libros.add(l);				
				}
				return libros;
				
			} finally {
				
				CONEXION.cerrarConexion();
			}
		}
		
		private static final String SELECT_BY_ID_SQL = 
				"SELECT id, titulo, autor, idioma, generoLiterario, anoPublicacion, prestado FROM libros WHERE id = ?";
		
		public Libro readById(int id) throws SQLException {
			
			try {
				
				ps = CONEXION.getConexion().prepareStatement(SELECT_BY_ID_SQL);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				Libro l = null;
				if (rs.next()) {
					l = convertirLibro(rs);
				}
				return l;
				
			} finally {
				
				CONEXION.cerrarConexion();
			}
		}
		
		private static final String SELECT_BY_TITULO_SQL =
				"SELECT id,titulo, autor, idioma, generoLiterario, anoPublicacion, prestado "
				+ "FROM libros WHERE titulo LIKE ? "
				+ "ORDER BY id DESC";
		
		public List<Libro> readByTitulo(String titulo) throws SQLException {
			
			try {
				
				List<Libro> libros = new ArrayList<Libro>();
				ps = CONEXION.getConexion().prepareStatement(SELECT_BY_TITULO_SQL);
				ps.setString(1, "%" + titulo + "%");
				rs = ps.executeQuery();
				
				while (rs.next()) {
					
					Libro l = convertirLibro(rs);
					libros.add(l);
				}
				
				return libros;
				
			} finally {
				
				CONEXION.cerrarConexion();
			}
		}
		
		private static final String SELECT_BY_AUTOR_SQL = 
				"SELECT id, titulo, autor, idioma, generoLiterario, anoPublicacion, prestado "
				+ "FROM libros "
				+ "WHERE autor LIKE ? "
				+ "ORDER BY autor, titulo";
		
		public List<Libro> readByAutor(String autor) throws SQLException {
			
			try {
				
				List<Libro> libros = new ArrayList<Libro>();
				ps = CONEXION.getConexion().prepareStatement(SELECT_BY_AUTOR_SQL);
				ps.setString(1, "%" + autor + "%");
				rs = ps.executeQuery();
				
				while (rs.next()) {
					
					Libro l = convertirLibro(rs);
					libros.add(l);
				}
				return libros;
				
			} finally {
				
				CONEXION.cerrarConexion();
			}
		}
			private static final String SELECT_DISPONIBLES_SQL=
			        "SELECT id, titulo, autor, idioma, generoLiterario, anoPublicacion, prestado "
			        + "FROM libros "
			        + "WHERE prestado = false "
			        + "ORDER BY titulo";
			
			public List<Libro> readDisponibles() throws SQLException {

			    try {

			        List<Libro> libros = new ArrayList<Libro>();
			        ps = CONEXION.getConexion().prepareStatement(SELECT_DISPONIBLES_SQL);
			        rs = ps.executeQuery();

			        while (rs.next()) {

			            Libro l = convertirLibro(rs);
			            libros.add(l);
			        }

			        return libros;

			    } finally {

			        CONEXION.cerrarConexion();
			        
			    }
			}
			    
			    private static final String DELETE_SQL =
			            "DELETE FROM libros WHERE id = ?";
			    
			    public boolean delete(int id) throws SQLException {

			        try {

			            ps = CONEXION.getConexion().prepareStatement(DELETE_SQL);
			            ps.setInt(1, id);
			            int filas = ps.executeUpdate();

			            return filas > 0;

			        } finally {

			            CONEXION.cerrarConexion();

			    }
			 }
			    private static final String UPDATE_SQL =
			            "UPDATE libros "
			            + "SET titulo = ?, autor = ?, idioma = ?, generoLiterario = ?, anoPublicacion = ?, prestado = ? "
			            + "WHERE id = ?";

			    public boolean update(Libro libro) throws SQLException {

			        try {

			            ps = CONEXION.getConexion().prepareStatement(UPDATE_SQL);

			            ps.setString(1, libro.getTitulo());
			            ps.setString(2, libro.getAutor());
			            ps.setString(3, libro.getIdioma());
			            ps.setString(4, libro.getGeneroLiterario());
			            ps.setInt(5, libro.getAnoPublicacion());
			            ps.setBoolean(6, libro.isPrestado());
			            ps.setInt(7, libro.getId());

			            int filas = ps.executeUpdate();

			            return filas > 0;

			        } finally {

			            CONEXION.cerrarConexion();

			        }
			    }
			    private static final String SELECT_BY_IDIOMA_SQL =
			            "SELECT id, titulo, autor, idioma, generoLiterario, anoPublicacion, prestado "
			            + "FROM libros "
			            + "WHERE idioma LIKE ? "
			            + "ORDER BY titulo";
			    
			    public List<Libro> readByIdioma(String idioma) throws SQLException {

			        try {

			            List<Libro> libros = new ArrayList<Libro>();
			            ps = CONEXION.getConexion().prepareStatement(SELECT_BY_IDIOMA_SQL);
			            ps.setString(1, "%" + idioma + "%");
			            rs = ps.executeQuery();

			            while (rs.next()) {

			                Libro l = convertirLibro(rs);

			                libros.add(l);
			            }

			            return libros;

			        } finally {

			            CONEXION.cerrarConexion();

			        }
			    }
			    private static final String SELECT_BY_GENERO_SQL =
			            "SELECT id, titulo, autor, idioma, generoLiterario, anoPublicacion, prestado "
			            + "FROM libros "
			            + "WHERE generoLiterario LIKE ? "
			            + "ORDER BY titulo";
			    
			    public List<Libro> readByGenero(String generoLiterario) throws SQLException {

			        try {

			            List<Libro> libros = new ArrayList<Libro>();
			            ps = CONEXION.getConexion().prepareStatement(SELECT_BY_GENERO_SQL);
			            ps.setString(1, "%" + generoLiterario + "%");
			            rs = ps.executeQuery();

			            while (rs.next()) {

			                Libro l = convertirLibro(rs);
			                libros.add(l);
			            }

			            return libros;

			        } finally {

			            CONEXION.cerrarConexion();}

			    }
			    
			        private static final String UPDATE_ESTADO_SQL =
			                "UPDATE libros SET prestado = ? WHERE id = ?";

			        public boolean updateEstado(int id, boolean prestado) throws SQLException {

			            try {
			                ps = CONEXION.getConexion().prepareStatement(UPDATE_ESTADO_SQL);

			                ps.setBoolean(1, prestado);
			                ps.setInt(2, id);

			                int filas = ps.executeUpdate();

			                return filas > 0;

			            } finally {
			                CONEXION.cerrarConexion();
			            }
			        }	      
			    }
		

		
		
		
		
		
