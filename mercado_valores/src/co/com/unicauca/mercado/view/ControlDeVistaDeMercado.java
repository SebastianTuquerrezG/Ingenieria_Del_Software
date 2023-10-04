package co.com.unicauca.mercado.view;

import co.com.unicauca.mercado.controller.AccionController;
import co.com.unicauca.mercado.model.Accion;
import co.com.unicauca.mercado.model.AccionObserver;
import co.com.unicauca.mercado.sms.SMSService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlDeVistaDeMercado extends JFrame {
    private final AccionController controller = new AccionController();

    private final JTextField nameField;
    private final JTextField initialPriceField;
    private final JTextField lowerThresholdField;
    private final JTextField upperThresholdField;
    private final JTextArea stockListArea;

    public ControlDeVistaDeMercado() {
        setTitle("Seguimiento del Mercado de Valores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Nombre de la Acción:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Precio Inicial:"));
        initialPriceField = new JTextField();
        inputPanel.add(initialPriceField);

        inputPanel.add(new JLabel("Umbral Inferior:"));
        lowerThresholdField = new JTextField();
        inputPanel.add(lowerThresholdField);

        inputPanel.add(new JLabel("Umbral Superior:"));
        upperThresholdField = new JTextField();
        inputPanel.add(upperThresholdField);

        JButton addButton = new JButton("Agregar Acción");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //validar que los campos no esten vacios
                if(nameField.getText().isEmpty() || initialPriceField.getText().isEmpty() ||
                        lowerThresholdField.getText().isEmpty() || upperThresholdField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                    return;
                }
                String name = nameField.getText();
                double initialPrice = Double.parseDouble(initialPriceField.getText());
                double lowerThreshold = Double.parseDouble(lowerThresholdField.getText());
                double upperThreshold = Double.parseDouble(upperThresholdField.getText());

                controller.addAccion(name, initialPrice, lowerThreshold, upperThreshold);
                updateStockList();
            }
        });

        inputPanel.add(addButton);

        JButton removeButton = new JButton("Eliminar Acción");
        removeButton.addActionListener(e -> {
            String name = nameField.getText();
            controller.removeAccion(name);
            updateStockList();
        });

        inputPanel.add(removeButton);

        add(inputPanel, BorderLayout.NORTH);

        stockListArea = new JTextArea();
        stockListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(stockListArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void updateStockList() {
        stockListArea.setText("");
        AccionObserver notifier = new AccionObserver();
        for (Accion stock : controller.getAcciones()) {
            stockListArea.append(stock.getNombre() + " - Precio Actual: $" + stock.getPrecioActual() + "\n");
            if(stock.precioSuperaUmbralSuperior() || stock.precioSuperaUmbralInferior()){
                notifier.update(stock, stock);
                String phoneNumber = "1234567890";
                String message = "La accion " + stock.getNombre() + " ha superado el umbral superior";

                SMSService smsService = new SMSService();
                smsService.sendSMS(phoneNumber, message);
            }
        }
    }
}
