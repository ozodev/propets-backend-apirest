package com.propets.apirest.main.models.enums;
public enum ColorType {
    NEGRO(0,"Negro"),
    CAFE(1,"Cafe"),
    BLANCO(2,"Blanco"),
    DORADO(3,"Dorado"),
    CHOCOLATE(4,"Chocolate"),
    ;
    ColorType(int color,String title){
        this.color=color;
        this.title=title;
    }
    private final int color;
    private final String title;
    public int getColor() {return color;}
    public String getTitle() {return title;}
    public static ColorType valueOf(int color){
        ColorType colorType = NEGRO;
        for (ColorType type:ColorType.values()) if(type.getColor()==color) colorType = type;
        return colorType;
    }
}
