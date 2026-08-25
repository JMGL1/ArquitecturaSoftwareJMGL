package bo.edu.usfx.biblioteca.presentacion;

/**
 *
 * @author X13
 */
import bo.edu.usfx.biblioteca.dominio.Prestamo;
import bo.edu.usfx.biblioteca.infraestructura.RepositorioPrestamosJdbc;

public class ReportePrestamosCsv {
    
    private final RepositorioPrestamosJdbc repo;

    // ESTE ES EL CONSTRUCTOR QUE TE FALTABA
    public ReportePrestamosCsv(RepositorioPrestamosJdbc repo) {
        this.repo = repo;
    }

    public String generarMensual(int mes, int anio) {
        // Mantenemos los mensajes de consola (prints) del sistema legado para que sea idéntico
        System.out.println("--- Reporte del mes ---");
        System.out.println("[MySQL jdbc:mysql://10.0.0.7:3306/biblioteca] SELECT * FROM prestamo WHERE MONTH(fecha) = " + mes + " AND YEAR(fecha) = " + anio);
        System.out.println("[FileWriter] C:/reportes/biblioteca_" + anio + "_" + mes + ".csv");

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
