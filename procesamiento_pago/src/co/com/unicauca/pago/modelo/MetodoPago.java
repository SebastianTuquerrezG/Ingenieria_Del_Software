package co.com.unicauca.pago.modelo;

public abstract class MetodoPago {
    protected IFormaDePago formaDePago;

    public void setFormaDePago(IFormaDePago formaDePago){
        this.formaDePago = formaDePago;
    }

    public abstract boolean procesarPago(double valor);
}
