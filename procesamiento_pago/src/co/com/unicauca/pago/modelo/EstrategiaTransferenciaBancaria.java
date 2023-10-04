package co.com.unicauca.pago.modelo;

public class EstrategiaTransferenciaBancaria implements IFormaDePago{
    @Override
    public boolean procesarPago(double valor) {
        System.out.println("Pago realizado con transferencia bancaria por valor de: $" + valor);
        return true;
    }
}
