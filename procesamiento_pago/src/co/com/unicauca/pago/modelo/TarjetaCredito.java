package co.com.unicauca.pago.modelo;

public class TarjetaCredito extends MetodoPago{
    private String numeroTarjeta;
    private double limiteCredito;

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public TarjetaCredito(String numeroTarjeta, double limiteCredito){
        this.numeroTarjeta = numeroTarjeta;
        this.limiteCredito = limiteCredito;
    }

    @Override
    public boolean procesarPago(double valor){
        //Lógica para procesar el pago utilizando la estrategia seleccionada.
        //Logica para verificar el limite de la tarjeta de credito
        if(valor > limiteCredito){
            //Realizar pago y actualizar el limite de la tarjeta de credito
            System.out.println("No se puede realizar el pago, excede el límite de crédito");
            return false;
        }
        formaDePago.procesarPago(valor);
        return true;
    }
}
