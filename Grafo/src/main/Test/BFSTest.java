import org.example.estrucuturas.Grafo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BFSTest {

    ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testBFSGrafoVacio() {

        // Arrange
        Grafo<Integer> grafo = new Grafo<>();

        // Act
        grafo.bfs(1);

        // Assert
        assertEquals("", outputStream.toString());
    }

    @Test
    public void testBFSGrafoUnSoloVertice() {

        // Arrange
        Grafo<Integer> grafo = new Grafo<>();
        grafo.agregarVertice(1);

        // Act
        grafo.bfs(1);

        // Assert
        assertEquals("1 ", outputStream.toString());
    }

    @Test
    public void testBFSGrafoMultipleVertices() {

        // Arrange
        Grafo<String> grafo = new Grafo<>();
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

        // Act
        grafo.dfs("A");

        // Assert
        assertEquals("A B C D E ", outputStream.toString());
    }

}
