/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.com350.practica2.zoologico;

/**
 *
 * @author X13
 */

import java.util.ArrayList;

public class Zoologico {

    private String nombre;
    private String direccion;
    private String telefono;
    private ArrayList<Jaula> jaulas;

    public Zoologico(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.jaulas = new ArrayList<>();
    }

    public void agregarJaula(Jaula jaula) {
        jaulas.add(jaula);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ArrayList<Jaula> getJaulas() {
        return jaulas;
    }

    public void setJaulas(ArrayList<Jaula> jaulas) {
        this.jaulas = jaulas;
    }

    @Override
    public String toString() {
        return "Zoologico [nombre='" + nombre + "'" +
                ", direccion='" + direccion + "'" +
                ", telefono='" + telefono + "'" +
                ", cantidadDeJaulas=" + jaulas.size() +
                "]";
    }
}
