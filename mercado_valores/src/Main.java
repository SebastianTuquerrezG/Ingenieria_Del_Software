import co.com.unicauca.mercado.view.ControlDeVistaDeMercado;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControlDeVistaDeMercado controlDeVistaDeMercado = new ControlDeVistaDeMercado();
            controlDeVistaDeMercado.setVisible(true);
        });
    }
}