package com.com350.practica3.venta;

import java.util.ArrayList;

public class ProductoCompuesto implements DetalleVenta {
    private String descripcion;
    private int cantidad;
    private ArrayList<DetalleVenta> productos;

    public ProductoCompuesto(String descripcion, int cantidad) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(DetalleVenta producto) {
        productos.add(producto);
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }

    @Override
    public double getPrecio() {
        double total = 0;
        for (DetalleVenta producto : productos) {
            total += producto.getPrecio();
        }
        return total * cantidad;
    }

    public void mostrarProductos() {
        for (DetalleVenta producto : productos) {
            System.out.println("   - " + producto.getDescripcion()
                    + " x" + producto.getCantidad()
                    + " = Bs. " + String.format("%.2f", producto.getPrecio()));
        }
    }
}
