package co.com.unicauca.mercado.model;

import java.util.Observable;
import java.util.Observer;

public class AccionObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Accion) {
            Accion accion = (Accion) o;
            if (accion.precioSuperaUmbralSuperior()){
                System.out.println("La accion " + accion.getNombre() + " ha superado el umbral superior");
            } else if (accion.precioSuperaUmbralInferior()){
                System.out.println("La accion " + accion.getNombre() + " ha superado el umbral inferior");
            }
        }
    }
}
