package com.com350.practica3.biblioteca;

public class MainBiblioteca {
    public static void main(String[] args) {
        LibroReal libroReal = new LibroReal(
                "El Principito",
                "Antoine de Saint-Exupéry",
                1943,
                "Cuando se mira al cielo, de noche, se ve una estrella diferente."
        );

        Libro proxy = new ProxyLibro(libroReal);

        BibliotecaVirtual biblioteca = new BibliotecaVirtual();
        biblioteca.agregarLibro(proxy);

        System.out.println("Cantidad de libros en la biblioteca: " + biblioteca.cantidadLibros());
        biblioteca.getLibro(0).leer();
    }
}
