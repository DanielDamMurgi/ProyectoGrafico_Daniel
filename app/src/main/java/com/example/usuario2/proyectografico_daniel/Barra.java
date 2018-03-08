package com.example.usuario2.proyectografico_daniel;

import android.content.Context;
import android.view.View;

/**
 * Created by usuario2 on 22/01/18.
 */

public class Barra {

    private Empresa empresa;
    private int valor;


    public Barra(Empresa empresa, int valor) {
        this.empresa=empresa;
        this.valor = valor;
    }

    public Barra(){

    }

    public int getValor() {
        return valor;
    }

    public Empresa getEmpresa() {
        return empresa;
    }
}
