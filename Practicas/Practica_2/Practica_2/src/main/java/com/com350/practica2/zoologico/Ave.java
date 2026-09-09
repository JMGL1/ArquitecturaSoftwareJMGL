/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.com350.practica2.zoologico;

/**
 *
 * @author X13
 */
public class Ave extends Animal {

    private double peso;
    private double tamanoAlas;

    public Ave() {
        super();
    }

    public Ave(String nombre, double peso, double tamanoAlas) {
        super(nombre);
        this.peso = peso;
        this.tamanoAlas = tamanoAlas;
    }

    // Método propio de las aves
    public void volar() {
        System.out.println(nombre + " está volando.");
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTamanoAlas() {
        return tamanoAlas;
    }

    public void setTamanoAlas(double tamanoAlas) {
        this.tamanoAlas = tamanoAlas;
    }

    @Override
    public String toString() {
        return "Ave [" + super.toString() +
                ", peso=" + peso + "kg" +
                ", tamanoAlas=" + tamanoAlas + "cm" +
                "]";
    }
}
