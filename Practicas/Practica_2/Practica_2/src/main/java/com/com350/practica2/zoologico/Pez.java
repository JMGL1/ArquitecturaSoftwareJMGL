/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.com350.practica2.zoologico;

/**
 *
 * @author X13
 */

public class Pez extends Animal {

    private double longitud;

    public Pez() {
        super();
    }

    public Pez(String nombre, double longitud) {
        super(nombre);
        this.longitud = longitud;
    }

    // Método propio de los peces
    public void nadar() {
        System.out.println(nombre + " está nadando.");
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Pez [" + super.toString() +
                ", longitud=" + longitud + "cm" +
                "]";
    }
}
