/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dao.AccesoBaseDatos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Equipo1
 */
public class GestorCSV {
    
    private static final String CABECERA = 
        "nombre;descripcion;id_categoria;id_subcategoria;id_estado;cantidad;id_ubicacion";

    public static void exportar(String ruta) {
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            
            pw.println(CABECERA);
            
            String sql = "SELECT nombre, descripcion, id_categoria, "
                       + "id_subcategoria, id_estado, cantidad, id_ubicacion "
                       + "FROM material";
            
            try (PreparedStatement ps = AccesoBaseDatos.getInstance().getConn().prepareStatement(sql)){
                
                try (ResultSet rs = ps.executeQuery()) {
                    
                    while (rs.next()) {
                    pw.println(
                        rs.getString("nombre")       + ";" +
                        rs.getString("descripcion")  + ";" +
                        rs.getInt("id_categoria")    + ";" +
                        rs.getInt("id_subcategoria") + ";" +
                        rs.getInt("id_estado")       + ";" +
                        rs.getInt("cantidad")        + ";" +
                        rs.getInt("id_ubicacion")
                        );
                    }
                
                }

            } catch (SQLException e) {
                System.out.println("SQL EXCEPTION -> " + e.getMessage());
            }
        } catch (IOException ex) {
            System.out.println("Ocurrio un error");
        }
        
    }

    
    public static int importar(String ruta){
        
        int importados = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            
            br.readLine(); // saltar cabecera
            String linea;
            
            while ((linea = br.readLine()) != null) {
                
                String[] d = linea.split(";");
                String sql = "INSERT INTO material (nombre, descripcion, id_categoria, "
                           + "id_subcategoria, id_estado, cantidad, id_ubicacion, fecha_alta) "
                           + "VALUES (?, ?, ?, ?, ?, ?, ?, CURDATE())";
                
                try (PreparedStatement ps = AccesoBaseDatos.getInstance().getConn().prepareStatement(sql)) {
                    ps.setString(1, d[0]);
                    ps.setString(2, d[1]);
                    ps.setInt(3, Integer.parseInt(d[2]));
                    ps.setInt(4, Integer.parseInt(d[3]));
                    ps.setInt(5, Integer.parseInt(d[4]));
                    ps.setInt(6, Integer.parseInt(d[5]));
                    ps.setInt(7, Integer.parseInt(d[6]));
                    ps.executeUpdate();
                    importados++;
                    
                } catch (SQLException e) {
                    System.out.println("SQL EXCEPTION -> " + e.getMessage());
                }
            }
        } catch (IOException e){
            System.out.println("Ocurrio un error");
        }
        
        
        return importados;
    }
    
    
}