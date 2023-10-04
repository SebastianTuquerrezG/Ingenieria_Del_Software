package co.com.unicauca.pago;

import co.com.unicauca.pago.view.ControlVistaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ControlVistaPrincipal().setVisible(true);
            }
        });
    }
}