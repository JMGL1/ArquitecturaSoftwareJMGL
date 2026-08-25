package bo.edu.usfx.biblioteca.dominio;

/**
 *
 * @author X13
 */
import java.util.List;

public class CatalogoPoliticas {
    
    private final List<PoliticaPrestamo> politicas;

    public CatalogoPoliticas(List<PoliticaPrestamo> politicas) {
        this.politicas = politicas;
    }

    public PoliticaPrestamo para(Usuario usuario) {
        return politicas.stream()
                .filter(p -> p.aplicaA(usuario))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de usuario desconocido"));
    }
}
