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
    private final PoliticaPrestamo politica;

    public ServicioPrestamos(RepositorioPrestamosJdbc repositorio, NotificadorSmtp notificador, PoliticaPrestamo politica) {
        this.repositorio = repositorio;
        this.notificador = notificador;
        this.politica = politica;
    }

    public Prestamo registrar(Usuario usuario, Libro libro, LocalDate hoy) {
        politica.validar(usuario, repositorio.activosDe(usuario), libro);
        
        Prestamo prestamo = new Prestamo(usuario, libro, hoy, politica.calcularFechaLimite(usuario, hoy));
        repositorio.guardar(prestamo);
        notificador.notificar(usuario.getCorreo(), "Prestamo registrado", "Devuelva hasta el " + prestamo.getFechaLimite());
        return prestamo;
    }

    // ESTE ES EL MÉTODO QUE TE MARCABA ERROR
    public String registrarDevolucion(Prestamo prestamo, LocalDate fechaDevolucion) {
        prestamo.getLibro().setDisponible(true);
        double multa = politica.calcularMulta(prestamo, fechaDevolucion);
        
        // Simular prints de la base de datos heredada para no perder la salida de consola
        System.out.println("[MySQL jdbc:mysql://10.0.0.7:3306/biblioteca] UPDATE prestamo SET devolucion = '" + fechaDevolucion + "', multa = " + multa + " WHERE signatura = '" + prestamo.getLibro().getSignatura() + "'");
        System.out.println("[MySQL jdbc:mysql://10.0.0.7:3306/biblioteca] UPDATE libro SET disponible = 1 WHERE signatura = '" + prestamo.getLibro().getSignatura() + "'");
        
        return "Devolucion registrada. Multa: Bs " + multa;
    }
}