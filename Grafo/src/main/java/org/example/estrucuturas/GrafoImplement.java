package org.example.estrucuturas;

import java.util.List;

public interface GrafoImplement<T> {
    void agregarVertice(Vertice<T> vertice);
    void agregarArista(Vertice<T> origen, Vertice<T> destino, double peso);
    List<Vertice<T>> obtenerVecinos(Vertice<T> vertice);
    List<Vertice<T>> obtenerVertices();
}
