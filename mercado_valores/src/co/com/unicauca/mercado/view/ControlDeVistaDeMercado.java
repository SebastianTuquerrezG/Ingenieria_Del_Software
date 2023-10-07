package co.com.unicauca.mercado.view;

import co.com.unicauca.mercado.controller.AccionController;
import co.com.unicauca.mercado.controller.UsuarioController;
import co.com.unicauca.mercado.model.Accion;
import co.com.unicauca.mercado.model.AccionObserver;
import co.com.unicauca.mercado.model.Usuario;
import co.com.unicauca.mercado.sms.SMSService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ControlDeVistaDeMercado extends JFrame {
    private final AccionController controller = new AccionController();
    private final UsuarioController usuarioController = new UsuarioController();
    private Usuario usuarioActual;

    private final JTextField nameField;
    private final JTextField initialPriceField;
    private final JTextField lowerThresholdField;
    private final JTextField upperThresholdField;
    private final JTextArea stockListArea;
    private final JTextField usuarioField;
    private final JComboBox<Accion> accionesComboBox;

    public ControlDeVistaDeMercado() {
        setTitle("Seguimiento del Mercado de Valores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7,2));

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

        // Campo para el nombre de usuario
        inputPanel.add(new JLabel("Nombre de Usuario:"));
        usuarioField = new JTextField();
        inputPanel.add(usuarioField);

        // Lista desplegable de acciones
        inputPanel.add(new JLabel("Seleccionar Acción:"));
        accionesComboBox = new JComboBox<>(controller.getAcciones().toArray(new Accion[0]));
        inputPanel.add(accionesComboBox);

        JButton addButton = getAddButton();

        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

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
        scrollPane.setPreferredSize(new Dimension(500, 300));
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private JButton getAddButton() {
        JButton addButton = new JButton("Agregar Acción");
        addButton.addActionListener(e -> {
            //validar que los campos no esten vacios
            if(nameField.getText().isEmpty() || initialPriceField.getText().isEmpty() ||
                    lowerThresholdField.getText().isEmpty() || upperThresholdField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                return;
            }
            String name = nameField.getText();
            String usuario = usuarioField.getText();
            double initialPrice = Double.parseDouble(initialPriceField.getText());
            double lowerThreshold = Double.parseDouble(lowerThresholdField.getText());
            double upperThreshold = Double.parseDouble(upperThresholdField.getText());

            usuarioActual = usuarioController.getUsuario(usuario);
            if (usuarioActual == null) {
                usuarioActual = new Usuario(usuario);
                usuarioController.addUsuario(usuarioActual);
            }

            Accion stock = (Accion) accionesComboBox.getSelectedItem();
            //if (stock != null) {
                usuarioController.seguirAccion(usuarioActual, stock);
                controller.addAccion(name, initialPrice, lowerThreshold, upperThreshold);
                updateStockList();
            //} else {
            //    JOptionPane.showMessageDialog(null, "Debes seleccionar una acción");
            //}
        });
        return addButton;
    }

    private void updateStockList() {
        stockListArea.setText("Acciones Disponibles: " + usuarioActual.getNombre() +  "\n");

        if (usuarioActual != null) {
            List<Accion> accionesSeguidas = usuarioActual.getAccionesSeguidas();
            if (!accionesSeguidas.isEmpty()) {
                for (Accion stock : accionesSeguidas) {
                    if (stock != null) {
                        stockListArea.append(stock.getNombre() + " - Precio Actual: $" + stock.getPrecioActual() + "\n");
                        if (stock.precioSuperaUmbralSuperior() || stock.precioSuperaUmbralInferior()) {
                            AccionObserver notifier = new AccionObserver();
                            notifier.update(stock, stock);
                            String phoneNumber = "1234567890";
                            String message = "La acción " + stock.getNombre() + " ha superado el umbral superior";

                            SMSService smsService = new SMSService();
                            smsService.sendSMS(phoneNumber, message);
                        }
                    }
                }
            } else {
                stockListArea.append("No hay acciones disponibles");
            }
        }

        accionesComboBox.removeAllItems();
        List<Accion> accionesDisp = controller.getAcciones();
        if (!accionesDisp.isEmpty()) {
            for (Accion stock : accionesDisp) {
                accionesComboBox.addItem(stock);
            }
        }
    }
}
