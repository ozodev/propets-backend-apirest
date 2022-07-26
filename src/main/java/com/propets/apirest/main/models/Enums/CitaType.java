package com.propets.apirest.main.models.Enums;

public enum CitaType {
    CONSULTA(0,"Consulta"),
    CONTROL(1,"Control"),
    ASEO(2,"Aseo"),
    PELUQUERIA(3,"Peluqueria"),
    VACUNACION(4,"Vacunacion"),
    ESTERILIZAR(5,"Esterilizacion"),
    ;
    private CitaType(int type,String titulo){
        this.type=type;
        this.titulo=titulo;
    }
    private int type;
    private String titulo;
    public String getTitulo() {return titulo;}
    public int getType() {return type;}

    public static CitaType value(int type){
        CitaType citaType = CitaType.CONSULTA;
        for(CitaType cita:CitaType.values()) if(cita.getType()==type) citaType=cita;
        return citaType;
    }
}
