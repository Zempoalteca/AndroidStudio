package com.android.capacitacion.gabriel.adaptadorpersonalizado;

/**
 * Created by gabriel on 02/04/18.
 */

public class Lugar {
    private String nombre;
    private String descripcion;
    private int imagen;

    public Lugar(String nombre, String descripcion, int imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImagen() {
        return imagen;
    }
}
