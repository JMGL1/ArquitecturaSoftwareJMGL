
package bo.edu.usfx.biblioteca.dominio.roles;

/**
 *
 * @author X13
 */
public interface Prestable {
    void prestar(String codigoUsuario);
    void devolver(String codigoUsuario);
}
