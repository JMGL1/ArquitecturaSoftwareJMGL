package bo.edu.usfx.biblioteca.dominio.roles;

public class EjemplarDigital implements Prestable, Descargable, Distribuible {
    @Override public void prestar(String codigoUsuario) { /* logica real */ }
    @Override public void devolver(String codigoUsuario) { /* logica real */ }
    @Override public byte[] descargarPdf() { return new byte[0]; }
    @Override public void enviarPorCorreo(String destino) { /* logica real */ }
}