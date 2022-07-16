package com.propets.apirest.main.models.objects;

import com.propets.apirest.main.models.Enums.FranjaType;
import com.propets.apirest.main.models.Enums.StatusType;
import org.springframework.lang.NonNull;

import javax.validation.constraints.*;

public class CitaData {
    @NotEmpty @NotNull
    private String centro;
    @NotEmpty @NotNull
    private String veterinario;
    @NotEmpty @NotNull @Email
    private String usuario;
    @NotEmpty @NotNull
    private String mascota;
    @NonNull @Positive
    private int franja;
    @NonNull @Positive
    private int dia;
    @NonNull @Positive
    private int mes;
    @NonNull @Positive
    private int year;
    @NonNull @NotEmpty
    private String status;

    public String getCentro() {return centro;}
    public void setCentro(String centroAtencion) {this.centro = centroAtencion;}

    public String getVeterinario() {return veterinario;}
    public void setVeterinario(String veterinario) {this.veterinario = veterinario;}

    public String getMascota() {return mascota;}
    public void setMascota(String mascota) {this.mascota = mascota;}

    public String getUsuario() {return usuario;}
    public void setUsuario(String usuario) {this.usuario = usuario;}

    public FranjaType getFranja() {return FranjaType.value(this.franja);}
    public void setFranja(String franja) {this.franja = FranjaType.valueOf(franja).getFranja();}

    public int getDia() {return dia;}
    public void setDia(int dia) {this.dia = dia;}

    public int getMes() {return mes;}
    public void setMes(int mes) {this.mes = mes;}

    public int getYear() {return year;}
    public void setYear(int year) {this.year = year;}

    public StatusType getStatus() {return StatusType.valueOf(status);}
    public void setStatus(String status) {this.status = status;}
}
