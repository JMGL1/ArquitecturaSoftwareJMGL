package bo.edu.usfx.biblioteca.presentacion;

/**
 *
 * @author X13
 */
import bo.edu.usfx.biblioteca.dominio.Prestamo;
import bo.edu.usfx.biblioteca.dominio.RepositorioPrestamos;

public class ReportePrestamosCsv {
    
    // AHORA USA LA INTERFAZ
    private final RepositorioPrestamos repo;

    public ReportePrestamosCsv(RepositorioPrestamos repo) {
        this.repo = repo;
    }

    public String generarMensual(int mes, int anio) {
        StringBuilder sb = new StringBuilder("codigo;titulo;fecha;limite;multa\n");
        for (Prestamo p : repo.obtenerTodos()) {
            sb.append(p.getUsuario().getCodigo()).append(";")
              .append(p.getLibro().getTitulo()).append(";")
              .append(p.getFechaPrestamo()).append(";")
              .append(p.getFechaLimite()).append(";0.0\n");
        }
        return sb.toString();
    }
}
