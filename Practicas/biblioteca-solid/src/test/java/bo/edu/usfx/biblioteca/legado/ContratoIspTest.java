package bo.edu.usfx.biblioteca.legado;

import bo.edu.usfx.biblioteca.dominio.roles.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Segregacion de Interfaces (ISP)")
class ContratoIspTest {

    @Test
    @DisplayName("El ejemplar fisico implementa solo 4 roles")
    void fisicoRoles() {
        EjemplarFisico fisico = new EjemplarFisico();
        assertThat(fisico).isInstanceOf(Prestable.class)
                          .isInstanceOf(Renovable.class)
                          .isInstanceOf(Reservable.class)
                          .isInstanceOf(Restaurable.class);
    }

    @Test
    @DisplayName("El ejemplar digital implementa solo 3 roles")
    void digitalRoles() {
        EjemplarDigital digital = new EjemplarDigital();
        assertThat(digital).isInstanceOf(Prestable.class)
                           .isInstanceOf(Descargable.class)
                           .isInstanceOf(Distribuible.class);
    }
}