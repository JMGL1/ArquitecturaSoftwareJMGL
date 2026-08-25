package bo.edu.usfx.biblioteca.legado;

import bo.edu.usfx.biblioteca.dominio.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("Sustituibilidad de Liskov (LSP)")
public class ContratoLspTest {

    @Test
    @DisplayName("LSP: Ningun material Prestable lanza excepcion al ser prestado")
    void sustituibilidadGarantizada() {
        Material libro = new LibroGeneral("1", "Clean Code");
        Material revista = new Revista("2", "Java Magazine");
        Material referencia = new LibroReferencia("3", "Diccionario"); // No estallará

        Catalogo catalogo = new Catalogo(List.of(libro, revista, referencia));
        LocalDate hoy = LocalDate.now();

        // El cliente procesa la lista sin try/catch. 
        // Si hay una excepcion UnsupportedOperationException, el test fallará.
        assertDoesNotThrow(() -> {
            List<String> comprobantes = catalogo.prestarTodo(hoy);
            
            // Liskov en accion: solo se prestan los 2 que tienen el ROL correcto. 
            // El de referencia es ignorado pacíficamente.
            assertThat(comprobantes).hasSize(2);
            assertThat(comprobantes.get(0)).contains("Clean Code");
            assertThat(comprobantes.get(1)).contains("Java Magazine");
        });
    }
}