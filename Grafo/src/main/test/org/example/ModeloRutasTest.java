package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;


class ModeloRutasTest {

    @Test
    void test1() {
        GrafosImplemnt<Estacion> grafo = new ModeloRutas<>();

        Estacion estacionA = new Estacion("Estación A", "Tipo de Bus A");
        Estacion estacionB = new Estacion("Estación B", "Tipo de Bus B");
        Estacion estacionC = new Estacion("Estación C", "Tipo de Bus A");

        grafo.agregarVertice(estacionA);
        grafo.agregarVertice(estacionB);
        grafo.agregarVertice(estacionC);

        grafo.agregarArista(estacionA, estacionB, 5);
        grafo.agregarArista(estacionB, estacionC, 3);
        grafo.agregarArista(estacionC, estacionA, 2);

    }

    @Test
    void test2() {
        GrafosImplemnt<Estacion> grafo = new ModeloRutas<>();

        Estacion estacionA = new Estacion("Estación A", "Tipo de Bus A");
        Estacion estacionB = new Estacion("Estación B", "Tipo de Bus B");
        Estacion estacionC = new Estacion("Estación C", "Tipo de Bus A");

        grafo.agregarVertice(estacionA);
        grafo.agregarVertice(estacionB);
        grafo.agregarVertice(estacionC);

        grafo.agregarArista(estacionA, estacionB, 5);
        grafo.agregarArista(estacionB, estacionC, 3);
        grafo.agregarArista(estacionC, estacionA, 2);

        List<Estacion> vecinos = grafo.obtenerVecinos(estacionB);
        System.out.println("Vecinos de Estación B:");
        for (Estacion vecino : vecinos) {
            System.out.println(vecino.getNombre());
    }}


    @Test
    void test3() {
        GrafosImplemnt<Estacion> grafo = new ModeloRutas<>();

        Estacion estacionA = new Estacion("Estación A", "P21E");
        Estacion estacionB = new Estacion("Estación B", "P21E");
        Estacion estacionC = new Estacion("Estación C", "P21E");

        grafo.agregarVertice(estacionA);
        grafo.agregarVertice(estacionB);
        grafo.agregarVertice(estacionC);

        grafo.agregarArista(estacionA, estacionB, 5);
        grafo.agregarArista(estacionB, estacionC, 3);
        grafo.agregarArista(estacionC, estacionA, 2);

        System.out.println("Recorrido de todas las estaciones:");

        for (Estacion estacion : grafo.obtenerVertices()) {
            System.out.println(estacion.getNombre());
        }

    }
}