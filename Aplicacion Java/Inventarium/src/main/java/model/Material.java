/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 * Representa un elemento del inventario del taller de informática.
 * Contiene toda la información asociada a un material: identificación,
 * descripción, categoría, estado, cantidad, ubicación y fecha de alta.
 *
 * @author Equipo1
 * @version 1.0
 */

public class Material {
    
    
    private int id;
    private String nombre;
    private String descripcion;
    private int id_categoria;
    private int id_subcategoria;
    private int id_estado;
    private int cantidad;
    private int id_ubicacion;
    private LocalDate fecha_alta;
    private String observaciones;

    public Material(String nombre, String descripcion, int id_categoria, int id_subcategoria, int id_estado, int cantidad, int id_ubicacion, LocalDate fecha_alta, String observaciones) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id_categoria = id_categoria;
        this.id_subcategoria = id_subcategoria;
        this.id_estado = id_estado;
        this.cantidad = cantidad;
        this.id_ubicacion = id_ubicacion;
        this.fecha_alta = fecha_alta;
        this.observaciones = observaciones;
    }

    


    public int getId() {return id;}

    public String getNombre() {return nombre;}

    public String getDescripcion() {return descripcion;}

    public int getId_categoria() {return id_categoria;}

    public int getId_subcategoria() {return id_subcategoria;}

    public int getId_estado() {return id_estado;}
    
    public int getCantidad() {return cantidad;}

    public int getId_ubicacion() {return id_ubicacion;}

    public LocalDate getFecha_alta() {return fecha_alta;}

    public String getObservaciones() {return observaciones;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Material -> ");
        sb.append("Id: ").append(id);
        sb.append(", Nombre: ").append(nombre);
        sb.append(", Descripcion: ").append(descripcion);
        sb.append(", Categoria: ").append(id_categoria);
        sb.append(", Subcategoria: ").append(id_subcategoria);
        sb.append(", Estado: ").append(id_estado);
        sb.append(", Cantidad: ").append(cantidad);
        sb.append(", Id ubicacion: ").append(id_ubicacion);
        sb.append(", Fecha alta: ").append(fecha_alta);
        sb.append(", Observaciones: ").append(observaciones);
        return sb.toString();
    }
    
    
    
    
    
}
