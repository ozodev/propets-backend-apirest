package com.propets.apirest.main.models.enums;

public enum CitationType {
    CONSULTA(0,"Consulta"),
    CONTROL(1,"Control"),
    ASEO(2,"Aseo"),
    PELUQUERIA(3,"Peluqueria"),
    VACUNACION(4,"Vacunacion"),
    ESTERILIZAR(5,"Esterilizacion"),
    ;
    CitationType(int type, String title){
        this.type=type;
        this.title =title;
    }
    private final int type;
    private final String title;
    public String getTitle() {return title;}
    public int getType() {return type;}

    public static CitationType value(int type){
        CitationType citationType = CitationType.CONSULTA;
        for(CitationType citation: CitationType.values()) if(citation.getType()==type) citationType=citation;
        return citationType;
    }
}
