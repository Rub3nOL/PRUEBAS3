/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Equipo1
 */
public class Usuario {
    
    private int id;
    private String nombre;
    private String contrasena;
    private Enum rol;

    public Usuario(String nombre, String contrasena, Enum rol) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.rol = rol;
    }
    
    public Usuario(int id, String nombre, String contrasena, Enum rol) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.rol = rol;
    }
    

    public int getId() {return id;}

    public String getNombre() {return nombre;}

    public String getContrasena() {return contrasena;}

    public Enum getRol() {return rol;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario -> ");
        sb.append("Id: ").append(id);
        sb.append(", Nombre: ").append(nombre);
        sb.append(", Contraseña: ").append(contrasena);
        sb.append(", Rol: ").append(rol);
        return sb.toString();
    }
    
    
    
    
    
    
    
    
    
    
}
