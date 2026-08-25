package bo.edu.usfx.biblioteca.dominio;

/**
 *
 * @author X13
 */
import java.time.LocalDate;
import java.util.List;

public class Catalogo {
    private final List<Material> materiales;

    public Catalogo(List<Material> materiales) {
        this.materiales = materiales;
    }

    // El cliente ya NO comprueba tipos ni atrapa excepciones defensivas.
    // Solo pide prestar a lo que de verdad es Prestable.
    public List<String> prestarTodo(LocalDate hoy) {
        return materiales.stream()
                .filter(Prestable.class::isInstance)
                .map(Prestable.class::cast)
                .map(p -> ((Material) p).titulo() + " -> " + p.prestar(hoy))
                .toList();
    }
}