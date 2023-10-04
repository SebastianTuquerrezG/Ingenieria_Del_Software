package co.com.unicauca.pago.modelo;

public class Cliente {
    private String nombre;
    private String email;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cliente(String nombre, String email){
        this.nombre = nombre;
        this.email = email;
    }
}
