package co.com.unicauca.pago.modelo;

import java.time.LocalDateTime;

public class Transaccion {
    private String descripcion;
    private double valor;
    private LocalDateTime fecha;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Transaccion(String descripcion, double valor){
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = LocalDateTime.now();
    }
}
