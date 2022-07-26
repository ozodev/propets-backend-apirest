package com.propets.apirest.main.models.Enums;

public enum FranjaType {
    OCHO_NUEVE(1,"8:00 am - 9:00 am"),
    NUEVE_DIES(2,"9:00 am - 10:00 am"),
    DIES_ONCE(3,"10:00 am - 11:00 am"),
    ONCE_DOCE(4,"11:00 am - 12:00 pm"),
    UNA_DOS(5,"1:00 pm - 2:00 pm"),
    DOS_TRES(6,"2:00 pm - 3:00 pm"),
    TRES_CUATRO(7,"3:00 pm - 4:00 pm"),
    CUATRO_CINCO(8,"4:00 pm - 5:00 pm"),
    ;

    private FranjaType(int franja,String titulo){
        this.franja=franja;
        this.titulo=titulo;
    }
    private int franja;
    private String titulo;
    public int getFranja() {return franja;}
    public String getTitulo() {return titulo;}

    public static FranjaType value(int franja){
        FranjaType franjaType = OCHO_NUEVE;
        for(FranjaType type: FranjaType.values()) if(type.getFranja()==franja) franjaType=type;
        return franjaType;
    }
}
