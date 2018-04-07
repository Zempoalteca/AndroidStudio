package com.android.capacitacion.gabriel.tiendadecursos;

import java.io.Serializable;

public class Curso implements Serializable {

    private int imagenCurso;
    private int imagenInstructor;
    private String nombreCurso;
    private String nombreInstructor;
    private String descripcion;
    private String requisitos;
    private double costo;
    private double puntuación;

    public Curso(int imagenCurso, int imagenInstructor, String nombreCurso, String nombreInstructor, String descripcion, String requisitos, double costo, double puntuación) {
        this.imagenCurso = imagenCurso;
        this.imagenInstructor = imagenInstructor;
        this.nombreCurso = nombreCurso;
        this.nombreInstructor = nombreInstructor;
        this.descripcion = descripcion;
        this.requisitos = requisitos;
        this.costo = costo;
        this.puntuación = puntuación;
    }

    public int getImagenCurso() {
        return imagenCurso;
    }

    public int getImagenInstructor() {
        return imagenInstructor;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public String getNombreInstructor() {
        return nombreInstructor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public double getCosto() {
        return costo;
    }

    public double getPuntuación() {
        return puntuación;
    }

}
