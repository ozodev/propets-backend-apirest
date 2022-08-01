package com.propets.apirest.main.models.enums;

public enum VeterinaryType {
    GENERAL("A"),
    PELUQUERIA("B"),
    CIRUJANO("C"),
    TECNICO("D"),
    ;
    VeterinaryType(String type){this.type=type;}
    private final String type;
    public String getType() {return type;}

    public static VeterinaryType value(String value){
        VeterinaryType veterinaryType = GENERAL;
        for(VeterinaryType type: VeterinaryType.values()) if(type.getType().equals(value)) veterinaryType = type;
        return veterinaryType;
    }
}
