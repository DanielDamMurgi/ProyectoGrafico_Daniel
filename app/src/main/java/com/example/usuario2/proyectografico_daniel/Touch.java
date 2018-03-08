package com.example.usuario2.proyectografico_daniel;

/**
 * Created by Equipo on 02/02/2018.
 */

public class Touch {

    private int inicioX, finX;
    private int inicioY, finY;

    private int valor;

    public Touch(int inicioX, int finX,int inicioY, int finY, int valor) {
        this.inicioX = inicioX;
        this.finX = finX;
        this.inicioY=inicioY;
        this.finY=finY;
        this.valor = valor;
    }

    public int getInicioX() {
        return inicioX;
    }

    public int getFinX() {
        return finX;
    }

    public int getInicioY() {
        return inicioY;
    }

    public int getFinY() {
        return finY;
    }

    public int getValor() {
        return valor;
    }
}
