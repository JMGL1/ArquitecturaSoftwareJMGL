package com.com350.practica3.venta;

import java.time.LocalDate;
import java.util.ArrayList;

public class Venta {
    private String nombre;
    private LocalDate fecha;
    private String tipoDocumento;
    private String numeroDocumento;
    private ArrayList<DetalleVenta> detalles;

    public Venta(String nombre, LocalDate fecha, String tipoDocumento, String numeroDocumento) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.detalles = new ArrayList<>();
    }

    public void agregarDetalle(DetalleVenta detalle) {
        detalles.add(detalle);
    }

    public double getTotal() {
        double total = 0;
        for (DetalleVenta detalle : detalles) {
            total += detalle.getPrecio();
        }
        return total;
    }

    public void mostrarDetalle() {
        System.out.println("\n========== DETALLE DE LA VENTA ==========");
        System.out.println("Cliente: " + nombre);
        System.out.println("Fecha: " + fecha);
        System.out.println("Tipo de documento: " + tipoDocumento);
        System.out.println("Número de documento: " + numeroDocumento);
        System.out.println("-----------------------------------------");

        if (detalles.isEmpty()) {
            System.out.println("No hay productos en la venta.");
        } else {
            for (DetalleVenta detalle : detalles) {
                System.out.println(detalle.getDescripcion()
                        + " x" + detalle.getCantidad()
                        + " = Bs. " + String.format("%.2f", detalle.getPrecio()));

                if (detalle instanceof ProductoCompuesto) {
                    ((ProductoCompuesto) detalle).mostrarProductos();
                }
            }
        }

        System.out.println("-----------------------------------------");
        System.out.println("TOTAL A COBRAR: Bs. " + String.format("%.2f", getTotal()));
        System.out.println("=========================================\n");
    }
}
