package bo.edu.usfx.biblioteca.arquitectura;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

class ReglasDisenoTest {

    // ArchUnit analiza el bytecode cargando todas las clases de tu paquete principal
    private final JavaClasses CLASES = new ClassFileImporter().importPackages("bo.edu.usfx.biblioteca");

    @Test
    @DisplayName("R1 - el dominio no conoce la infraestructura (DIP)")
    void elDominioNoConoceLaInfraestructura() {
        noClasses()
            .that().resideInAPackage("..dominio..")
            .should().dependOnClassesThat()
            .resideInAnyPackage("..infraestructura..", "..persistencia..", "..ui..")
            .allowEmptyShould(true).check(CLASES);
    }

    @Test
    @DisplayName("R2 - Nadie fuera de infraestructura importa java.sql (DIP)")
    void sinJavaSqlFueraDeInfraestructura() {
        noClasses()
            .that().resideOutsideOfPackage("..infraestructura..")
            // Excluimos la clase Main porque es el Composition Root donde se ensambla todo
            .and().haveSimpleNameNotStartingWith("Main")
            .should().dependOnClassesThat().resideInAPackage("java.sql..")
            .allowEmptyShould(true).check(CLASES);
    }

    @Test
    @DisplayName("R3 - Las clases Repositorio* del dominio son interfaces (DIP)")
    void repositoriosSonInterfaces() {
        classes()
            .that().resideInAPackage("..dominio..")
            .and().haveSimpleNameStartingWith("Repositorio")
            .should().beInterfaces()
            .allowEmptyShould(true).check(CLASES);
    }

    @Test
    @DisplayName("R4 - ninguna clase usa UnsupportedOperationException (LSP / ISP)")
    void sinOperacionesNoSoportadas() {
        noClasses()
            .that().resideOutsideOfPackage("..legado..")
            .should().dependOnClassesThat()
            .areAssignableTo(UnsupportedOperationException.class)
            .allowEmptyShould(true).check(CLASES);
    }

    @Test
    @DisplayName("R5 - No existen clases Gestor*, *Manager ni *Utils (SRP)")
    void sinClasesDios() {
        noClasses()
            .that().resideOutsideOfPackage("..legado..")
            .should().haveSimpleNameStartingWith("Gestor")
            .orShould().haveSimpleNameEndingWith("Manager")
            .orShould().haveSimpleNameEndingWith("Utils")
            .allowEmptyShould(true).check(CLASES);
    }

    @Test
    @DisplayName("R6 - El grafo de paquetes no tiene ciclos (ADP)")
    void sinCiclosDeDependencia() {
        slices().matching("bo.edu.usfx.biblioteca.(*)..")
            .should().beFreeOfCycles()
            .allowEmptyShould(true).check(CLASES);
    }
}