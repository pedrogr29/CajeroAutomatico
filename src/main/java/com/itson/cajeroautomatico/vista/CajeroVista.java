package com.itson.cajeroautomatico.vista;

import com.itson.cajeroautomatico.controlador.RetiroControlador;
import java.awt.*;
import javax.swing.*;

public class CajeroVista extends JFrame {

    private RetiroControlador controlador;
    private double montoSeleccionado;
    private JPanel panelMontos;
    private JPanel panelDetalles;
    private JButton botonRetirar;

    public CajeroVista() {
        setTitle("Cajero Automático - Retirar Efectivo");
        setSize(850, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        JLabel titulo = new JLabel(
                "CAJERO AUTOMÁTICO - RETIRAR EFECTIVO",
                SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));
        add(titulo, BorderLayout.NORTH);

        panelMontos = new JPanel(new GridLayout(4, 2, 10, 10));
        panelMontos.setBorder(BorderFactory.createTitledBorder(
                "Seleccione una cantidad"));

        panelDetalles = new JPanel(new BorderLayout());
        panelDetalles.setBorder(BorderFactory.createTitledBorder(
                "Información del retiro"));

        JPanel contenido = new JPanel(new GridLayout(1, 2, 15, 0));
        contenido.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        contenido.add(panelMontos);
        contenido.add(panelDetalles);
        add(contenido, BorderLayout.CENTER);

        botonRetirar = new JButton("RETIRAR EFECTIVO");
        botonRetirar.setFont(new Font("Arial", Font.BOLD, 15));
        botonRetirar.setEnabled(false);
        botonRetirar.addActionListener(e -> {
            if (controlador != null) {
                controlador.procesarRetiro();
            }
        });

        add(botonRetirar, BorderLayout.SOUTH);

        mostrarMontos();
    }

    public void setControlador(RetiroControlador controlador) {
        this.controlador = controlador;
    }

    public void mostrarMontos() {
        panelMontos.removeAll();

        for (int monto = 200; monto <= 1600; monto += 200) {
            final int cantidad = monto;
            JButton boton = new JButton("$" + cantidad);

            boton.addActionListener(e -> {
                if (controlador != null) {
                    controlador.seleccionarMonto(cantidad);
                }
            });

            panelMontos.add(boton);
        }

        panelMontos.revalidate();
        panelMontos.repaint();
    }

    public void mostrarDatosRetiro(String nombre, double saldo,
            double monto, double comision, double saldoFinal) {

        montoSeleccionado = monto;
        panelDetalles.removeAll();

        JTextArea detalles = new JTextArea();
        detalles.setEditable(false);
        detalles.setFont(new Font("Arial", Font.PLAIN, 16));
        detalles.setText(
                "Cliente: " + nombre +
                "\n\nSaldo actual: $" + String.format("%.2f", saldo) +
                "\n\nCantidad a retirar: $" + String.format("%.2f", monto) +
                "\n\nComisión: $" + String.format("%.2f", comision) +
                "\n\nSaldo final: $" + String.format("%.2f", saldoFinal)
        );

        panelDetalles.add(detalles, BorderLayout.CENTER);
        panelDetalles.revalidate();
        panelDetalles.repaint();
        botonRetirar.setEnabled(true);
    }

    public void mostrarRecibo(String numeroCuenta, String nombre,
            double monto, double comision) {

        String ultimosDigitos = numeroCuenta.substring(
                Math.max(0, numeroCuenta.length() - 4));

        panelDetalles.removeAll();

        JTextArea recibo = new JTextArea();
        recibo.setEditable(false);
        recibo.setFont(new Font("Arial", Font.PLAIN, 16));
        recibo.setText(
                "       RECIBO DE RETIRO\n\n" +
                "Cuenta terminación: " + ultimosDigitos +
                "\n\nCliente: " + nombre +
                "\n\nCantidad retirada: $" + String.format("%.2f", monto) +
                "\n\nComisión: $" + String.format("%.2f", comision) +
                "\n\nRETIRO REALIZADO CON ÉXITO"
        );

        panelDetalles.add(recibo, BorderLayout.CENTER);
        panelDetalles.revalidate();
        panelDetalles.repaint();
        botonRetirar.setEnabled(false);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error en el retiro",
                JOptionPane.ERROR_MESSAGE);
    }
}
