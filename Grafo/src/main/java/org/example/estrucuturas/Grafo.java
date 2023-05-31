package org.example.estrucuturas;
import java.util.*;

public class Grafo<T> implements GrafoImplement<T>{
    private Map<Vertice<T>, List<Arista<T>>> grafo;

    public Grafo() {
        this.grafo = new HashMap<>();
    }

    @Override
    public void agregarVertice(Vertice<T> vertice) {
        grafo.put(vertice, new ArrayList<>());
    }

    @Override
    public void agregarArista(Vertice<T> origen, Vertice<T> destino, double peso) {
        Arista<T> arista = new Arista<>(origen, destino, peso);
        grafo.get(origen).add(arista);
        grafo.get(destino).add(arista);
    }

    @Override
    public List<Vertice<T>> obtenerVecinos(Vertice<T> vertice) {
        List<Vertice<T>> vecinos = new ArrayList<>();
        for (Arista<T> arista : grafo.get(vertice)) {
            Vertice<T> vecino = arista.getOrigen() == vertice ? arista.getDestino() : arista.getOrigen();
            vecinos.add(vecino);
        }
        return vecinos;
    }

    @Override
    public List<Vertice<T>> obtenerVertices() {
        return new ArrayList<>(grafo.keySet());
    }
}
