package com.com350.practica3.venta;

public class PagoFactory {

    public static Pago crearPago(int tipo) {
        if (tipo == 1) {
            return new PagoEfectivo();
        }

        if (tipo == 2) {
            return new PagoTarjeta();
        }

        if (tipo == 3) {
            return new PagoTransferencia();
        }

        return null;
    }
}
