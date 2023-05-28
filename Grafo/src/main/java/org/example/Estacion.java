package org.example;

public class Estacion <T> implements Comparable<Estacion<T>> {
    String nombre;
    T tpoBus;
    Arista arista;

    public Estacion(String nombre, T tpoBus) {
        this.nombre = nombre;
        this.tpoBus = tpoBus;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public T getTpoBus() {
        return tpoBus;
    }

    public void setTpoBus(T tpoBus) {
        this.tpoBus = tpoBus;
    }

    @Override
    public String toString() {
        return "Estacion{" +
                "nombre='" + nombre + '\'' +
                ", tpoBus=" + tpoBus +
                '}';
    }

    @Override
    public int compareTo(Estacion<T> o) {
        int Value = this.nombre.compareTo(o.getNombre());
        if (Value == 0) {

            
        }
        return 0;
    }
}
