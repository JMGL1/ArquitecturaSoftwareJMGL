package bo.edu.usfx.biblioteca.dominio;

/**
 *
 * @author X13
 */
public class PoliticaDocente implements PoliticaPrestamo {
    @Override public boolean aplicaA(Usuario u) { return "DOCENTE".equals(u.getTipo()); }
    @Override public int diasPermitidos() { return 15; }
    @Override public int maximoLibros() { return 5; }
    @Override public double tarifaDiaria() { return 1.0; }
}