import org.example.estrucuturas.Grafo;
import org.example.estrucuturas.Vertice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DijkstraTest {

    @Test
    public void testDijkstra_GrafoSinConexiones() {

        // Arrange
        Grafo<Integer> grafo = new Grafo<>();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);

        // Act
        Map<Vertice<Integer>, Double> distancias = grafo.dijkstra(1);
        ArrayList<Vertice<Integer>> vertices = new ArrayList<>();
        for (Map.Entry<Vertice<Integer>, Double> entry : distancias.entrySet()) {
            vertices.add(entry.getKey());
        }

        // Assert
        assertEquals(Double.MAX_VALUE, distancias.get(vertices.get(2)));
        assertEquals(Double.MAX_VALUE, distancias.get(vertices.get(3)));
        assertEquals(0.0, distancias.get(vertices.get(1)));
    }

    @Test
    public void testDijkstra_GrafoConConexiones() {
        // Arrange
        Grafo<Integer> grafo = new Grafo<>();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarArista(1, 2, 2.0);
        grafo.agregarArista(1, 3, 3.0);
        grafo.agregarArista(2, 3, 1.0);
        grafo.agregarArista(2, 4, 4.0);
        grafo.agregarArista(3, 4, 2.0);

        // Act
        Map<Vertice<Integer>, Double> distancias = grafo.dijkstra(1);
        ArrayList<Vertice<Integer>> vertices = new ArrayList<>();
        for (Map.Entry<Vertice<Integer>, Double> entry : distancias.entrySet()) {
            vertices.add(entry.getKey());
        }

        // Assert
        assertEquals(0.0, distancias.get(vertices.get(1)));
        assertEquals(2.0, distancias.get(vertices.get(2)));
        assertEquals(3.0, distancias.get(vertices.get(3)));
        assertEquals(5.0, distancias.get(vertices.get(4)));
    }

    @Test
    public void testDijkstra_GrafoConConexionesDesconectadas() {
        // Arrange
        Grafo<Integer> grafo = new Grafo<>();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarArista(1, 2, 2.0);
        grafo.agregarArista(1, 3, 3.0);
        grafo.agregarArista(2, 3, 1.0);
        grafo.agregarVertice(5);

        // Act
        Map<Vertice<Integer>, Double> distancias = grafo.dijkstra(1);
        ArrayList<Vertice<Integer>> vertices = new ArrayList<>();
        for (Map.Entry<Vertice<Integer>, Double> entry : distancias.entrySet()) {
            vertices.add(entry.getKey());
        }

        // Assert
        assertEquals(0.0, distancias.get(vertices.get(1)));
        assertEquals(2.0, distancias.get(vertices.get(2)));
        assertEquals(3.0, distancias.get(vertices.get(3)));
        assertEquals(Double.MAX_VALUE, distancias.get(vertices.get(4)));
        assertEquals(Double.MAX_VALUE, distancias.get(vertices.get(5)));
    }

}
