
package bo.edu.usfx.biblioteca.dominio;

/**
 *
 * @author X13
 */
import java.time.LocalDate;

public interface Prestable {
    LocalDate prestar(LocalDate hoy);
}
