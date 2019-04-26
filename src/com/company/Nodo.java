package com.company;

import java.lang.reflect.Array;
import java.util.List;

public class Nodo {

    private int id;
    private int nodoAnterior;
    private int menordistancia;
    private boolean visitado;

    public Nodo(int id, int menordistancia) {
        this.id = id;
        this.menordistancia = menordistancia;
        this.visitado = false;
        this.nodoAnterior = Integer.MAX_VALUE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNodoAnterior() {
        return nodoAnterior;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public void setNodoAnterior(int nodoAnterior) {
        this.nodoAnterior = nodoAnterior;
    }

    public int getMenordistancia() {
        return menordistancia;
    }

    public void setMenordistancia(int menordistancia) {
        this.menordistancia = menordistancia;
    }
}
