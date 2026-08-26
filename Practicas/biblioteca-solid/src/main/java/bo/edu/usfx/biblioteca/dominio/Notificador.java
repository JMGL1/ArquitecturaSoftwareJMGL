package bo.edu.usfx.biblioteca.dominio;

/**
 *
 * @author X13
 */
public interface Notificador {
    void notificar(String destino, String asunto, String mensaje);
}