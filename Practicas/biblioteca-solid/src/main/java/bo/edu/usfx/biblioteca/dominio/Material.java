
package bo.edu.usfx.biblioteca.dominio;

/**
 *
 * @author X13
 */

public sealed interface Material permits LibroGeneral, Revista, LibroReferencia {
    String signatura();
    String titulo();
}
