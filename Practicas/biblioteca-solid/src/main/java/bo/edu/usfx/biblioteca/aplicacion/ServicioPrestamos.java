package bo.edu.usfx.biblioteca.aplicacion;

import bo.edu.usfx.biblioteca.dominio.*;
import java.time.LocalDate;

public class ServicioPrestamos {
    // AQUI ESTA LA MAGIA: Dependemos de abstracciones, no de clases concretas
    private final RepositorioPrestamos repositorio;
    private final Notificador notificador;
    private final CatalogoPoliticas catalogo;

    public ServicioPrestamos(RepositorioPrestamos repositorio, Notificador notificador, CatalogoPoliticas catalogo) {
        this.repositorio = repositorio;
        this.notificador = notificador;
        this.catalogo = catalogo;
    }

    public Prestamo registrar(Usuario usuario, Libro libro, LocalDate hoy) {
        PoliticaPrestamo politica = catalogo.para(usuario);
        politica.validar(usuario, repositorio.activosDe(usuario), libro);
        
        Prestamo prestamo = new Prestamo(usuario, libro, hoy, politica.calcularFechaLimite(hoy));
        repositorio.guardar(prestamo);
        notificador.notificar(usuario.getCorreo(), "Prestamo registrado", "Devuelva hasta el " + prestamo.getFechaLimite());
        return prestamo;
    }

    public String registrarDevolucion(Prestamo prestamo, LocalDate fechaDevolucion) {
        prestamo.getLibro().setDisponible(true);
        double multa = catalogo.para(prestamo.getUsuario()).calcularMulta(prestamo, fechaDevolucion);
        return "Devolucion registrada. Multa: Bs " + multa;
    }
}