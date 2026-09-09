/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.com350.practica2.zoologico;

/**
 *
 * @author X13
 */

public class Mamifero extends Animal {

    private double temperatura;
    private int numeroPatas;
    private String color;

    public Mamifero() {
        super();
    }

    public Mamifero(String nombre, double temperatura, int numeroPatas, String color) {
        super(nombre);
        this.temperatura = temperatura;
        this.numeroPatas = numeroPatas;
        this.color = color;
    }

    // Método propio de los mamíferos
    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public int getNumeroPatas() {
        return numeroPatas;
    }

    public void setNumeroPatas(int numeroPatas) {
        this.numeroPatas = numeroPatas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Mamifero [" + super.toString() +
                ", temperatura=" + temperatura + "°C" +
                ", numeroPatas=" + numeroPatas +
                ", color='" + color + "'" +
                "]";
    }
}
