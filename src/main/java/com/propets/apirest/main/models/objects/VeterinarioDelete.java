package com.propets.apirest.main.models.objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VeterinarioDelete extends UsuarioAuth{
    @NotEmpty
    @NotNull
    private String usuario;
    public String getUsuario() {return usuario;}
    public void setUsuario(String usuario) {this.usuario = usuario;}
}
