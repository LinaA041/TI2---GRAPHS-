package org.example;

import java.util.List;

public interface GrafosImplemnt <T> {
    void agregarVertice(Estacion<T> estacion);
    void agregarArista(Estacion<T> estacion1, Estacion<T> estacion2, double peso);
    List<Estacion<T>> obtenerVecinos(Estacion<T> estacion);

    List<T> obtenerVertices();
}
