package bo.edu.usfx.biblioteca.dominio;

/**
 *
 * @author X13
 */

import java.time.LocalDate;

public interface Renovable {
    LocalDate renovar(LocalDate limiteActual);
}
