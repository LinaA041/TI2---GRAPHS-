package org.example.estrucuturas;

import java.util.List;
import java.util.Map;

public interface GrafoImplement<T> {
    void agregarVertice(T valor);
    void agregarArista(T origen, T destino, double peso);
    void dfs(T inicio);
    void bfs(T inicio);
    Map<Vertice<T>, Double> dijkstra(T inicio);
    double[][] floydWarshall();
    List<String> prim();
}
