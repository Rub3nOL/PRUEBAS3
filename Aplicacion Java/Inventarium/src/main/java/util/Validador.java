/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 * Clase de utilidad con métodos estáticos para validar los datos de entrada
 * del usuario antes de insertarlos o procesarlos en la aplicación.
 * Todos los métodos usan expresiones regulares o comprobaciones numéricas.
 *
 * @author Equipo1
 * @version 1.0
 */

public class Validador {

/**
 * Valida un nombre de usuario.
 * El nombre debe tener entre 3 y 20 caracteres y solo puede contener
 * letras, números, guiones bajos, puntos o guiones medios.
 *
 * @param nombre Nombre de usuario a validar.
 * @return true si el nombre es válido; false en caso contrario.
 */
    
    public static boolean leerNombre(String nombre) {
        String regex = ("^[a-zA-Z0-9_.-]{3,20}$");
        if (nombre.matches(regex)) {
            return true;
        }
        return false;
    }
    
/**
 * Valida una contraseña segura.
 * Debe tener al menos 8 caracteres, una mayúscula, una minúscula,
 * un número y un carácter especial.
 *
 * @param contrasena Contraseña a validar.
 * @return true si la contraseña cumple los requisitos; false en caso contrario.
 */

    public static boolean leerContraseña(String contrasena) {
        
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";

        if (contrasena.matches(regex)) {
            return true;
        }
        return false;
    }
    
    /**
 * Valida el nombre de un material del inventario.
 * Debe tener al menos 3 caracteres.
 *
 * @param material Nombre del material a validar.
 * @return true si el nombre es válido; false en caso contrario.
 */

    public static boolean leerMaterial(String material) {
        String regex = ("^.{3,}$");

        if (material.matches(regex)) {
            return true;
        }
        return false;
    }
    
/**
 * Valida la descripción de un material.
 * Debe tener entre 3 y 500 caracteres. Admite cualquier carácter incluidos saltos de línea.
 *
 * @param descripcion Descripción a validar.
 * @return true si la descripción es válida; false en caso contrario.
 */

    public static boolean leerDescripcion(String descripcion) {
        String regex = ("^[\\s\\S]{3,500}$");
        
            if (descripcion.matches(regex)) {
                return true;
        }
        return false;    
    }
    
/**
 * Valida la cantidad de un material.
 * Debe ser un número entero entre 1 y 10 000.
 *
 * @param cantidad Cantidad a validar.
 * @return true si la cantidad está en el rango permitido; false en caso contrario.
 */
    
    public static boolean leerCantidad(int cantidad){
        return cantidad > 0 && cantidad <= 10000;
    }
    
}
