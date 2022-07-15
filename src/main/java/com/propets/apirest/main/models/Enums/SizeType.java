package com.propets.apirest.main.models.Enums;

public enum SizeType {
    SMALL(0),
    MEDIUM(1),
    BIG(2),
    GIANT(3),
    ;
    private SizeType(int size){this.size=size;}
    private int size;
    public int getSize() {return size;}

    public static SizeType valueOf(double size){
        SizeType sizeType = SMALL;
        for (SizeType type: SizeType.values()) if(type.getSize()==size) sizeType=type;
        return sizeType;
    }
}
