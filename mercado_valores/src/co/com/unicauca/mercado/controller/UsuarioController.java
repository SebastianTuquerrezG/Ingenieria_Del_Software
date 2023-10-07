package co.com.unicauca.mercado.controller;

import co.com.unicauca.mercado.model.Accion;
import co.com.unicauca.mercado.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private List<Usuario> usuarios = new ArrayList<>();

    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void removeUsuario(String nombre) {
        usuarios.removeIf(usuario -> usuario.getNombre().equals(nombre));
    }

    public Usuario getUsuario(String nombre) {
        return usuarios.stream()
                .filter(usuario -> usuario.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }

    public void seguirAccion(Usuario usuario, Accion accion) {
        usuario.seguirAccion(accion);
    }

    public void dejarDeSeguirAccion(Usuario usuario, Accion accion) {
        usuario.dejarDeSeguirAccion(accion);
    }
}
