package bo.edu.usfx.biblioteca.presentacion;

/**
 *
 * @author X13
 */
import bo.edu.usfx.biblioteca.dominio.Prestamo;

public class ComprobantePrestamo {
    
    public String imprimir(Prestamo p) {
        return "=== BIBLIOTECA USFX ===\n"
             + "Usuario : " + p.getUsuario().getNombre() + " (" + p.getUsuario().getCodigo() + ")\n"
             + "Titulo  : " + p.getLibro().getTitulo() + "\n"
             + "Entrega : " + p.getFechaLimite() + "\n"
             + "=======================";
    }
}