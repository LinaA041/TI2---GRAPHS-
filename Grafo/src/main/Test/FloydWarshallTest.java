import org.example.estrucuturas.Grafo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloydWarshallTest {

    @Test
    public void testFloydWarshallGrafoSinAristas() {

        // Arrange
        Grafo<Integer> grafo = new Grafo<>();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);

        // Act
        double[][] distancias = grafo.floydWarshall();

        // Assert
        for (int i = 0; i < distancias.length; i++) {
            for (int j = 0; j < distancias[i].length; j++) {
                if (i == j) {
                    assertEquals(0.0, distancias[i][j]);
                } else {
                    assertEquals(Double.MAX_VALUE, distancias[i][j]);
                }
            }
        }
    }

    @Test
    public void testFloydWarshallGrafoConAristas() {

        // Assert
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
        double[][] distancias = grafo.floydWarshall();

        // Assert
        assertEquals(0.0, distancias[0][0]);
        assertEquals(2.0, distancias[0][1]);
        assertEquals(3.0, distancias[0][2]);
        assertEquals(6.0, distancias[0][3]);

        assertEquals(2.0, distancias[1][0]);
        assertEquals(0.0, distancias[1][1]);
        assertEquals(1.0, distancias[1][2]);
        assertEquals(4.0, distancias[1][3]);

        assertEquals(3.0, distancias[2][0]);
        assertEquals(1.0, distancias[2][1]);
        assertEquals(0.0, distancias[2][2]);
        assertEquals(3.0, distancias[2][3]);

        assertEquals(6.0, distancias[3][0]);
        assertEquals(4.0, distancias[3][1]);
        assertEquals(3.0, distancias[3][2]);
        assertEquals(0.0, distancias[3][3]);
    }

    @Test
    public void testFloydWarshallGrafoDesconectado() {

        // Arrange
        Grafo<Integer> grafo = new Grafo<>();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarArista(1, 2, 2.0);
        grafo.agregarArista(3, 4, 1.0);

        // Act
        double[][] distancias = grafo.floydWarshall();

        // Assert
        assertEquals(0.0, distancias[0][0]);
        assertEquals(2.0, distancias[0][1]);
        assertEquals(Double.MAX_VALUE, distancias[0][2]);
        assertEquals(Double.MAX_VALUE, distancias[0][3]);

        assertEquals(2.0, distancias[1][0]);
        assertEquals(0.0, distancias[1][1]);
        assertEquals(Double.MAX_VALUE, distancias[1][2]);
        assertEquals(Double.MAX_VALUE, distancias[1][3]);

        assertEquals(Double.MAX_VALUE, distancias[2][0]);
        assertEquals(Double.MAX_VALUE, distancias[2][1]);
        assertEquals(0.0, distancias[2][2]);
        assertEquals(1.0, distancias[2][3]);

        assertEquals(Double.MAX_VALUE, distancias[3][0]);
        assertEquals(Double.MAX_VALUE, distancias[3][1]);
        assertEquals(1.0, distancias[3][2]);
        assertEquals(0.0, distancias[3][3]);
    }

}
