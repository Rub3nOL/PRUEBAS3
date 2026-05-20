/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Equipo1
 */
public class AccesoBaseDatos {
    
    private String bd;
    private String url ;
    private String usuario;
    private String clave ;

    private Connection conn;

    private AccesoBaseDatos() {
        cargarConfiguracion();
        abrirConexion();
    }
    
    private void cargarConfiguracion(){
        
        Properties prop = new Properties();
        
        try(FileInputStream fis = new FileInputStream("configuracion.properties")) {
            prop.load(fis);
            
            this.url = prop.getProperty("db.url");
            this.usuario = prop.getProperty("db.usuario");
            this.clave = prop.getProperty("db.clave");
            
            System.out.println("Configuracion cargada correctamente desde la raiz");
            
            
        } catch (IOException e) {
            System.out.println("Error: No se encontro el archivo en " + new File(".").getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error");
        }
        
    }
    
    private void abrirConexion() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", this.usuario);
            properties.setProperty("password", this.clave);
            properties.setProperty("useSSL", "false");
            properties.setProperty("serverTimezone", "Europe/Madrid");

            conn = DriverManager.getConnection(this.url, properties);
            System.out.println("Conexion correcta a la base de datos.");

        } catch (SQLException ex) {
            System.out.println("Error al conectar con la base de datos.");
            System.out.println("Mensaje: " + ex.getMessage());
            conn = null;
        }
    }

    public static AccesoBaseDatos getInstance() {
        return AccesoBaseDatosHolder.INSTANCE;
    }

    private static class AccesoBaseDatosHolder {
        private static final AccesoBaseDatos INSTANCE = new AccesoBaseDatos();
    }

    public Connection getConn() {
        try {
            if (conn == null || conn.isClosed()) {
                abrirConexion();
            }
        } catch (SQLException ex) {
            System.out.println("Error al comprobar el estado de la conexión.");
            System.out.println("Mensaje: " + ex.getMessage());
        }
        return conn;
    }

    public boolean cerrar() {
        if (conn == null) {
            return true;
        }

        try {
            conn.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión.");
            System.out.println("Mensaje: " + ex.getMessage());
            return false;
        }
    }
}