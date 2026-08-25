package bo.edu.usfx.biblioteca.legado;

import bo.edu.usfx.biblioteca.aplicacion.ServicioPrestamos;
import bo.edu.usfx.biblioteca.dominio.Prestamo;
import bo.edu.usfx.biblioteca.dominio.Usuario;
import bo.edu.usfx.biblioteca.dominio.Libro;
import bo.edu.usfx.biblioteca.dominio.PoliticaPrestamo;
import bo.edu.usfx.biblioteca.infraestructura.NotificadorSmtp;
import bo.edu.usfx.biblioteca.infraestructura.RepositorioPrestamosJdbc;
import bo.edu.usfx.biblioteca.presentacion.ComprobantePrestamo;
import bo.edu.usfx.biblioteca.presentacion.ReportePrestamosCsv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Caracterizacion del modulo de prestamos legado")
class PruebasCaracterizacionTest {

    private final LocalDate HOY = LocalDate.of(2026, 8, 25);

    private Usuario estudiante() { return new Usuario("218123", "Ana Quispe", "ana@usfx.bo", "ESTUDIANTE"); }
    private Libro libro()        { return new Libro("005.1 M379c", "Clean Architecture", "Robert C. Martin"); }

    @Test
    @DisplayName("un estudiante recibe 7 dias de plazo")
    void plazoDelEstudiante() {
        ServicioPrestamos servicio = new ServicioPrestamos(new RepositorioPrestamosJdbc(), new NotificadorSmtp(), new PoliticaPrestamo());
        
        Prestamo prestamo = servicio.registrar(estudiante(), libro(), HOY);

        assertThat(prestamo.getFechaLimite()).isEqualTo(LocalDate.of(2026, 9, 1));
    }

    @Test
    @DisplayName("el comprobante conserva su formato exacto")
    void formatoDelComprobante() {
        ServicioPrestamos servicio = new ServicioPrestamos(new RepositorioPrestamosJdbc(), new NotificadorSmtp(), new PoliticaPrestamo());
        
        Prestamo prestamo = servicio.registrar(estudiante(), libro(), HOY);
        String comprobante = new ComprobantePrestamo().imprimir(prestamo);

        assertThat(comprobante).isEqualTo(
                  "=== BIBLIOTECA USFX ===\n"
                + "Usuario : Ana Quispe (218123)\n"
                + "Titulo  : Clean Architecture\n"
                + "Entrega : 2026-09-01\n"
                + "=======================");
    }

    @Test
    @DisplayName("el estudiante no puede tener mas de 3 ejemplares activos")
    void limiteDeEjemplares() {
        ServicioPrestamos servicio = new ServicioPrestamos(new RepositorioPrestamosJdbc(), new NotificadorSmtp(), new PoliticaPrestamo());
        Usuario ana = estudiante();
        
        for (int i = 1; i <= 3; i++) {
            servicio.registrar(ana, new Libro("SIG-" + i, "Titulo " + i, "Autor"), HOY);
        }

        assertThatThrownBy(() -> servicio.registrar(ana, new Libro("SIG-4", "Cuarto", "Autor"), HOY))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("limite de 3");
    }

    @ParameterizedTest(name = "{0} con {1} dias de retraso paga Bs {2}")
    @DisplayName("tarifa de multa por tipo de usuario")
    @CsvSource({
            "ESTUDIANTE,     5, 10.0",
            "DOCENTE,        5,  5.0",
            "ADMINISTRATIVO, 5,  7.5",
            "EXTERNO,        5, 25.0",
            "ESTUDIANTE,     0,  0.0",
            "EXTERNO,      100, 200.0" 
    })
    void tarifaDeMulta(String tipo, int diasRetraso, double esperado) {
        PoliticaPrestamo politica = new PoliticaPrestamo();
        Usuario usuario = new Usuario("999", "Prueba", "p@usfx.bo", tipo);
        Prestamo prestamo = new Prestamo(usuario, libro(), HOY, HOY.plusDays(7));

        double multa = politica.calcularMulta(prestamo, HOY.plusDays(7 + diasRetraso));

        assertThat(multa).isEqualTo(esperado);
    }

    @Test
    @DisplayName("la devolucion libera el ejemplar y reporta la multa")
    void devolucion() {
        ServicioPrestamos servicio = new ServicioPrestamos(new RepositorioPrestamosJdbc(), new NotificadorSmtp(), new PoliticaPrestamo());
        Libro ejemplar = libro();
        
        Prestamo prestamo = servicio.registrar(estudiante(), ejemplar, HOY);
        String recibo = servicio.registrarDevolucion(prestamo, HOY.plusDays(12));

        assertThat(recibo).isEqualTo("Devolucion registrada. Multa: Bs 10.0");
        assertThat(ejemplar.isDisponible()).isTrue();
    }

    @Test
    @DisplayName("el reporte mensual mantiene su cabecera CSV")
    void cabeceraDelReporte() {
        RepositorioPrestamosJdbc repositorio = new RepositorioPrestamosJdbc();
        ServicioPrestamos servicio = new ServicioPrestamos(repositorio, new NotificadorSmtp(), new PoliticaPrestamo());
        
        servicio.registrar(estudiante(), libro(), HOY);
        String reporteCsv = new ReportePrestamosCsv(repositorio).generarMensual(8, 2026);

        assertThat(reporteCsv)
                .startsWith("codigo;titulo;fecha;limite;multa\n")
                .contains("218123;Clean Architecture;2026-08-25;2026-09-01");
    }
}