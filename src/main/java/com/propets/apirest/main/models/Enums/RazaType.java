package com.propets.apirest.main.models.Enums;

public enum RazaType {
    CRIOLLO(0),
    PITBUL(1),
    TERRIER(2),
    SAN_BERNARDO(3),
    ROTTWEILER(4),
    LABRADOR(5),
    DOBERMANN(6),
    CHIHUAHAU(7),
    BULLDOG(8),
    BOXER(9),
    HUSKY(10);
    private RazaType(int raza){
        this.raza = raza;
    }
    private int raza;
    public int getRaza() {return raza;}

    public static RazaType valueOf(int raza){
        RazaType razaType = RazaType.CRIOLLO;
        for (RazaType type:RazaType.values()) {
            if(type.getRaza()== raza) razaType=type;
        }
        return razaType;
    }
}
