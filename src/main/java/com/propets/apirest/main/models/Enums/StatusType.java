package com.propets.apirest.main.models.Enums;

public enum StatusType {
    PENDIENTE("A"),
    CANCELADA("B"),
    COMPLETADA("C"),
    ACTIVA("D"),
    ;
    private StatusType(String status){this.status=status;}
    private String status;
    public String getStatus() {return status;}

    public static StatusType value(String status){
        StatusType defecto = PENDIENTE;
        for(StatusType type:StatusType.values()) if(type.getStatus().equals(status)) defecto=type;
        return defecto;
    }
}
