package bo.edu.usfx.biblioteca.dominio;

/**
 *
 * @author X13
 */
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface PoliticaPrestamo {
    
    // Estos son los "datos" que cada tipo de usuario responderá diferente
    boolean aplicaA(Usuario usuario);
    int diasPermitidos();
    int maximoLibros();
    double tarifaDiaria();

    // El comportamiento estándar (fórmulas) ya no tiene if/else
    default void validar(Usuario usuario, List<Prestamo> activos, Libro libro) {
        if (activos.size() >= maximoLibros()) {
            throw new IllegalStateException("limite de " + maximoLibros());
        }
    }

    default LocalDate calcularFechaLimite(LocalDate hoy) {
        return hoy.plusDays(diasPermitidos());
    }

    default double calcularMulta(Prestamo prestamo, LocalDate fechaDevolucion) {
        long diasRetraso = ChronoUnit.DAYS.between(prestamo.getFechaLimite(), fechaDevolucion);
        if (diasRetraso <= 0) return 0.0;

        double multa = diasRetraso * tarifaDiaria();
        return Math.min(multa, 200.0); // El tope es 200
    }
}