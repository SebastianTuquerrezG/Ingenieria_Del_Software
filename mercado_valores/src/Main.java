import co.com.unicauca.mercado.view.ControlDeVistaDeMercado;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ControlDeVistaDeMercado controlDeVistaDeMercado = new ControlDeVistaDeMercado();
                controlDeVistaDeMercado.setVisible(true);
            }
        });
    }
}