package org.example.estrucuturas;

import java.util.ArrayList;
import java.util.List;

public class Vertice<T> {
    private T valor;
    private List<Vertice<T>> vecinos;

    public Vertice(T valor) {
        this.valor = valor;
        this.vecinos = new ArrayList<>();
    }

    public T getValor() {
        return valor;
    }

    public List<Vertice<T>> getVecinos() {
        return vecinos;
    }

    public void agregarVecino(Vertice<T> vecino) {
        vecinos.add(vecino);
    }
}
