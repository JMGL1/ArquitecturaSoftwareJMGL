package com.com350.practica3.venta;

public class ProductoSimple implements DetalleVenta {
    private String descripcion;
    private double precioUnitario;
    private int cantidad;

    public ProductoSimple(String descripcion, double precioUnitario, int cantidad) {
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
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
        return precioUnitario * cantidad;
    }
}
