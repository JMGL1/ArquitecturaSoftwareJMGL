package com.com350.practica3.venta;

public class PagoTransferencia implements Pago {
    @Override
    public void pagar(double monto) {
        System.out.println("Pago realizado por transferencia bancaria: Bs. " + String.format("%.2f", monto));
    }
}
