package co.com.unicauca.pago.modelo;

public class EstrategiaPayPal implements IFormaDePago {
    @Override
    public boolean procesarPago(double valor) {
        System.out.println("Pagando con PayPal $" + valor);
        return true;
    }

}
