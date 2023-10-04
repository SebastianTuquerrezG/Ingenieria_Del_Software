package co.com.unicauca.mercado.controller;

import co.com.unicauca.mercado.model.Accion;
import co.com.unicauca.mercado.model.AccionMercado;
import co.com.unicauca.mercado.model.AccionObserver;

import java.util.List;

public class AccionController {
    private final AccionMercado accionMercado = new AccionMercado();
    private final AccionObserver accionObserver = new AccionObserver();

    public void addAccion(String name, double precioActual, double umbralInferior, double umbralSuperior){
        Accion accion = new Accion(name, precioActual, precioActual, umbralInferior, umbralSuperior);
        accion.addObserver(accionObserver);
        accionMercado.addAccion(accion);
    }

    public void removeAccion(String name){
        Accion accionRemove = null;
        for (Accion accion : accionMercado.getAcciones()) {
            if(accion.getNombre().equals(name)){
                accionRemove = accion;
                break;
            }
        }

        if(accionRemove != null){
            accionRemove.deleteObserver(accionObserver);
            accionMercado.removeAccion(accionRemove);
        } else {
            System.out.println("No se encontro la accion");
        }
    }

    public List<Accion> getAcciones(){
        return accionMercado.getAcciones();
    }
}
