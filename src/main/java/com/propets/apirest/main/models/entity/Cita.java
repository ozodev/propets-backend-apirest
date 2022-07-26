package com.propets.apirest.main.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.propets.apirest.main.models.Enums.CitaType;
import com.propets.apirest.main.models.Enums.FranjaType;
import com.propets.apirest.main.models.Enums.StatusType;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Table(name = "citas")
public class Cita implements Serializable {
    @Id
    @Column(name = "cita_uuid",length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "centro_uuid",foreignKey = @ForeignKey(name = "fk_cita_centro_uuid"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NonNull
    private CentroAtencion centro;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "veterinario_uuid",foreignKey = @ForeignKey(name = "fk_cita_veterinario_uuid"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NonNull
    private Veterinario veterinario;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="usuario_email",referencedColumnName = "usuario_email",foreignKey = @ForeignKey(name = "fk_cita_usuario_email"))
    private Usuario usuario;
    @NonNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "mascota_uuid",foreignKey = @ForeignKey(name = "fk_cita_mascota_uuid"))
    private Mascota mascota;

    @NonNull @Positive
    @Column(name = "cita_franja",length = 1)
    private int franja;
    @NonNull @Positive
    @Column(name = "cita_dia",length = 2)
    private int dia;
    @NonNull @Positive
    @Column(name = "cita_mes",length = 2)
    private int mes;
    @NonNull @Positive
    @Column(name = "cita_year",length = 4)
    private int year;
    @NonNull @NotEmpty
    @Column(name = "cita_status",length = 1)
    private String status;

    @NonNull
    @Column(name = "cita_type")
    private int type;
    public void update(Cita data){
        setCentro(data.getCentro());
        setVeterinario(data.getVeterinario());
        setMes(data.getMes());
        setDia(data.getDia());
        setYear(data.getYear());
        setFranja(data.getFranja());
        setStatus(data.getStatus());
    }
    public CentroAtencion getCentro() {return centro;}
    public void setCentro(CentroAtencion centro) {this.centro = centro;}

    public Veterinario getVeterinario() {return veterinario;}
    public void setVeterinario(Veterinario veterinario) {this.veterinario = veterinario;}

    public Mascota getMascota() {return mascota;}
    public void setMascota(Mascota mascota) {this.mascota = mascota;}

    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public FranjaType getFranja() {return FranjaType.value(this.franja);}
    public void setFranja(FranjaType franja) {this.franja = franja.getFranja();}

    public int getDia() {return dia;}
    public void setDia(int dia) {this.dia = dia;}

    public int getMes() {return mes;}
    public void setMes(int mes) {this.mes = mes;}

    public int getYear() {return year;}
    public void setYear(int year) {this.year = year;}

    public StatusType getStatus() {return StatusType.value(this.status);}
    public void setStatus(StatusType status) {this.status = status.getStatus();}
    public CitaType getType() {return CitaType.value(this.type);}
    public void setType(CitaType type) {this.type = type.getType();}
    public String getParner(){return this.usuario.getEmail();}
    public String getPaciente(){return this.getMascota().getId();}
    public String getLugar(){return getCentro().getId();}
    public String getDoctor(){return this.getVeterinario().getId();}

    private static final long serialVersionUID = 1L;
}
