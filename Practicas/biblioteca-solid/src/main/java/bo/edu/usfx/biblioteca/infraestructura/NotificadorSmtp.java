package bo.edu.usfx.biblioteca.infraestructura;

/**
 *
 * @author X13
 */

import bo.edu.usfx.biblioteca.legado.ServidorCorreoSMTP;

public class NotificadorSmtp {
   
    private final ServidorCorreoSMTP correo = new ServidorCorreoSMTP("smtp.usfx.bo", 587);

    public void notificar(String destino, String asunto, String mensaje) {
        correo.enviar(destino, asunto, mensaje);
    }
}
