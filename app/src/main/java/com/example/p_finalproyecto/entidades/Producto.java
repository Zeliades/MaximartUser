package com.example.p_finalproyecto.entidades;

import java.io.Serializable;

public class Producto{
    private int id;
    private String nombre;
    private String tipo;
    private int precio;
    private String descripcion;
    private int iddrawable;

    //GETTERS AND SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIddrawable() {
        return iddrawable;
    }

    public void setIddrawable(int iddrawable) {
        this.iddrawable = iddrawable;
    }


}
