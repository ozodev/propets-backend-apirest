package com.propets.apirest.main.models.objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class MascotaData extends UsuarioAuth{
    @NotNull @NotEmpty
    private String nombre;
    @NotNull @Positive
    private int raza;
    @NotNull @Positive
    private double size;
    @NotNull @Positive
    private double peso;
    @NotNull @Positive
    private int color;

    private String id;
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public int getRaza() {return raza;}
    public void setRaza(int raza) {this.raza = raza;}
    public double getSize() {return size;}
    public void setSize(double size) {this.size = size;}
    public double getPeso() {return peso;}
    public void setPeso(double peso) {this.peso = peso;}
    public int getColor() {return color;}
    public void setColor(int color) {this.color = color;}

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
}
