package com.example.usuario2.proyectografico_daniel;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by usuario2 on 22/01/18.
 */

public class DatosGrafica {

    private static ArrayList<Barra> barras = new ArrayList<>();

    private static ArrayList<Empresa> empresa = new ArrayList<>();

    public static ArrayList<Touch> touch = new ArrayList<>();

    public DatosGrafica() {

        insertarEmpresas();

        insertarDatos();

    }

    public static ArrayList<Barra> getBarras() {
        return barras;
    }

    public static ArrayList<Empresa> getEmpresa() {
        return empresa;
    }

    public static ArrayList<Touch> getTouch() {
        return touch;
    }

    private static void insertarEmpresas(){
        if (empresa.isEmpty()){
            empresa.add(new Empresa("Company A",R.color.companyA));
            empresa.add(new Empresa("Company B",R.color.companyB));
            empresa.add(new Empresa("Company C",R.color.companyC));
        }
    }

    private static void insertarDatos(){

        if(barras.isEmpty()) {

            barras.add(new Barra(empresa.get(0), 18000));
            barras.add(new Barra(empresa.get(1), 1000));
            barras.add(new Barra(empresa.get(2), 12000));

            barras.add(new Barra(empresa.get(0), 2000));
            barras.add(new Barra(empresa.get(1), 6000));
            barras.add(new Barra(empresa.get(2), 3000));

            barras.add(new Barra(empresa.get(0), 300));
            barras.add(new Barra(empresa.get(1), 9000));
            barras.add(new Barra(empresa.get(2), 16500));

            barras.add(new Barra(empresa.get(2), 10000));
        }
    }
}
