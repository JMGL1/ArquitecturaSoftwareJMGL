package bo.edu.usfx.biblioteca.aplicacion;

/**
 *
 * @author X13
 */

import bo.edu.usfx.biblioteca.dominio.*;
import bo.edu.usfx.biblioteca.infraestructura.*;
import java.time.LocalDate;

public class ServicioPrestamos {
    private final RepositorioPrestamosJdbc repositorio;
    private final NotificadorSmtp notificador;
    private final CatalogoPoliticas catalogo;

    public ServicioPrestamos(RepositorioPrestamosJdbc repositorio, NotificadorSmtp notificador, CatalogoPoliticas catalogo) {
        this.repositorio = repositorio;
        this.notificador = notificador;
        this.catalogo = catalogo;
    }

    public Prestamo registrar(Usuario usuario, Libro libro, LocalDate hoy) {
        // Obtenemos la politica especifica para ESTE usuario
        PoliticaPrestamo politica = catalogo.para(usuario);
        
        politica.validar(usuario, repositorio.activosDe(usuario), libro);
        
        Prestamo prestamo = new Prestamo(usuario, libro, hoy, politica.calcularFechaLimite(hoy));
        repositorio.guardar(prestamo);
        notificador.notificar(usuario.getCorreo(), "Prestamo registrado", "Devuelva hasta el " + prestamo.getFechaLimite());
        return prestamo;
    }

    public String registrarDevolucion(Prestamo prestamo, LocalDate fechaDevolucion) {
        prestamo.getLibro().setDisponible(true);
        
        PoliticaPrestamo politica = catalogo.para(prestamo.getUsuario());
        double multa = politica.calcularMulta(prestamo, fechaDevolucion);
        
        System.out.println("[MySQL jdbc:mysql://10.0.0.7:3306/biblioteca] UPDATE prestamo SET devolucion = '" + fechaDevolucion + "', multa = " + multa + " WHERE signatura = '" + prestamo.getLibro().getSignatura() + "'");
        System.out.println("[MySQL jdbc:mysql://10.0.0.7:3306/biblioteca] UPDATE libro SET disponible = 1 WHERE signatura = '" + prestamo.getLibro().getSignatura() + "'");
        
        return "Devolucion registrada. Multa: Bs " + multa;
    }
}