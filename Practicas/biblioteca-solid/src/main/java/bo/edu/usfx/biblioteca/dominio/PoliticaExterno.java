package bo.edu.usfx.biblioteca.dominio;

/**
 *
 * @author X13
 */
public class PoliticaExterno implements PoliticaPrestamo {
    @Override public boolean aplicaA(Usuario u) { return "EXTERNO".equals(u.getTipo()); }
    @Override public int diasPermitidos() { return 3; }
    @Override public int maximoLibros() { return 1; }
    @Override public double tarifaDiaria() { return 5.0; }
}