package com.com350.practica3.biblioteca;

public class LibroReal implements Libro {
    private String titulo;
    private String autor;
    private int anio;
    private String contenido;

    public LibroReal(String titulo, String autor, int anio, String contenido) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.contenido = contenido;
    }

    @Override
    public void leer() {
        System.out.println("\n--- LIBRO ---");
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Año: " + anio);
        System.out.println("Contenido: " + contenido);
    }
}
