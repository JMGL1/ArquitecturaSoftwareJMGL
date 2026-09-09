package com.com350.practica3.venta;

import java.util.Scanner;

public class MainVentas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaVentasFacade facade = new SistemaVentasFacade();
        boolean salir = false;
        boolean ventaIniciada = false;

        while (!salir) {
            System.out.println("\n=========== MINI SUPERMERCADO ===========");
            System.out.println("1. Iniciar venta");
            System.out.println("2. Agregar producto");
            System.out.println("3. Mostrar detalle de la venta");
            System.out.println("4. Vender");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del cliente: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Tipo de documento: ");
                    String tipoDocumento = scanner.nextLine();
                    System.out.print("Número de documento: ");
                    String numeroDocumento = scanner.nextLine();

                    facade.iniciarVenta(nombre, tipoDocumento, numeroDocumento);
                    ventaIniciada = true;
                    break;

                case 2:
                    if (!ventaIniciada) {
                        System.out.println("Primero debe iniciar una venta.");
                        break;
                    }

                    System.out.println("\n------------- PRODUCTOS -------------");
                    System.out.println("1. Arroz 1 kg          - Bs. 8.50  (Simple)");
                    System.out.println("2. Aceite 1 litro      - Bs. 12.00 (Simple)");
                    System.out.println("3. Galletas            - Bs. 6.00  (Simple)");
                    System.out.println("4. Combo Desayuno      - Bs. 11.00 (Compuesto)");
                    System.out.println("5. Combo Limpieza      - Bs. 20.00 (Compuesto)");
                    System.out.print("Seleccione el producto: ");
                    int codigo = scanner.nextInt();
                    System.out.print("Indique la cantidad: ");
                    int cantidad = scanner.nextInt();
                    scanner.nextLine();

                    facade.agregarProducto(codigo, cantidad);
                    break;

                case 3:
                    facade.mostrarVenta();
                    break;

                case 4:
                    if (!ventaIniciada) {
                        System.out.println("Primero debe iniciar una venta.");
                        break;
                    }

                    System.out.println("\n------------- TIPO DE PAGO -------------");
                    System.out.println("1. Efectivo");
                    System.out.println("2. Tarjeta de crédito");
                    System.out.println("3. Transferencia bancaria");
                    System.out.print("Seleccione el tipo de pago: ");
                    int tipoPago = scanner.nextInt();
                    scanner.nextLine();

                    if (facade.vender(tipoPago)) {
                        ventaIniciada = false;
                    }
                    break;

                case 5:
                    salir = true;
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }
}
