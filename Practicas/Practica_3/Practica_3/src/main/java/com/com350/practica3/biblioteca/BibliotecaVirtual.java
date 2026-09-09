package com.com350.practica3.biblioteca;

import java.util.ArrayList;

public class BibliotecaVirtual {
    private ArrayList<Libro> libros;

    public BibliotecaVirtual() {
        libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public Libro getLibro(int posicion) {
        return libros.get(posicion);
    }
    
    public int cantidadLibros() {
        return libros.size();
    }
}
