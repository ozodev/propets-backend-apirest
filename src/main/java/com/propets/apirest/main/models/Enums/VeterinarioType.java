package com.propets.apirest.main.models.Enums;

public enum VeterinarioType {
    GENERAL("A"),
    PELUQUERIA("B"),
    CIRUJANO("C"),
    TECNICO("D"),
    ;
    private VeterinarioType(String type){this.type=type;}
    private String type;
    public String getType() {return type;}

    public static VeterinarioType value(String inttype){
        VeterinarioType veterinarioType = GENERAL;
        for(VeterinarioType type:VeterinarioType.values()) if(type.getType().equals(inttype)) veterinarioType = type;
        return veterinarioType;
    }
}
