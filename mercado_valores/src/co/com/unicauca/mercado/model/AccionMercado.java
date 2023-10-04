package co.com.unicauca.mercado.model;

import java.util.ArrayList;
import java.util.List;

public class AccionMercado {
    private List<Accion> acciones = new ArrayList<>();

    public AccionMercado() {
    }

    public void addAccion(Accion accion) {
        acciones.add(accion);
    }

    public void removeAccion(Accion accion) {
        acciones.remove(accion);
    }

    public List<Accion> getAcciones() {
        return acciones;
    }
}
