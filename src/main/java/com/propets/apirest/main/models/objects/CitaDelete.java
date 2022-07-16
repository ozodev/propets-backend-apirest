package com.propets.apirest.main.models.objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CitaDelete {
    @NotNull @NotEmpty
    private String mascota;
    public String getMascota() {return mascota;}
    public void setMascota(String mascota) {this.mascota = mascota;}
}
