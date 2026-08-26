package bo.edu.usfx.biblioteca.infraestructura;

/**
 *
 * @author X13
 */

import bo.edu.usfx.biblioteca.dominio.Prestamo;
import bo.edu.usfx.biblioteca.dominio.RepositorioPrestamos;
import bo.edu.usfx.biblioteca.dominio.Usuario;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPrestamosJdbc implements RepositorioPrestamos {
    
    private final DataSource dataSource;

    // DIP: La conexión ya no se crea internamente, viene inyectada
    public RepositorioPrestamosJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void guardar(Prestamo p) {
        String sql = "INSERT INTO prestamo (codigo_usuario, signatura, fecha, limite) VALUES (?, ?, ?, ?)";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getUsuario().getCodigo());
            ps.setString(2, p.getLibro().getSignatura()); // Corregido: getSignatura()
            ps.setObject(3, p.getFechaPrestamo());
            ps.setObject(4, p.getFechaLimite());
            ps.executeUpdate();
        } catch (SQLException e) {
            // Usamos RuntimeException para que no te pida crear una clase de Excepción nueva
            throw new RuntimeException("Error de persistencia", e); 
        }
    }

    @Override
    public List<Prestamo> activosDe(Usuario usuario) {
        return new ArrayList<>(); // Dummy para cumplir el contrato
    }

    @Override
    public List<Prestamo> obtenerTodos() {
        return new ArrayList<>(); // Dummy para que compile ReportePrestamosCsv
    }
}