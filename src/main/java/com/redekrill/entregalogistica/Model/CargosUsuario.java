package com.redekrill.entregalogistica.Model;

public enum CargosUsuario {
    ADMIN("admin"),
    USUARIO("usuario");

    private String cargo;

    CargosUsuario(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }
}
