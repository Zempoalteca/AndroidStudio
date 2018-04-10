package com.android.capacitacion.gabriel.simuladorcopamundial;

import java.util.ArrayList;

public class Equipo {

    private String nombre;
    private int bandera;
    private String[] jugadores;

    public Equipo(String nombre, int bandera, String[] jugadores) {
        this.nombre = nombre;
        this.bandera = bandera;
        this.jugadores = jugadores;
    }

    public String getNombre() {
        return nombre;
    }

    public int getBandera() {
        return bandera;
    }

    public String[] getJugadores() {
        return jugadores;
    }
}
