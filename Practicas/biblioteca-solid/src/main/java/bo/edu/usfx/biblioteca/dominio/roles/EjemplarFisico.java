package bo.edu.usfx.biblioteca.dominio.roles;

/**
 *
 * @author X13
 */
public class EjemplarFisico implements Prestable, Renovable, Reservable, Restaurable {
    @Override public void prestar(String codigoUsuario) { /* logica real */ }
    @Override public void devolver(String codigoUsuario) { /* logica real */ }
    @Override public void renovar(String codigoUsuario) { /* logica real */ }
    @Override public void reservar(String codigoUsuario) { /* logica real */ }
    @Override public void enviarARestauracion() { /* logica real */ }
}