/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.AccesoBaseDatos;
import org.mindrot.jbcrypt.BCrypt;
import model.Roles;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Equipo1
 */
public class UsuarioDAO {
    
    private static Connection getConexion(){
        
        return AccesoBaseDatos.getInstance().getConn();
    }
    
    private static Usuario crearUsuario (final ResultSet rs) throws SQLException {
        
        String rol = rs.getString("rol");
        
        Roles rol1 = Roles.valueOf(rol);
        
        return new Usuario(rs.getString("nombre"), rs.getString("contrasena"),rol1);

    } 
    
    
    public static void anadirProfe(Usuario u){
        
        String hash = BCrypt.hashpw(u.getContrasena(), BCrypt.gensalt());
        
        String sql = "INSERT INTO usuarios (nombre, contrasena, rol) VALUES (?, ?, ?)";
        
        try (PreparedStatement ps = getConexion().prepareStatement(sql)){
                        
            ps.setString(1, u.getNombre());
            ps.setString(2, hash);
            ps.setString(3, u.getRol().toString());
            int salida = ps.executeUpdate();

            if(salida != 1){
                throw new Exception("No se ha insertado un solo registro de pacientes");
            }else if(salida == 1){
                System.out.println("Se ha añadido un profesor correctamente");
            }else{
                System.out.println("Se han añadido varios profesores");
            }
                
            
            
        } catch (SQLException e){
            System.out.println("SQL -> " + e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
  
    }
    
    public static boolean existeUsuario(String nombre) throws SQLException {
        
        String sql = "SELECT nombre FROM usuarios WHERE nombre = ?";
        
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setString(1, nombre);
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
                
            }
        }
    }
    
    public static boolean eliminarProfe(String nombre) throws SQLException {
        
        String sql = ("DELETE FROM usuarios WHERE nombre =?");
        
        try (PreparedStatement ps = getConexion().prepareStatement(sql)){
            
            ps.setString(1, nombre);
            
            return ps.executeUpdate() > 0; 

        }

    }
    
    public static List<Usuario> verUsuarios(){
        
        String sql = "SELECT nombre, contrasena, rol FROM usuarios";
        
        List<Usuario> lista = new ArrayList<>();
        
        try (PreparedStatement ps = getConexion().prepareStatement(sql)){
            
            try(ResultSet rs = ps.executeQuery()) {
                
                while(rs.next()) {
                    
                    lista.add(crearUsuario(rs));

                }
                
            }
                       
        } catch (SQLException ex) {
            System.out.println("SQL ERROR -> " + ex.getMessage());
        }
        
        return lista;

    }

    public static Usuario login(String nombre, String contrasena) {
            
        String sql = "SELECT nombre, contrasena, rol FROM usuarios WHERE nombre = ?";
        
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            
            ps.setString(1, nombre);
            
            try (ResultSet rs = ps.executeQuery()) {
                
                if (rs.next()) {
                    
                    String hash = rs.getString("contrasena");
                    // compara el texto plano con el hash de la BD
                    if (BCrypt.checkpw(contrasena, hash)) {
                        return crearUsuario(rs);
                    }
                    
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL ERROR -> " + e.getMessage());
        }
        
        return null;
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
