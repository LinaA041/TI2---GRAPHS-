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
        Integer inicioIndex = indices.get(inicio);

        if (inicioIndex != null) {
            // Inicializar las estructuras de datos
            Map<Integer, Double> distancias = new HashMap<>();
            Map<Integer, Boolean> visitados = new HashMap<>();
            PriorityQueue<Vertice<T>> colaPrioridad = new PriorityQueue<>(Comparator.comparingDouble(Vertice::getPeso));
            Map<Vertice<T>, Double> resultado = new HashMap<>();

            for (int i = 0; i < vertices.size(); i++) {
                if (i == inicioIndex) {
                    distancias.put(i, 0.0);
                } else {
                    distancias.put(i, Double.POSITIVE_INFINITY);
                }
                visitados.put(i, false);
                colaPrioridad.offer(new Vertice<>(vertices.get(i), distancias.get(i)));
            }

            // Aplicar el algoritmo de Dijkstra
            while (!colaPrioridad.isEmpty()) {
                Vertice<T> verticeActual = colaPrioridad.poll();
                int indiceActual = indices.get(verticeActual.getValor());
                visitados.put(indiceActual, true);

                for (int i = 0; i < vertices.size(); i++) {
                    if (matrizAdyacencia[indiceActual][i] != 0 && !visitados.get(i)) {
                        double pesoArista = matrizAdyacencia[indiceActual][i];
                        double distanciaActual = distancias.get(i);
                        double distanciaNueva = distancias.get(indiceActual) + pesoArista;

                        if (distanciaNueva < distanciaActual) {
                            distancias.put(i, distanciaNueva);
                            colaPrioridad.removeIf(v -> v.getValor().equals(vertices.get(i)));
                            colaPrioridad.offer(new Vertice<>(vertices.get(i), distanciaNueva));
                        }
                    }
                }
            }

            // Construir el resultado final
            for (int i = 0; i < vertices.size(); i++) {
                resultado.put(new Vertice<>(vertices.get(i), distancias.get(i)), distancias.get(i));
            }

            return resultado;
        }

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
        return null;
    }
}
