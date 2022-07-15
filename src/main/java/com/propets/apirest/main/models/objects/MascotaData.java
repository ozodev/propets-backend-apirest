package com.propets.apirest.main.models.objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class MascotaData extends UsuarioAuth{
    @NotNull @NotEmpty
    private String nombre;
    @NotNull @NotEmpty
    private String raza;
    @NotNull @NotEmpty
    private String size;
    @NotNull @Positive
    private double peso;
    @NotNull @NotEmpty
    private String color;

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getRaza() {return raza;}
    public void setRaza(String raza) {this.raza = raza;}
    public String getSize() {return size;}
    public void setSize(String size) {this.size = size;}
    public double getPeso() {return peso;}
    public void setPeso(double peso) {this.peso = peso;}
    public String getColor() {return color;}
    public void setColor(String color) {this.color = color;}
}
