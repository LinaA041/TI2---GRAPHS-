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

        grafo.agregarArista("A", "B",1);
        grafo.agregarArista("A", "C",2);
        grafo.agregarArista("B", "D",2);
        grafo.agregarArista("C", "E",1);
        grafo.agregarArista("D", "E",4);

        System.out.println("Recorrido DFS:");
        grafo.dfs("A");

        System.out.println("\nRecorrido BFS:");
        grafo.bfs("A");

        System.out.println("\nDistancias desde el vértice 'A' utilizando Dijkstra:");
        Map<Vertice<String>, Double> distanciasDijkstra = grafo.dijkstra("A");
        for (Map.Entry<Vertice<String>, Double> entry : distanciasDijkstra.entrySet()) {
            System.out.println(entry.getKey().getValor() + ": " + entry.getValue());
        }

        System.out.println("\nMatriz de distancias utilizando Floyd-Warshall:");
        double[][] distanciasFloydWarshall = grafo.floydWarshall();
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
