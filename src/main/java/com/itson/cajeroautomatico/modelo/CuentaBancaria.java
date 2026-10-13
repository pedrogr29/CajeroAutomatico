package com.itson.cajeroautomatico.modelo;

public class CuentaBancaria {

    private String numeroCuenta;
    private String nombreCliente;
    private double saldo;

    public CuentaBancaria(String numeroCuenta,
                          String nombreCliente,
                          double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.saldo = saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public double consultarSaldo() {
        return saldo;
    }

    public boolean tieneSaldoSuficiente(double total) {
        return total > 0 && saldo >= total;
    }

    public boolean descontarSaldo(double cantidad) {
        if (tieneSaldoSuficiente(cantidad)) {
            saldo -= cantidad;
            return true;
        }
        return false;
    }
}

