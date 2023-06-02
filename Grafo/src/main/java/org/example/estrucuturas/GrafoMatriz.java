package org.example.estrucuturas;

import java.util.*;

public class GrafoMatriz<T> implements GrafoImplement<T> {
    private Map<T, Integer> indices;
    private List<T> vertices;
    private double[][] matrizAdyacencia;

    public GrafoMatriz() {
        this.indices = new HashMap<>();
        this.vertices = new ArrayList<>();
        this.matrizAdyacencia = new double[0][0];
    }

    @Override
    public void agregarVertice(T valor) {
        if (!indices.containsKey(valor)) {
            indices.put(valor, vertices.size());
            vertices.add(valor);

            // Crear una nueva matriz de adyacencia con el tamaño actualizado
            double[][] nuevaMatriz = new double[vertices.size()][vertices.size()];

            // Copiar los valores de la matriz anterior a la nueva matriz
            for (int i = 0; i < matrizAdyacencia.length; i++) {
                for (int j = 0; j < matrizAdyacencia.length; j++) {
                    nuevaMatriz[i][j] = matrizAdyacencia[i][j];
                }
            }

            matrizAdyacencia = nuevaMatriz;
        }
    }

    @Override
    public void agregarArista(T origen, T destino, double peso) {
        Integer indiceOrigen = indices.get(origen);
        Integer indiceDestino = indices.get(destino);

        if (indiceOrigen != null && indiceDestino != null) {
            matrizAdyacencia[indiceOrigen][indiceDestino] = peso;
            matrizAdyacencia[indiceDestino][indiceOrigen] = peso;
        }
    }

    @Override
    public void dfs(T inicio) {
        Set<T> visitados = new HashSet<>();
        Integer inicioIndex = indices.get(inicio);

        if (inicioIndex != null) {
            dfsRecursivo(inicioIndex, visitados);
        }
    }

    private void dfsRecursivo(int vertice, Set<T> visitados) {
        visitados.add(vertices.get(vertice));
        System.out.print(vertices.get(vertice) + " ");

        for (int i = 0; i < matrizAdyacencia.length; i++) {
            if (matrizAdyacencia[vertice][i] != 0 && !visitados.contains(vertices.get(i))) {
                dfsRecursivo(i, visitados);
            }
        }
    }

    @Override
    public void bfs(T inicio) {
        Set<T> visitados = new HashSet<>();
        Queue<Integer> cola = new LinkedList<>();
        Integer inicioIndex = indices.get(inicio);

        if (inicioIndex != null) {
            cola.offer(inicioIndex);
            visitados.add(inicio);

            while (!cola.isEmpty()) {
                int vertice = cola.poll();
                System.out.print(vertices.get(vertice) + " ");

                for (int i = 0; i < matrizAdyacencia.length; i++) {
                    if (matrizAdyacencia[vertice][i] != 0 && !visitados.contains(vertices.get(i))) {
                        cola.offer(i);
                        visitados.add(vertices.get(i));
                    }
                }
            }
        }
    }

    @Override
    public Map<Vertice<T>, Double> dijkstra(T inicio) {
        return null;
    }

    @Override
    public double[][] floydWarshall() {
        int n = vertices.size();
        double[][] distancias = new double[n][n];

        // Inicializar la matriz de distancias
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    distancias[i][j] = 0;
                } else if (matrizAdyacencia[i][j] != 0) {
                    distancias[i][j] = matrizAdyacencia[i][j];
                } else {
                    distancias[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }

        // Aplicar el algoritmo de Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                    }
                }
            }
        }

        return distancias;
    }

    @Override
    public List<String> prim() {
        int n = vertices.size();
        List<String> aristasMST = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();
        PriorityQueue<Arista<T>> colaPrioridad = new PriorityQueue<>(Comparator.comparingDouble(Arista::getPeso));
        int inicio = 0;

        visitados.add(inicio);

        // Inicializar la cola de prioridad con las aristas adyacentes al vértice inicial
        for (int i = 0; i < n; i++) {
            if (matrizAdyacencia[inicio][i] != 0) {
                colaPrioridad.offer(new Arista<>(new Vertice<>(vertices.get(inicio)), new Vertice<>(vertices.get(i)), matrizAdyacencia[inicio][i]));
            }
        }

        // Aplicar el algoritmo de Prim
        while (!colaPrioridad.isEmpty()) {
            Arista<T> aristaActual = colaPrioridad.poll();
            Vertice<T> origen = aristaActual.getOrigen();
            Vertice<T> destino = aristaActual.getDestino();

            if (!visitados.contains(indices.get(destino.getValor()))) {
                visitados.add(indices.get(destino.getValor()));
                aristasMST.add(aristaActual.toString());

                // Agregar las aristas adyacentes al destino a la cola de prioridad
                for (int i = 0; i < n; i++) {
                    if (matrizAdyacencia[indices.get(destino.getValor())][i] != 0 && !visitados.contains(i)) {
                        colaPrioridad.offer(new Arista<>(destino, new Vertice<>(vertices.get(i)), matrizAdyacencia[indices.get(destino.getValor())][i]));
                    }
                }
            }
        }

        return aristasMST;
    }
}
