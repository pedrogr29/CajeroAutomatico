package com.itson.cajeroautomatico.modelo;

public class Retiro {

    private double monto;
    private double comision;
    private double saldoFinal;

    public Retiro(double monto, double comision) {
        this.monto = monto;
        this.comision = comision;
    }

    public double getMonto() {
        return monto;
    }

    public double getComision() {
        return comision;
    }

    public double calcularTotal() {
        return monto + comision;
    }

    public double calcularSaldoFinal(double saldoActual) {
        saldoFinal = saldoActual - calcularTotal();
        return saldoFinal;
    }
}
