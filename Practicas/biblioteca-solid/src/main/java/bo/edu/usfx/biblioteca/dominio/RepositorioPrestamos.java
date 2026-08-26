package bo.edu.usfx.biblioteca.dominio;

/**
 *
 * @author X13
 */

import java.util.List;

public interface RepositorioPrestamos {
    void guardar(Prestamo prestamo);
    List<Prestamo> activosDe(Usuario usuario);
    List<Prestamo> obtenerTodos();
}