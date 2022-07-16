package com.propets.apirest.main.models.objects;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VeterinarioAuth extends VeterinarioDelete{

    @NotEmpty @NotNull
    private String centro;

    @NotNull @NotEmpty
    private String cedula;

    @NotNull @NotEmpty
    private String especialidad;

    public String getCentro() {return centro;}
    public void setCentro(String centro) {this.centro = centro;}
    public String getCedula() {return cedula;}
    public void setCedula(String cedula) {this.cedula = cedula;}
    public String getEspecialidad() {return especialidad;}
    public void setEspecialidad(String especialidad) {this.especialidad = especialidad;}
}
