package bo.edu.usfx.biblioteca.dominio;

/**
 *
 * @author X13
 */
public class PoliticaEgresado implements PoliticaPrestamo {
    @Override public boolean aplicaA(Usuario u) { return "EGRESADO".equals(u.getTipo()); }
    @Override public int diasPermitidos() { return 5; }
    @Override public int maximoLibros() { return 2; }
    @Override public double tarifaDiaria() { return 3.0; }
}
