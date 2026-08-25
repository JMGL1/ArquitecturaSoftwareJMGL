package bo.edu.usfx.biblioteca.dominio;

/**
 *
 * @author X13
 */
public class PoliticaEstudiante implements PoliticaPrestamo {
    @Override public boolean aplicaA(Usuario u) { return "ESTUDIANTE".equals(u.getTipo()); }
    @Override public int diasPermitidos() { return 7; }
    @Override public int maximoLibros() { return 3; }
    @Override public double tarifaDiaria() { return 2.0; }
}
