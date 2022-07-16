package com.propets.apirest.main.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.propets.apirest.main.models.Enums.VeterinarioType;
import com.propets.apirest.main.models.objects.VeterinarioAuth;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "veterinarios")
public class Veterinario implements Serializable {
    @Id
    @NotEmpty
    @Column(name = "veterinario_uuid",length = 36,nullable = false)
    private String id;

    @Column(name = "veterinario_especialidad",length = 3)
    @NotEmpty @NotNull
    private String especialidad;

    @Column(name = "veterinario_cc",length = 11)
    @NotNull @NotEmpty
    private String cedula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "centro_uuid",foreignKey = @ForeignKey(name = "fk_veterinario_centro_uuid"))
    private CentroAtencion centroAtencion;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="usuario_email",referencedColumnName = "usuario_email",foreignKey = @ForeignKey(name = "fk_veterinario_usuario_email"),unique = true)
    private Usuario usuario;

    public Veterinario() {}

    public Veterinario(VeterinarioAuth data){
        this.id = UUID.randomUUID().toString();
        update(data);
    }
    public void update(VeterinarioAuth data){
        this.cedula = data.getCedula();
        this.especialidad = VeterinarioType.valueOf(data.getEspecialidad()).getType();
    }

    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}
    public CentroAtencion getCentroAtencion() {return centroAtencion;}
    public void setCentroAtencion(CentroAtencion centroAtencion) {this.centroAtencion = centroAtencion;}
    public VeterinarioType getEspecialidad() {return VeterinarioType.value(especialidad);}
    public void setEspecialidad(VeterinarioType especialidad) {this.especialidad = especialidad.getType();}
    public String getCedula() {return cedula;}
    public void setCedula(String cedula) {this.cedula = cedula;}

    public String getDireccion(){return getCentroAtencion().getDireccion();}
    public String getCiudad(){return  getCentroAtencion().getCiudad();}

    private static final long serialVersionUID = 1L;
}