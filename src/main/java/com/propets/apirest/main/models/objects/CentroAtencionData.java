package com.propets.apirest.main.models.objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CentroAtencionData extends UsuarioAuth {
    @NotEmpty @NotNull
    private String ciudad;
    @NotNull @NotEmpty
    private String direccion;

    public String getCiudad() {return ciudad;}
    public void setCiudad(String ciudad) {this.ciudad = ciudad;}
    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
}
