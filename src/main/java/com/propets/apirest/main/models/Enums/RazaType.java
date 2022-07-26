package com.propets.apirest.main.models.Enums;

public enum RazaType {
    CRIOLLO(0,"Criollo"),
    PITBUL(1,"Pitbul"),
    TERRIER(2,"Terrier"),
    SAN_BERNARDO(3,"San Bernardo"),
    ROTTWEILER(4,"Rottweiler"),
    LABRADOR(5,"Labrador"),
    DOBERMANN(6,"Dobermann"),
    CHIHUAHAU(7,"Chihuahua"),
    BULLDOG(8,"Bulldog"),
    BOXER(9,"Boxer"),
    HUSKY(10,"Husky");
    private RazaType(int raza,String titulo){
        this.raza = raza;
        this.titulo=titulo;
    }
    private int raza;
    private String titulo;
    public int getRaza() {return raza;}
    public String getTitulo() {return titulo;}

    public static RazaType valueOf(int raza){
        RazaType razaType = RazaType.CRIOLLO;
        for (RazaType type:RazaType.values()) {
            if(type.getRaza()== raza) razaType=type;
        }
        return razaType;
    }
}
