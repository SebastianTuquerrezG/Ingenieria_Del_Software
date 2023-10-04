package co.com.unicauca.pago.modelo;

public class EstrategiaTarjetaCredito implements IFormaDePago{
    @Override
    public boolean procesarPago(double valor) {
        System.out.println("Pago realizado con tarjeta de crédito por valor de: $" + valor);
        return true;
    }
}
