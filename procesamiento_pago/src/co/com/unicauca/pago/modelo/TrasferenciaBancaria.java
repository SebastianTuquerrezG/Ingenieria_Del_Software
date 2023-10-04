package co.com.unicauca.pago.modelo;

public class TrasferenciaBancaria extends MetodoPago {
    private String cuentaBancaria;

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public TrasferenciaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    @Override
    public boolean procesarPago(double valor) {
        // Lógica para procesar un pago mediante transferencia bancaria.
        // Utiliza la información de la cuenta bancaria proporcionada.
        // Retorna true si la transferencia se realiza con éxito.
        formaDePago.procesarPago(valor);
        return true;
    }
}
