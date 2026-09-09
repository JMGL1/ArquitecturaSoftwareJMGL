package com.com350.practica3.venta;

public class ProductoFactory {

    public static DetalleVenta crearProducto(int tipo, String descripcion,
                                              double precio, int cantidad) {
        if (tipo == 1) {
            return new ProductoSimple(descripcion, precio, cantidad);
        }

        if (tipo == 2) {
            return new ProductoCompuesto(descripcion, cantidad);
        }

        return null;
    }
}
