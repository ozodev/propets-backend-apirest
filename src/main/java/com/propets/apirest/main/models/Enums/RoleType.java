package com.propets.apirest.main.models.Enums;

public enum RoleType {
    ADMIN(2),
    USUARIO(0),
    VETERINARIO(1);

    private int rol;
    RoleType(int rol){
        this.rol=rol;
    }
    public int getRol() {return rol;}

    public static RoleType valueOf(int rol){
        switch (rol){
            case 1: return RoleType.VETERINARIO;
            case 2: return RoleType.ADMIN;
            default: return RoleType.USUARIO;
        }
    }
}
