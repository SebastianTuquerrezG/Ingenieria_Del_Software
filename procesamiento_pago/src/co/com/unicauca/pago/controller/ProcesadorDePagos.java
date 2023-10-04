package co.com.unicauca.pago.controller;
import co.com.unicauca.pago.modelo.Cliente;
import co.com.unicauca.pago.modelo.IFormaDePago;

public class ProcesadorDePagos {
    private IFormaDePago formaDePago;
    private Cliente cliente;

    public ProcesadorDePagos(IFormaDePago formaDePago, Cliente cliente) {
        this.formaDePago = formaDePago;
        this.cliente = cliente;
    }

    public boolean procesarPago(double valor) {
        // Lógica para procesar el pago utilizando la estrategia seleccionada.,
        if (cliente != null && formaDePago != null) {
            if (formaDePago.procesarPago(valor)){
                System.out.println("Pagando con tarjeta de crédito " + cliente.getNombre());
                return true;
            }
        }
        return false;
    }
}
