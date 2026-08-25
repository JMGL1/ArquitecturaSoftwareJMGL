package bo.edu.usfx.biblioteca.infraestructura;

/**
 *
 * @author X13
 */

import bo.edu.usfx.biblioteca.dominio.Prestamo;
import bo.edu.usfx.biblioteca.dominio.Usuario;
import bo.edu.usfx.biblioteca.legado.ConexionMySQL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioPrestamosJdbc {
    
    private final ConexionMySQL conexion = new ConexionMySQL("jdbc:mysql://10.0.0.7:3306/biblioteca", "root", "usfx2026");
    
    private final List<Prestamo> bdSimulada = new ArrayList<>();

    public void guardar(Prestamo prestamo) {
        bdSimulada.add(prestamo);
        String sql = "INSERT INTO prestamo (codigo_usuario, signatura, fecha, limite) VALUES ('"
                + prestamo.getUsuario().getCodigo() + "', '"
                + prestamo.getLibro().getSignatura() + "', '"
                + prestamo.getFechaPrestamo() + "', '"
                + prestamo.getFechaLimite() + "')";
        conexion.ejecutar(sql);
    }

    public List<Prestamo> activosDe(Usuario usuario) {
        return bdSimulada.stream()
                .filter(p -> p.getUsuario().getCodigo().equals(usuario.getCodigo()))
                .collect(Collectors.toList());
    }

    public List<Prestamo> obtenerTodos() {
        return bdSimulada;
    }
} // <-- ESTA ES LA LLAVE QUE TE FALTABA