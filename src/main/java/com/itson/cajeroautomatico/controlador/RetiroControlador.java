package com.itson.cajeroautomatico.controlador;

import com.itson.cajeroautomatico.modelo.CuentaBancaria;
import com.itson.cajeroautomatico.modelo.Retiro;
import com.itson.cajeroautomatico.vista.CajeroVista;

public class RetiroControlador {

    private CuentaBancaria cuenta;
    private CajeroVista vista;
    private Retiro retiroActual;
    private double comisionFija;

    public RetiroControlador(CuentaBancaria cuenta,
            CajeroVista vista, double comisionFija) {
        this.cuenta = cuenta;
        this.vista = vista;
        this.comisionFija = comisionFija;
    }

    public boolean validarMonto(double monto) {
        return monto > 0 && monto % 200 == 0;
    }

    public void seleccionarMonto(double monto) {

        if (!validarMonto(monto)) {
            vista.mostrarError(
                    "El monto debe ser múltiplo de $200.");
            return;
        }

        retiroActual = new Retiro(monto, comisionFija);

        double saldoActual = cuenta.consultarSaldo();
        double saldoFinal =
                retiroActual.calcularSaldoFinal(saldoActual);

        vista.mostrarDatosRetiro(
                cuenta.getNombreCliente(),
                saldoActual,
                retiroActual.getMonto(),
                retiroActual.getComision(),
                saldoFinal
        );
    }

    public void procesarRetiro() {

        if (retiroActual == null) {
            vista.mostrarError(
                    "Primero seleccione una cantidad.");
            return;
        }

        double total = retiroActual.calcularTotal();

        if (!cuenta.tieneSaldoSuficiente(total)) {
            vista.mostrarError(
                    "Saldo insuficiente para realizar el retiro.");
            return;
        }

        boolean exitoso = cuenta.descontarSaldo(total);

        if (exitoso) {
            generarRecibo();
            retiroActual = null;
        } else {
            vista.mostrarError(
                    "No se pudo completar el retiro.");
        }
    }

    public void generarRecibo() {

        if (retiroActual == null) {
            return;
        }

        vista.mostrarRecibo(
                cuenta.getNumeroCuenta(),
                cuenta.getNombreCliente(),
                retiroActual.getMonto(),
                retiroActual.getComision()
        );
    }
}
