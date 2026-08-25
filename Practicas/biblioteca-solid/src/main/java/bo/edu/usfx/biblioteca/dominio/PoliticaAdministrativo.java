package bo.edu.usfx.biblioteca.dominio;

/**
 *
 * @author X13
 */
public class PoliticaAdministrativo implements PoliticaPrestamo {
    @Override public boolean aplicaA(Usuario u) { return "ADMINISTRATIVO".equals(u.getTipo()); }
    @Override public int diasPermitidos() { return 10; }
    @Override public int maximoLibros() { return 2; }
    @Override public double tarifaDiaria() { return 1.5; }
}