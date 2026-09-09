package com.com350.practica3.venta;

public class PagoEfectivo implements Pago {
    @Override
    public void pagar(double monto) {
        System.out.println("Pago realizado en efectivo: Bs. " + String.format("%.2f", monto));
    }
}
