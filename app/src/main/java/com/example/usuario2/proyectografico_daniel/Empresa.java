package com.example.usuario2.proyectografico_daniel;

/**
 * Created by usuario2 on 2/02/18.
 */

public class Empresa {

    private String nombre;
    private int color;

    public Empresa() {
    }

    public Empresa(String nombre, int color) {
        this.nombre = nombre;
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public int getColor() {
        return color;
    }
}
