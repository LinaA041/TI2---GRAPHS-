package org.example;

import java.util.*;

/**
 * <b> este es el grafo donde vamos a conectar las estaciones  </b> <br
 *
 */
public class ModeloRutas<T> implements GrafosImplemnt<T> {
    private Map<Estacion<T>, List<Estacion<T>>> grafo;

    public ModeloRutas() {
        this.grafo = new HashMap<>();
    }

    @Override
    public void agregarVertice(Estacion<T> estacion) {
        grafo.put(estacion, new ArrayList<>());
    }

    @Override
    public void agregarArista(Estacion<T> estacion1, Estacion<T> estacion2, double peso) {
        grafo.get(estacion1).add(estacion2);
        grafo.get(estacion2).add(estacion1);
    }

    @Override
    public List<Estacion<T>> obtenerVecinos(Estacion<T> estacion) {
        return grafo.get(estacion);
    }

    @Override
    public List<T> obtenerVertices() {

       //Pendiente

           return null;
    }

}

