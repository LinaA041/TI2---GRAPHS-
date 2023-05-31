package org.example.estrucuturas;

public class Arista<T> implements Comparable<Arista<T>>{

        private Vertice<T> origen;
        private Vertice<T> destino;
        private double peso;

        public Arista(Vertice<T> origen, Vertice<T> destino, double peso) {
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;
        }

        public Vertice<T> getOrigen() {
            return origen;
        }

        public Vertice<T> getDestino() {
            return destino;
        }

        public double getPeso() {
            return peso;
        }
    @Override
    public int compareTo(Arista<T> otra) {
        return Double.compare(this.peso, otra.peso);
    }
    @Override
    public String toString() {
        return origen.getValor() + " - " + destino.getValor() + " (" + peso + ")";
    }
}

