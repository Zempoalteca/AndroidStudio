package com.android.capacitacion.gabriel.simuladorcopamundial;

import java.io.Serializable;

public class Match implements Serializable{

    private Equipo izquierda;
    private int goles_equipo_izq;
    private Equipo derecha;
    private int goles_equipo_der;
    private String resultado;

    public Match(Equipo izquierda, int goles_equipo_izq, Equipo derecha, int goles_equipo_der, String resultado) {
        this.izquierda = izquierda;
        this.goles_equipo_izq = goles_equipo_izq;
        this.derecha = derecha;
        this.goles_equipo_der = goles_equipo_der;
        this.resultado = resultado;
    }

    public Equipo getIzquierda() {
        return izquierda;
    }

    public int getGoles_equipo_izq() {
        return goles_equipo_izq;
    }

    public Equipo getDerecha() {
        return derecha;
    }

    public int getGoles_equipo_der() {
        return goles_equipo_der;
    }

    public String getResultado() {
        return resultado;
    }
}
