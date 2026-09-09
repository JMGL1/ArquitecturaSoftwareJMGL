package com.com350.practica3.venta;

import java.time.LocalDate;

public class SistemaVentasFacade {
    private Venta venta;

    public void iniciarVenta(String nombre, String tipoDocumento, String numeroDocumento) {
        venta = new Venta(nombre, LocalDate.now(), tipoDocumento, numeroDocumento);
        System.out.println("Venta iniciada correctamente.");
    }

    public void agregarProducto(int codigo, int cantidad) {
        if (venta == null) {
            System.out.println("Primero debe iniciar una venta.");
            return;
        }

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor que 0.");
            return;
        }

        DetalleVenta producto;

        switch (codigo) {
            case 1:
                producto = ProductoFactory.crearProducto(1, "Arroz 1 kg", 8.50, cantidad);
                break;
            case 2:
                producto = ProductoFactory.crearProducto(1, "Aceite 1 litro", 12.00, cantidad);
                break;
            case 3:
                producto = ProductoFactory.crearProducto(1, "Galletas", 6.00, cantidad);
                break;
            case 4:
                ProductoCompuesto comboDesayuno = (ProductoCompuesto)
                        ProductoFactory.crearProducto(2, "Combo Desayuno", 0, cantidad);
                comboDesayuno.agregarProducto(
                        ProductoFactory.crearProducto(1, "Café", 8.00, 1));
                comboDesayuno.agregarProducto(
                        ProductoFactory.crearProducto(1, "Pan", 3.00, 1));
                producto = comboDesayuno;
                break;
            case 5:
                ProductoCompuesto comboLimpieza = (ProductoCompuesto)
                        ProductoFactory.crearProducto(2, "Combo Limpieza", 0, cantidad);
                comboLimpieza.agregarProducto(
                        ProductoFactory.crearProducto(1, "Detergente", 15.00, 1));
                comboLimpieza.agregarProducto(
                        ProductoFactory.crearProducto(1, "Esponja", 5.00, 1));
                producto = comboLimpieza;
                break;
            default:
                System.out.println("Producto no válido.");
                return;
        }

        venta.agregarDetalle(producto);
        System.out.println("Producto agregado correctamente.");
    }

    public void mostrarVenta() {
        if (venta == null) {
            System.out.println("Primero debe iniciar una venta.");
            return;
        }
        venta.mostrarDetalle();
    }

    public boolean vender(int tipoPago) {
        if (venta == null) {
            System.out.println("Primero debe iniciar una venta.");
            return false;
        }

        if (venta.getTotal() <= 0) {
            System.out.println("No se puede vender porque la venta está vacía.");
            return false;
        }

        venta.mostrarDetalle();

        Pago pago = PagoFactory.crearPago(tipoPago);
        if (pago == null) {
            System.out.println("Tipo de pago no válido.");
            return false;
        }

        pago.pagar(venta.getTotal());
        System.out.println("Venta realizada correctamente.");
        venta = null;
        return true;
    }
}
