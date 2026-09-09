package com.com350.practica3.venta;

public class PagoTarjeta implements Pago {
    @Override
    public void pagar(double monto) {
        System.out.println("Pago realizado con tarjeta de crédito: Bs. " + String.format("%.2f", monto));
    }
}
