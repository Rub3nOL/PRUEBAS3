/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.AccesoBaseDatos;
import model.Material;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Equipo1
 */
public class MaterialDAO {
    
    
    private static Connection getConexion(){
        
        return AccesoBaseDatos.getInstance().getConn();
                
    }
    
    private static Material crearMaterial (final ResultSet rs) throws SQLException {
        
        LocalDate fecha = rs.getDate("fecha_alta").toLocalDate();
        
        
        return new Material(rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("id_categoria"),rs.getInt("id_subcategoria"), rs.getInt("id_estado"), 
                rs.getInt("cantidad"), rs.getInt("id_ubicacion"), fecha, rs.getString("observaciones"));

    }
    
    public static boolean actualizarMaterial(int cantidad, int idEstado, String obser, String nombre){
        
        String sql = ("UPDATE material SET cantidad =?, id_estado =?, observaciones=? WHERE nombre =?");
        
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            
            ps.setInt(1, cantidad);
            ps.setInt(2, idEstado);
            ps.setString(3, obser);
            ps.setString(4, nombre);
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
                System.out.println("SQL -> " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Surgio un error inesperado");
        }
        
        return false;
    }
    
    public static void insertarMaterial (Material m) {
        
            String insertSql = ("INSERT INTO material(nombre, descripcion, id_categoria,id_subcategoria, id_estado,id_ubicacion, cantidad, fecha_alta,observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    

            try (PreparedStatement ps = getConexion().prepareStatement(insertSql)){
                
                ps.setString(1, m.getNombre());
                ps.setString(2, m.getDescripcion());
                ps.setInt(3, m.getId_categoria());
                ps.setInt(4, m.getId_subcategoria());
                ps.setInt(5, m.getId_estado());
                ps.setInt(6, m.getId_ubicacion());

                ps.setInt(7, m.getCantidad());

                ps.setDate(8,Date.valueOf(m.getFecha_alta()));

                ps.setString(9,m.getObservaciones());
                
                int salida = ps.executeUpdate();
                
                if(salida < 1){
                    System.out.println("No se ha añadido el material correctamente");
                }else if(salida == 1){
                    System.out.println("Se ha añadido un material correctamente");
                }else {
                    System.out.println("Se han añadido varios materiales");
                }
                
                
            } catch (SQLException e) {
                System.out.println("SQL -> " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Surgio un error inesperado");
            }

    }
    
    public static List<Material> verInventario(){

        String sql = "SELECT m.id_material, m.nombre, c.nombre as categoria, e.nombre as estado, cantidad, id_ubicacion FROM material m "
                + "JOIN categorias c ON c.id_categoria = m.id_categoria JOIN estado e ON e.id_estado = m.id_estado";
        
        List<Material> lista = new ArrayList<>();
        
        try (PreparedStatement ps = getConexion().prepareStatement(sql)){
            
            try(ResultSet rs = ps.executeQuery()) {
                
                while(rs.next()) {
                    
                    lista.add(crearMaterial(rs));

                }
                
            }
                       
        } catch (SQLException ex) {
            System.out.println("SQL ERROR -> " + ex.getMessage());
        }
        
        return lista;
        
        
    }
    
    public static boolean eliminarMaterial(String nombre) throws SQLException {

    String sql = ("DELETE FROM material WHERE nombre =?");

    try (PreparedStatement ps = getConexion().prepareStatement(sql)){

        ps.setString(1, nombre);

        return ps.executeUpdate() > 0;

        }

    }
    
    
    public static List<Material> filtrarPorNombre(String nombre){

        String sql = "SELECT m.id_material, m.nombre, c.nombre as categoria, e.nombre as estado, cantidad, id_ubicacion FROM material m JOIN categorias c ON c.id_categoria = m.id_categoria " +
                    "JOIN estado e ON e.id_estado = m.id_estado WHERE m.nombre=?";
        
        List<Material> lista = new ArrayList<>();
        
        try (PreparedStatement ps = getConexion().prepareStatement(sql)){
            
            ps.setString(1, nombre);
            
            try(ResultSet rs = ps.executeQuery()) {
                
                while(rs.next()) {
                    
                    lista.add(crearMaterial(rs));

                }
                
            }
                       
        } catch (SQLException ex) {
            System.out.println("SQL ERROR -> " + ex.getMessage());
        }
        
        return lista;
         
    }
    
    public static List<Material> filtrarPorCategoria(String categoria){

        String sql = "SELECT m.id_material, m.nombre, c.nombre as categoria, e.nombre as estado, cantidad, id_ubicacion FROM material m JOIN categorias c ON c.id_categoria = m.id_categoria " +
                    "JOIN estado e ON e.id_estado = m.id_estado WHERE c.nombre =?";
        
        List<Material> lista = new ArrayList<>();
        
        try (PreparedStatement ps = getConexion().prepareStatement(sql)){
            
            ps.setString(1, categoria);
            
            try(ResultSet rs = ps.executeQuery()) {
                
                while(rs.next()) {
                    
                    lista.add(crearMaterial(rs));

                }
                
            }
                       
        } catch (SQLException ex) {
            System.out.println("SQL ERROR -> " + ex.getMessage());
        }
        
        return lista;
         
    }
    
    public static List<Material> filtrarPorEstado(String estado){

        String sql = "SELECT m.id_material, m.nombre, c.nombre as categoria, " +
                         "e.nombre as estado, m.cantidad, u.espacio " +
                         "FROM material m " +
                         "JOIN categorias c ON c.id_categoria = m.id_categoria " +
                         "JOIN estado e ON e.id_estado = m.id_estado "  +
                         "JOIN ubicaciones u ON u.codigo_armario = m.id_ubicacion WHERE e.nombre =?";
        
        List<Material> lista = new ArrayList<>();
        
        String[] columnas = {"ID", "Nombre", "Categoría", "Estado", "Cantidad", "Ubicacion"};
        
        DefaultTableModel tabla = new DefaultTableModel(columnas, 0);
        
        try (PreparedStatement ps = getConexion().prepareStatement(sql)){
            
            ps.setString(1, estado);
            
            try(ResultSet rs = ps.executeQuery()) {
                
                while (rs.next()) {
                    tabla.addRow(new Object[]{
                        rs.getInt("id_material"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getString("estado"),
                        rs.getInt("cantidad"),
                        rs.getString("espacio")
                    });
                }
                
            }
                       
        } catch (SQLException ex) {
            System.out.println("SQL ERROR -> " + ex.getMessage());
        }
        
        return lista;
         
    }
    
    public static int getIdCategoria(String nombre) throws SQLException {
        
        String sql = "SELECT id_categoria FROM categorias WHERE nombre = ?";
        
        try (PreparedStatement ps = getConexion().prepareStatement(sql)){
            ps.setString(1, nombre);
            try(ResultSet rs = ps.executeQuery()) {
                
                if(rs.next()) {
                    
                    return rs.getInt("id_categoria");

                }
                
            }
                       
        } catch (SQLException ex) {
            System.out.println("SQL ERROR -> " + ex.getMessage());
        }
        
        return -1;
    }

    public static int getIdSubcategoria(String nombre) throws SQLException {
        
        String sql = "SELECT id_subcategoria FROM subcategorias WHERE nombre = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)){
            ps.setString(1, nombre);
            try(ResultSet rs = ps.executeQuery()) {
                
                if(rs.next()) {
                    
                    return rs.getInt("id_subcategoria");

                }
                
            }
                       
        } catch (SQLException ex) {
            System.out.println("SQL ERROR -> " + ex.getMessage());
        }
        return -1;
    }

    public static int getIdEstado(String nombre) throws SQLException {
        
        String sql = "SELECT id_estado FROM estado WHERE nombre = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)){
            ps.setString(1, nombre);
            try(ResultSet rs = ps.executeQuery()) {
                
                if(rs.next()) {
                    
                    return rs.getInt("id_estado");

                }
                
            }
                       
        } catch (SQLException ex) {
            System.out.println("SQL ERROR -> " + ex.getMessage());
        }
        return -1;
    }

    public static int getIdUbicacion(String espacio) throws SQLException {
        
        String sql = "SELECT codigo_armario FROM ubicaciones WHERE espacio = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)){
            ps.setString(1, espacio);
            try(ResultSet rs = ps.executeQuery()) {
                
                if(rs.next()) {
                    
                    return rs.getInt("codigo_armario");

                }
                
            }
                       
        } catch (SQLException ex) {
            System.out.println("SQL ERROR -> " + ex.getMessage());
        }
        return -1;
    }    
      
    
}
