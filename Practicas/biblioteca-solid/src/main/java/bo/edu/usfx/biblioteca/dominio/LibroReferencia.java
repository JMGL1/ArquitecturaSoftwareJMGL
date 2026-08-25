package bo.edu.usfx.biblioteca.dominio;

public record LibroReferencia(String signatura, String titulo) implements Material {
    // No implementa nada más. Ni prestable ni renovable.
}