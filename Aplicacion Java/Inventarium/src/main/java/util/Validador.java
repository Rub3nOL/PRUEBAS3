/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Equipo1
 */
public class Validador {

    public static boolean leerNombre(String nombre) {
        String regex = ("^[a-zA-Z0-9_.-]{3,20}$");
        if (nombre.matches(regex)) {
            return true;
        }
        return false;
    }

    public static boolean leerContraseña(String contrasena) {
        
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";

        if (contrasena.matches(regex)) {
            return true;
        }
        return false;
    }

    public static boolean leerMaterial(String material) {
        String regex = ("^.{3,}$");

        if (material.matches(regex)) {
            return true;
        }
        return false;
    }

    public static boolean leerDescripcion(String descripcion) {
        String regex = ("^[\\s\\S]{3,500}$");
        
            if (descripcion.matches(regex)) {
                return true;
        }
        return false;    
    }
    
    public static boolean leerCantidad(int cantidad){
        return cantidad > 0 && cantidad <= 10000;
    }
}
