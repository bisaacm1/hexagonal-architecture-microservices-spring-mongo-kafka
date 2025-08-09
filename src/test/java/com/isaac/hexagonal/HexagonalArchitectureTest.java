package com.isaac.hexagonal;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;


class HexagonalArchitectureTest {

    private final JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.isaac.hexagonal");

    @Test
    @DisplayName("Domain should not depend on outside layers")
    void domainShouldNotDependOnOutsideLayers() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..core.domain..")
                .should().dependOnClassesThat().resideInAnyPackage(
                        "..adapters..",
                        "..config.."
                );

        rule.check(importedClasses);
    }

    @Test
    @DisplayName("Use cases should not depend on adapters")
    void useCasesShouldNotDependOnAdapters() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..core.usecase..")
                .should().dependOnClassesThat().resideInAPackage("..adapters..");

        rule.check(importedClasses);
    }

    @Test
    @DisplayName("Use cases should only depend on domain and ports")
    void useCasesShouldOnlyDependOnDomainAndPorts() {
        ArchRule rule = classes()
                .that().resideInAPackage("..core.usecase..")
                .should().onlyDependOnClassesThat().resideInAnyPackage(
                        "..core.domain..",
                        "..ports..",
                        "java..",
                        "javax..",
                        "org.springframework..",
                        "lombok.."
                );

        rule.check(importedClasses);
    }

    @Test
    @DisplayName("Ports should only depend on domain")
    void portsShouldOnlyDependOnDomain() {
        ArchRule rule = classes()
                .that().resideInAPackage("..ports..")
                .should().onlyDependOnClassesThat().resideInAnyPackage(
                        "..core.domain..",
                        "java..",
                        "javax.."
                );

        rule.check(importedClasses);
    }

    @Test
    @DisplayName("Hexagonal architecture layers should be respected")
    void hexagonalArchitectureLayersShouldBeRespected() {
        ArchRule rule = layeredArchitecture()
                .consideringAllDependencies()
                .layer("Domain").definedBy("..core.domain..")
                .layer("UseCases").definedBy("..core.usecase..")
                .layer("Ports").definedBy("..ports..")
                .layer("Adapters").definedBy("..adapters..")
                .layer("Config").definedBy("..config..")

                .whereLayer("Domain").mayOnlyBeAccessedByLayers("UseCases", "Ports", "Adapters")
                .whereLayer("UseCases").mayOnlyBeAccessedByLayers("Adapters", "Config")
                .whereLayer("Ports").mayOnlyBeAccessedByLayers("UseCases", "Adapters", "Config");

        rule.check(importedClasses);
    }

    @Test
    @DisplayName("Input ports should be used by adapters or use cases")
    void inputPortsShouldBeUsedByAdapters() {
        ArchRule rule = classes()
                .that().resideInAPackage("..ports.in..")
                .should().onlyBeAccessed().byAnyPackage(
                        "..adapters.in..",
                        "..config..",
                        "..core.usecase..",
                        "..application.core.usecase.."  // Added more specific package
                );

        rule.check(importedClasses);
    }

    @Test
    @DisplayName("Output ports should be used by adapters or use cases")
    void outputPortsShouldBeImplementedByAdapters() {
        ArchRule rule = classes()
                .that().resideInAPackage("..ports.out..")
                .should().onlyBeAccessed().byAnyPackage(
                        "..adapters.out..",
                        "..core.usecase..",
                        "..config..",
                        "..application.core.usecase.."  // Added more specific package
                );

        rule.check(importedClasses);
    }

    @Test
    @DisplayName("No cycles between packages")
    void noCyclesBetweenPackages() {
        ArchRule rule = slices()
                .matching("com.isaac.hexagonal.(*)..")
                .should().beFreeOfCycles();

        rule.check(importedClasses);
    }

    @Test
    @DisplayName("Naming conventions: controllers should be in controller package")
    void controllersShouldBeInControllerPackage() {
        ArchRule rule = classes()
                .that().haveSimpleNameEndingWith("Controller")
                .or().haveSimpleNameEndingWith("ControllerAdapter")
                .should().resideInAPackage("..adapters.in.controller..");

        rule.check(importedClasses);
    }

    @Test
    @DisplayName("Naming conventions: kafka consumers should be in consumer package")
    void consumersShouldBeInConsumerPackage() {
        ArchRule rule = classes()
                .that().haveSimpleNameEndingWith("Consumer")
                .should().resideInAPackage("..adapters.in.consumer..");

        rule.check(importedClasses);
    }

    @Test
    @DisplayName("Naming conventions: database adapters should be in database package")
    void databaseAdaptersShouldBeInDatabasePackage() {
        ArchRule rule = classes()
                .that().haveSimpleNameEndingWith("DatabaseAdapter")
                .or().haveSimpleNameEndingWith("DataBaseAdapter")
                .or().haveSimpleNameEndingWith("Repository")
                .should().resideInAPackage("..adapters.out.database..");

        rule.check(importedClasses);
    }
}
