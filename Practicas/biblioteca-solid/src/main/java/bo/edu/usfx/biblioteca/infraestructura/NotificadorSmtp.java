package bo.edu.usfx.biblioteca.infraestructura;

import bo.edu.usfx.biblioteca.dominio.Notificador;

public class NotificadorSmtp implements Notificador {
    
    private final String host;
    private final int puerto;

    // Constructor que exige Main.java (soluciona tu error 1)
    public NotificadorSmtp(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    @Override
    public void notificar(String destino, String asunto, String mensaje) {
        System.out.println("[SMTP " + host + ":" + puerto + "] Correo a " + destino + ": " + asunto);
    }
}