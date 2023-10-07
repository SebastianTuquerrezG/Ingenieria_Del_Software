package co.com.unicauca.mercado.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private List<Accion> accionesSeguidas;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Accion> getAccionesSeguidas() {
        return accionesSeguidas;
    }

    public void setAccionesSeguidas(List<Accion> accionesSeguidas) {
        this.accionesSeguidas = accionesSeguidas;
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.accionesSeguidas = new ArrayList<>();
    }

    public void seguirAccion(Accion accion) {
        if (!this.accionesSeguidas.contains(accion)) {
            this.accionesSeguidas.add(accion);
        }
    }

    public void dejarDeSeguirAccion(Accion accion) {
        this.accionesSeguidas.remove(accion);
    }
}
