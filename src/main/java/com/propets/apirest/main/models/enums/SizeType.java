package com.propets.apirest.main.models.enums;

public enum SizeType {
    SMALL(0,"Pequeño"),
    MEDIUM(1,"Mediano"),
    BIG(2,"Grande"),
    GIANT(3,"Gigante"),
    ;
    SizeType(int size,String title){
        this.size=size;
        this.title=title;
    }
    private final int size;
    private final String title;
    public int getSize() {return size;}
    public String getTitle() {return title;}
    public static SizeType valueOf(double size){
        SizeType sizeType = SMALL;
        for (SizeType type: SizeType.values()) if(type.getSize()==size) sizeType=type;
        return sizeType;
    }
}
