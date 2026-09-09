package com.com350.practica3.biblioteca;

public class ProxyLibro implements Libro {
    private LibroReal libroReal;

    public ProxyLibro(LibroReal libroReal) {
        this.libroReal = libroReal;
    }

    @Override
    public void leer() {
        System.out.println("Verificando permisos");
        libroReal.leer();
    }
}
