package org.example;

import org.example.estrucuturas.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Grafo<String> grafo = new Grafo<>();

        // Crear los vértices
        Vertice<String> a = new Vertice<>("A");
        Vertice<String> b = new Vertice<>("B");
        Vertice<String> c = new Vertice<>("C");
        Vertice<String> d = new Vertice<>("D");
        // Agregar los vértices al grafo
        grafo.agregarVertice(a);
        grafo.agregarVertice(b);
        grafo.agregarVertice(c);
        grafo.agregarVertice(d);
        // Agregar las aristas al grafo
        grafo.agregarArista(a, b, 1.5);
        grafo.agregarArista(b, c, 2.0);
        grafo.agregarArista(c, d, 3.5);
        grafo.agregarArista(d, a, 2.8);

        // Obtener los vecinos de un vértice
        List<Vertice<String>> vecinos = grafo.obtenerVecinos(a);
        System.out.println("Vecinos de A: " + vecinos);

        // Obtener todos los vértices del grafo
        List<Vertice<String>> vertices = grafo.obtenerVertices();
        System.out.println("Vértices del grafo: " + vertices);
    }
}