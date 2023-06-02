package org.example.estrucuturas;
import java.util.*;

public class Grafo<T> implements GrafoImplement<T>{
    private Map<T, Vertice<T>> vertices;
    private ArrayList<Arista<T>> aristas;

    public Grafo() {
        this.vertices = new HashMap<>();
        this.aristas = new ArrayList<>();
    }

    @Override
    public void agregarVertice(T valor) {
        vertices.put(valor, new Vertice<>(valor));
    }

    @Override
    public void agregarArista(T origen, T destino, double peso) {
        Vertice<T> v1 = vertices.get(origen);
        Vertice<T> v2 = vertices.get(destino);

        if (v1 != null && v2 != null) {
            v1.agregarVecino(v2);
            v2.agregarVecino(v1);
        }
        aristas.add(new Arista<T>(v1,v2,peso));

    }

    @Override
    public void dfs(T inicio) {
        Set<Vertice<T>> visitados = new HashSet<>();
        Vertice<T> verticeInicio = vertices.get(inicio);

        if (verticeInicio != null) {
            dfsRecursivo(verticeInicio, visitados);
        }
    }

    private void dfsRecursivo(Vertice<T> vertice, Set<Vertice<T>> visitados) {
        System.out.print(vertice.getValor() + " ");
        visitados.add(vertice);

        for (Vertice<T> vecino : vertice.getVecinos()) {
            if (!visitados.contains(vecino)) {
                dfsRecursivo(vecino, visitados);
            }
        }
    }

    @Override
    public void bfs(T inicio) {
        Set<Vertice<T>> visitados = new HashSet<>();
        Queue<Vertice<T>> cola = new LinkedList<>();
        Vertice<T> verticeInicio = vertices.get(inicio);

        if (verticeInicio != null) {
            cola.add(verticeInicio);
            visitados.add(verticeInicio);

            while (!cola.isEmpty()) {
                Vertice<T> vertice = cola.poll();
                System.out.print(vertice.getValor() + " ");

                for (Vertice<T> vecino : vertice.getVecinos()) {
                    if (!visitados.contains(vecino)) {
                        cola.add(vecino);
                        visitados.add(vecino);
                    }
                }
            }
        }
    }
    public Map<Vertice<T>, Integer> dijkstra(T inicio) {
        // Inicializar las estructuras de datos
        Map<Vertice<T>, Integer> distancias = new HashMap<>();
        PriorityQueue<Vertice<T>> colaPrioridad = new PriorityQueue<>(Comparator.comparingInt(distancias::get));
        Map<Vertice<T>, Vertice<T>> padres = new HashMap<>();

        // Establecer todas las distancias como infinito excepto el vértice de inicio
        for (Vertice<T> vertice : vertices.values()) {
            distancias.put(vertice, Integer.MAX_VALUE);
        }
        distancias.put(vertices.get(inicio), 0);

        // Agregar el vértice de inicio a la cola de prioridad
        colaPrioridad.offer(vertices.get(inicio));

        while (!colaPrioridad.isEmpty()) {
            Vertice<T> verticeActual = colaPrioridad.poll();

            // Obtener los vecinos del vértice actual
            List<Vertice<T>> vecinos = verticeActual.getVecinos();

            for (Vertice<T> vecino : vecinos) {
                int pesoArista = 1; // Peso predeterminado, puedes ajustarlo según tu implementación

                // Calcular la nueva distancia desde el inicio hasta el vecino
                int nuevaDistancia = distancias.get(verticeActual) + pesoArista;

                // Si la nueva distancia es menor que la distancia actual, actualizarla
                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    padres.put(vecino, verticeActual);
                    colaPrioridad.offer(vecino);
                }
            }
        }

        return distancias;
    }
    public int[][] floydWarshall() {
        int n = vertices.size();
        int[][] distancias = new int[n][n];

        // Inicializar la matriz de distancias con valores infinitos excepto la diagonal
        for (int i = 0; i < n; i++) {
            Arrays.fill(distancias[i], Integer.MAX_VALUE);
            distancias[i][i] = 0;
        }

        // Establecer las distancias iniciales según las aristas existentes
        for (Vertice<T> vertice : vertices.values()) {
            int i = getIndex(vertice);
            List<Vertice<T>> vecinos = vertice.getVecinos();
            for (Vertice<T> vecino : vecinos) {
                int j = getIndex(vecino);
                distancias[i][j] = 1; // Peso predeterminado, puedes ajustarlo según tu implementación
            }
        }

        // Aplicar el algoritmo de Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distancias[i][k] != Integer.MAX_VALUE && distancias[k][j] != Integer.MAX_VALUE) {
                        distancias[i][j] = Math.min(distancias[i][j], distancias[i][k] + distancias[k][j]);
                    }
                }
            }
        }

        return distancias;
    }

    private int getIndex(Vertice<T> vertice) {
        int index = 0;
        for (Vertice<T> v : vertices.values()) {
            if (v == vertice) {
                return index;
            }
            index++;
        }
        return -1;
    }
    public List<String> prim() {
        List<String> arbolMinimo = new ArrayList<>();
        Set<Vertice<T>> visitados = new HashSet<>();
        PriorityQueue<Arista<T>> colaPrioridad = new PriorityQueue<>();

        Vertice<T> primerVertice = vertices.values().iterator().next();
        visitados.add(primerVertice);

        // Agregar las aristas del primer vértice a la cola de prioridad
        for (Vertice<T> vecino : primerVertice.getVecinos()) {
            Arista<T> arista = new Arista<>(primerVertice, vecino, calcularPeso(primerVertice, vecino));
            colaPrioridad.offer(arista);
        }

        while (!colaPrioridad.isEmpty()) {
            Arista<T> aristaActual = colaPrioridad.poll();
            Vertice<T> origen = aristaActual.getOrigen();
            Vertice<T> destino = aristaActual.getDestino();

            if (visitados.contains(destino)) {
                continue;
            }

            // Agregar la arista al árbol mínimo y marcar el destino como visitado
            arbolMinimo.add(aristaActual.toString());
            visitados.add(destino);

            // Agregar las aristas del destino a la cola de prioridad
            for (Vertice<T> vecino : destino.getVecinos()) {
                if (!visitados.contains(vecino)) {
                    Arista<T> arista = new Arista<>(destino, vecino, calcularPeso(destino, vecino));
                    colaPrioridad.offer(arista);
                }
            }
        }

        return arbolMinimo;
    }
    private int calcularPeso(Vertice<T> origen, Vertice<T> destino) {
        // Aquí puedes implementar la lógica para calcular el peso de la arista
        // entre el origen y el destino según tu implementación específica

        return 0;
    }

}
