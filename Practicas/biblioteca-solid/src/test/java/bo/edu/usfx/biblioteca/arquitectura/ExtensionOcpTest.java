package bo.edu.usfx.biblioteca.arquitectura;

/**
 *
 * @author X13
 */
import bo.edu.usfx.biblioteca.dominio.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ExtensionOcpTest {

    @Test
    @DisplayName("OCP: se agrega EGRESADO sin tocar el codigo existente")
    void extensionSinModificacion() {
        // Ensamblamos el catalogo con la NUEVA politica (esta es la UNICA linea que se toca)
        CatalogoPoliticas catalogo = new CatalogoPoliticas(List.of(
                new PoliticaEstudiante(), new PoliticaDocente(),
                new PoliticaAdministrativo(), new PoliticaExterno(),
                new PoliticaEgresado() // <-- AGREGADO
        ));

        Usuario juan = new Usuario("205001", "Juan", "juan@usfx.bo", "EGRESADO");
        Libro libro = new Libro("123", "Prueba", "Autor");
        LocalDate hoy = LocalDate.of(2026, 8, 25);
        Prestamo prestamo = new Prestamo(juan, libro, hoy, hoy.plusDays(5));

        // Verificamos que el catalogo le da 5 dias de plazo y Bs 3.0 diarios de multa
        assertThat(catalogo.para(juan).calcularFechaLimite(hoy)).isEqualTo(hoy.plusDays(5));
        
        // Si retrasa 4 días, la multa es 4 * 3.0 = 12.0
        double multa = catalogo.para(juan).calcularMulta(prestamo, prestamo.getFechaLimite().plusDays(4));
        assertThat(multa).isEqualTo(12.0);
    }
}
