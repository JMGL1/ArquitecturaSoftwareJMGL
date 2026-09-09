/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.com350.practica2.zoologico;

/**
 *
 * @author X13
 */

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Zoologico zoologico = new Zoologico("Zoo Municipal", "Av. Siempre Viva 123", "555-1234");

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opcion: ");

            switch (opcion) {
                case 1:
                    añadirMamifero();
                    break;
                case 2:
                    añadirAve();
                    break;
                case 3:
                    añadirPez();
                    break;
                case 4:
                    mostrarMamiferos();
                    break;
                case 5:
                    mostrarAves();
                    break;
                case 6:
                    mostrarPeces();
                    break;
                case 7:
                    mostrarInfoZoologico();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion invalida, intenta de nuevo.");
            }

        } while (opcion != 0);

        sc.close();
    }

    static void mostrarMenu() {
        System.out.println("\n===== MENU ZOOLOGICO =====");
        System.out.println("1. Añadir mamifero");
        System.out.println("2. Añadir ave");
        System.out.println("3. Añadir pez");
        System.out.println("4. Mostrar mamiferos");
        System.out.println("5. Mostrar aves");
        System.out.println("6. Mostrar peces");
        System.out.println("7. Mostrar informacion del zoologico");
        System.out.println("0. Salir");
    }

    static void añadirMamifero() {
        // 1. Se elige la fábrica concreta correspondiente a la familia
        AnimalFactory factory = new MamiferoFactory();
        // 2. La fábrica crea el objeto (el cliente no usa "new Mamifero()")
        Animal animal = factory.crearAnimal();
        Mamifero mamifero = (Mamifero) animal;

        System.out.println("--- Nuevo mamifero (ej: leon, oso, mono) ---");
        System.out.print("Nombre: ");
        mamifero.setNombre(sc.nextLine());
        mamifero.setTemperatura(leerDecimal("Temperatura corporal (°C): "));
        mamifero.setNumeroPatas(leerEntero("Numero de patas: "));
        System.out.print("Color: ");
        mamifero.setColor(sc.nextLine());

        Jaula jaula = crearJaulaParaAnimal(mamifero);
        zoologico.agregarJaula(jaula);
        System.out.println("Mamifero añadido correctamente.");
    }

    static void añadirAve() {
        AnimalFactory factory = new AveFactory();
        Animal animal = factory.crearAnimal();
        Ave ave = (Ave) animal;

        System.out.println("--- Nueva ave (ej: loro, aguila, condor) ---");
        System.out.print("Nombre: ");
        ave.setNombre(sc.nextLine());
        ave.setPeso(leerDecimal("Peso (kg): "));
        ave.setTamanoAlas(leerDecimal("Tamaño de alas (cm): "));

        Jaula jaula = crearJaulaParaAnimal(ave);
        zoologico.agregarJaula(jaula);
        System.out.println("Ave añadida correctamente.");
    }

    static void añadirPez() {
        AnimalFactory factory = new PezFactory();
        Animal animal = factory.crearAnimal();
        Pez pez = (Pez) animal;

        System.out.println("--- Nuevo pez (ej: pacu, sabalo) ---");
        System.out.print("Nombre: ");
        pez.setNombre(sc.nextLine());
        pez.setLongitud(leerDecimal("Longitud (cm): "));

        Jaula jaula = crearJaulaParaAnimal(pez);
        zoologico.agregarJaula(jaula);
        System.out.println("Pez añadido correctamente.");
    }

    static Jaula crearJaulaParaAnimal(Animal animal) {
        System.out.println("--- Datos de la jaula ---");
        double alto = leerDecimal("Alto (m): ");
        double ancho = leerDecimal("Ancho (m): ");
        double largo = leerDecimal("Largo (m): ");
        return new Jaula(animal, alto, ancho, largo);
    }

    static void mostrarMamiferos() {
        System.out.println("\n--- Lista de Mamiferos ---");
        boolean hay = false;
        for (Jaula jaula : zoologico.getJaulas()) {
            if (jaula.getAnimal() instanceof Mamifero) {
                System.out.println(jaula.getAnimal());
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("No hay mamiferos registrados.");
        }
    }

    static void mostrarAves() {
        System.out.println("\n--- Lista de Aves ---");
        boolean hay = false;
        for (Jaula jaula : zoologico.getJaulas()) {
            if (jaula.getAnimal() instanceof Ave) {
                System.out.println(jaula.getAnimal());
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("No hay aves registradas.");
        }
    }

    static void mostrarPeces() {
        System.out.println("\n--- Lista de Peces ---");
        boolean hay = false;
        for (Jaula jaula : zoologico.getJaulas()) {
            if (jaula.getAnimal() instanceof Pez) {
                System.out.println(jaula.getAnimal());
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("No hay peces registrados.");
        }
    }

    static void mostrarInfoZoologico() {
        System.out.println("\n--- Informacion del Zoologico ---");
        System.out.println(zoologico);
        System.out.println("\nDetalle de jaulas:");
        if (zoologico.getJaulas().isEmpty()) {
            System.out.println("El zoologico todavia no tiene animales.");
        } else {
            for (Jaula jaula : zoologico.getJaulas()) {
                System.out.println(jaula);
            }
        }
    }


    static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            System.out.print("Ingresa un numero valido: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine(); // limpiar salto de linea pendiente
        return valor;
    }

    static double leerDecimal(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextDouble()) {
            System.out.print("Ingresa un numero valido: ");
            sc.next();
        }
        double valor = sc.nextDouble();
        sc.nextLine(); // limpiar salto de linea pendiente
        return valor;
    }
}
