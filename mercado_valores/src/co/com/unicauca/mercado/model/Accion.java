package co.com.unicauca.mercado.model;

import java.util.Observable;

public class Accion extends Observable {
    private String nombre;
    private double precioActual;
    private double precioAnterior;
    private double umbralInferior;
    private double umbralSuperior;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(double precioActual) {
        this.precioActual = precioActual;
    }

    public double getPrecioAnterior() {
        return precioAnterior;
    }

    public void setPrecioAnterior(double precioAnterior) {
        this.precioAnterior = precioAnterior;
    }

    public double getUmbralInferior() {
        return umbralInferior;
    }

    public void setUmbralInferior(double umbralInferior) {
        this.umbralInferior = umbralInferior;
    }

    public double getUmbralSuperior() {
        return umbralSuperior;
    }

    public void setUmbralSuperior(double umbralSuperior) {
        this.umbralSuperior = umbralSuperior;
    }

    public Accion(String nombre, double precioActual, double precioAnterior, double umbralInferior, double umbralSuperior) {
        this.nombre = nombre;
        this.precioActual = precioActual;
        this.precioAnterior = precioAnterior;
        this.umbralInferior = umbralInferior;
        this.umbralSuperior = umbralSuperior;
    }

    public void actualizarPrecio(double precio){
        this.precioAnterior = this.precioActual;
        this.precioActual = precio;
    }

    public boolean precioSuperaUmbralSuperior(){
        return this.precioActual > this.umbralSuperior;
    }

    public boolean precioSuperaUmbralInferior(){
        return this.precioActual < this.umbralInferior;
    }

    public void notificar(){
        this.setChanged();
        this.notifyObservers(this);
    }
}
