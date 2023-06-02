package org.example.Gson;

import com.google.gson.Gson;
import org.example.estrucuturas.Grafo;
import org.example.estrucuturas.GrafoImplement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class GsonWritter {
    public static void main(String[] args) {
        Gson gson = new Gson();

        Grafo<String> graphs = new Grafo<>();

        graphs.agregarVertice("a");
        graphs.agregarVertice("b");
        graphs.agregarVertice("c");
        graphs.agregarVertice("d");
        graphs.agregarVertice("e");

        graphs.agregarArista("A", "B",1);
        graphs.agregarArista("A", "C",2);
        graphs.agregarArista("B", "D",2);
        graphs.agregarArista("C", "E",1);
        graphs.agregarArista("D", "E",4);


        String json = gson.toJson(graphs);
        System.out.print(json);

        try {
            FileOutputStream fos = new FileOutputStream(new File("graphs.txt"));
            fos.write( json.getBytes(StandardCharsets.UTF_8) );
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
