package co.com.unicauca.pago.modelo;

public class PayPal extends MetodoPago{
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PayPal(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private boolean autenticar() {
        // Lógica para autenticar el usuario en PayPal.
        // Retorna true si la autenticación es exitosa.
        return true;
    }

    @Override
    public boolean procesarPago(double valor) {
        // Lógica para procesar un pago mediante PayPal.
        if (autenticar()) {
            // Utiliza la información de la cuenta PayPal proporcionada.
            return formaDePago.procesarPago(valor);
        }
        return false;
    }
}
