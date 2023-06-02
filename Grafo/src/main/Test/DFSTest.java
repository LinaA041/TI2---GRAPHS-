import org.example.estrucuturas.Grafo;
import org.example.estrucuturas.GrafoImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DFSTest {

    ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testDFSGrafoVacio() {

        // Arrange
        Grafo<Integer> grafo = new Grafo<>();

        // Act
        grafo.dfs(1);

        // Assert
        assertEquals("", outputStream.toString());
    }

    @Test
    public void testDFSGrafoUnSoloVertice() {

        // Arrange
        Grafo<Integer> grafo = new Grafo<>();
        grafo.agregarVertice(1);

        // Act
        grafo.dfs(1);

        // Assert
        assertEquals("1 ", outputStream.toString());
    }

    @Test
    public void testDFSGrafoMultipleVertices() {

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
        assertEquals("A B D E C ", outputStream.toString());
    }

}
