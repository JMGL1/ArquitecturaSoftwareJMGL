package bo.edu.usfx.biblioteca.aplicacion;

import bo.edu.usfx.biblioteca.dominio.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServicioPrestamosTest {

    @Mock private RepositorioPrestamos repositorio;
    @Mock private Notificador notificador;

    @Test
    @DisplayName("registra el prestamo y notifica al estudiante en milisegundos")
    void registraYNotifica() {
        // Configuramos el comportamiento falso (Mock)
        when(repositorio.activosDe(any())).thenReturn(List.of());
        
        CatalogoPoliticas catalogo = new CatalogoPoliticas(List.of(new PoliticaEstudiante()));
        ServicioPrestamos servicio = new ServicioPrestamos(repositorio, notificador, catalogo);
        
        Usuario ana = new Usuario("218123", "Ana Quispe", "ana@usfx.bo", "ESTUDIANTE");
        Libro libro = new Libro("005.1 M379c", "Clean Architecture", "R. C. Martin");
        
        Prestamo prestamo = servicio.registrar(ana, libro, LocalDate.of(2026, 8, 25));
        
        assertThat(prestamo.getFechaLimite()).isEqualTo(LocalDate.of(2026, 9, 1));
        
        // Verificamos que el servicio intentó guardar y notificar a través de las interfaces
        verify(repositorio).guardar(prestamo);
        verify(notificador).notificar(eq("ana@usfx.bo"), anyString(), anyString());
    }
}