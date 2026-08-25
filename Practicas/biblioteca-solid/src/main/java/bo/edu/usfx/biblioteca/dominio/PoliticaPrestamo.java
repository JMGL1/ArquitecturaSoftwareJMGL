package bo.edu.usfx.biblioteca.dominio;

/**
 *
 * @author X13
 */
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class PoliticaPrestamo {

    public void validar(Usuario usuario, List<Prestamo> activos, Libro libro) {
        int maximoLibros = 0;
        if ("ESTUDIANTE".equals(usuario.getTipo())) maximoLibros = 3;
        else if ("DOCENTE".equals(usuario.getTipo())) maximoLibros = 5;
        else if ("ADMINISTRATIVO".equals(usuario.getTipo())) maximoLibros = 2;
        else if ("EXTERNO".equals(usuario.getTipo())) maximoLibros = 1;

        if (activos.size() >= maximoLibros) {
            throw new IllegalStateException("limite de " + maximoLibros);
        }
    }

    public LocalDate calcularFechaLimite(Usuario usuario, LocalDate hoy) {
        int diasPermitidos = 0;
        if ("ESTUDIANTE".equals(usuario.getTipo())) diasPermitidos = 7;
        else if ("DOCENTE".equals(usuario.getTipo())) diasPermitidos = 15;
        else if ("ADMINISTRATIVO".equals(usuario.getTipo())) diasPermitidos = 10;
        else if ("EXTERNO".equals(usuario.getTipo())) diasPermitidos = 3;
        return hoy.plusDays(diasPermitidos);
    }

    public double calcularMulta(Prestamo prestamo, LocalDate fechaDevolucion) {
        long diasRetraso = ChronoUnit.DAYS.between(prestamo.getFechaLimite(), fechaDevolucion);
        if (diasRetraso <= 0) return 0.0;

        double tarifa = 0;
        String tipo = prestamo.getUsuario().getTipo();
        if ("ESTUDIANTE".equals(tipo)) tarifa = 2.0;
        else if ("DOCENTE".equals(tipo)) tarifa = 1.0;
        else if ("ADMINISTRATIVO".equals(tipo)) tarifa = 1.5;
        else if ("EXTERNO".equals(tipo)) tarifa = 5.0;

        double multa = diasRetraso * tarifa;
        return Math.min(multa, 200.0);
    }
}
