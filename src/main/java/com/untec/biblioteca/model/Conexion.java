package com.untec.biblioteca.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Conexion instance;

    private Connection conexion;

    private final String USER = "root";
    private final String PASSWORD = "1234";
    private final String SERVER = "localhost:3306";
    private final String BBDD = "Biblioteca_bd";

    private Conexion() {

        conectar();
    }

    private void conectar() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://" + SERVER + "/" + BBDD
                    + "?useSSL=false"
                    + "&serverTimezone=UTC"
                    + "&allowPublicKeyRetrieval=true";

            conexion = DriverManager.getConnection(
                    url,
                    USER,
                    PASSWORD
            );

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public synchronized static Conexion getEstado() {

        if (instance == null) {
            instance = new Conexion();

        }

        return instance;
    }

    public Connection getConexion() {

        try {

            if (conexion == null || conexion.isClosed()) {
                conectar();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conexion;
    }

    public void cerrarConexion() {

        try {

            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
