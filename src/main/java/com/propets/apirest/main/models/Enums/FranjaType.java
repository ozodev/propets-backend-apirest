package com.propets.apirest.main.models.Enums;

public enum FranjaType {
    OCHO_NUEVE(1),
    NUEVE_DIES(2),
    DIES_ONCE(3),
    ONCE_DOCE(4),
    UNA_DOS(5),
    DOS_TRES(6),
    TRES_CUATRO(7),
    CUATRO_CINCO(8),
    ;

    private FranjaType(int franja){this.franja=franja;}
    private int franja;
    public int getFranja() {return franja;}

    public static FranjaType value(int franja){
        FranjaType franjaType = OCHO_NUEVE;
        for(FranjaType type: FranjaType.values()) if(type.getFranja()==franja) franjaType=type;
        return franjaType;
    }
}
