package com.propets.apirest.main.models.Enums;

public enum Role_Type {
    ADMIN(2),
    USUARIO(0),
    VETERINARIO(1);

    private int rol;
    Role_Type(int rol){
        this.rol=rol;
    }
    public int getRol() {return rol;}

    public static Role_Type valueOf(int rol){
        switch (rol){
            case 1: return Role_Type.VETERINARIO;
            case 2: return Role_Type.ADMIN;
            default: return Role_Type.USUARIO;
        }
    }
}
