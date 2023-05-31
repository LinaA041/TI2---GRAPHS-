package org.example;

import org.example.estrucuturas.*;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        GrafoImplement<String> grafo = new Grafo<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");
        grafo.agregarVertice("E");

        grafo.agregarArista("A", "B");
        grafo.agregarArista("A", "C");
        grafo.agregarArista("B", "D");
        grafo.agregarArista("C", "E");
        grafo.agregarArista("D", "E");

        System.out.println("Recorrido DFS:");
        grafo.dfs("A");

        System.out.println("\nRecorrido BFS:");
        grafo.bfs("A");

        System.out.println("\nDistancias desde el vértice 'A' utilizando Dijkstra:");
        Map<Vertice<String>, Integer> distanciasDijkstra = grafo.dijkstra("A");
        for (Map.Entry<Vertice<String>, Integer> entry : distanciasDijkstra.entrySet()) {
            System.out.println(entry.getKey().getValor() + ": " + entry.getValue());
        }

        System.out.println("\nMatriz de distancias utilizando Floyd-Warshall:");
        int[][] distanciasFloydWarshall = grafo.floydWarshall();
        for (int i = 0; i < distanciasFloydWarshall.length; i++) {
            for (int j = 0; j < distanciasFloydWarshall[i].length; j++) {
                System.out.print(distanciasFloydWarshall[i][j] + " ");
            }
            System.out.println();
        }
        List<String> arbolMinimo = grafo.prim();

        System.out.println("Árbol mínimo de Prim:");
        for (String arista : arbolMinimo) {
            System.out.println(arista);
        }
    }
}
