package com.propets.apirest.main.models.Enums;

import org.hibernate.loader.plan.spi.CollectionAttributeFetch;

public enum ColorType {
    NEGRO(0),
    CAFE(1),
    BLANCO(2),
    DORADO(3),
    CHOCOLATE(4),
    ;
    private ColorType(int color){this.color=color;}
    private int color;
    public int getColor() {return color;}

    public static ColorType valueOf(int color){
        ColorType colorType = NEGRO;
        for (ColorType type:ColorType.values()) if(type.getColor()==color) colorType = type;
        return colorType;
    }
}
