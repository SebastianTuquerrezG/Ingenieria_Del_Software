package co.com.unicauca.pago.view;

import co.com.unicauca.pago.modelo.*;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ControlVistaPrincipal extends JFrame {
    private JComboBox<String> metodoPagoComboBox;
    private JButton pagarButton;
    private JTextField montoTextField;

    public ControlVistaPrincipal(){
        setTitle("Sistema de pago");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        metodoPagoComboBox = new JComboBox<>(new String[] {"PayPal", "Tarjeta de Credito", "Transferencia Bancaria"});
        montoTextField = new JTextField("Ingrese el monto", 15);
        pagarButton = new JButton("Pagar");

        add(new JLabel("Seleccione el método de pago: "));
        add(metodoPagoComboBox);
        add(montoTextField);
        add(pagarButton);

        pagarButton.addActionListener(e -> {
            String metodoPagoSeleccionado = (String) metodoPagoComboBox.getSelectedItem();
            double monto = Double.parseDouble(montoTextField.getText());

            MetodoPago metodoPago;

            switch (Objects.requireNonNull(metodoPagoSeleccionado)) {
                case "Tarjeta de Credito":
                    metodoPago = new TarjetaCredito("123456", 1000);
                    metodoPago.setFormaDePago(new EstrategiaTarjetaCredito());
                    break;
                case "PayPal":
                    metodoPago = new PayPal("correo@gmail.com", "unicauca");
                    metodoPago.setFormaDePago(new EstrategiaPayPal());
                    break;
                case "Transferencia Bancaria":
                    metodoPago = new TrasferenciaBancaria("123456");
                    metodoPago.setFormaDePago(new EstrategiaTransferenciaBancaria());
                    break;
                default:
                    metodoPago = null;
                    break;
            }

            if (metodoPago != null) {
                metodoPago.procesarPago(monto);
            }
        });

    }

    // Métodos para la interfaz de usuario, como mostrar ventanas, recopilar entradas del usuario, etc.
}
