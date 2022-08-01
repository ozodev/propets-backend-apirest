package com.propets.apirest.main.models.enums;

public enum StatusType {
    PENDIENTE("A"),
    CANCELADA("B"),
    COMPLETADA("C"),
    ACTIVA("D"),
    ;
    StatusType(String status){this.status=status;}
    private final String status;
    public String getStatus() {return status;}

    public static StatusType value(String status){
        StatusType defect = PENDIENTE;
        for(StatusType type:StatusType.values()) if(type.getStatus().equals(status)) defect=type;
        return defect;
    }
}
