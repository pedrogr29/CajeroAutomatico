package com.itson.cajeroautomatico.principal;

import com.itson.cajeroautomatico.modelo.CuentaBancaria;
import com.itson.cajeroautomatico.vista.CajeroVista;
import com.itson.cajeroautomatico.controlador.RetiroControlador;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Datos de prueba del cliente
            CuentaBancaria cuenta = new CuentaBancaria(
                    "1234567890123456",
                    "Juan Perez",
                    5000.00
            );

            // Crear la interfaz gráfica
            CajeroVista vista = new CajeroVista();

            // Crear el controlador con una comisión de $20
            RetiroControlador controlador =
                    new RetiroControlador(cuenta, vista, 20.00);

            // Conectar la vista con el controlador
            vista.setControlador(controlador);

            // Mostrar el cajero automático
            vista.setVisible(true);
        });
    }
}
