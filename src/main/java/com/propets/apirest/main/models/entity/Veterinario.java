package com.propets.apirest.main.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.propets.apirest.main.models.Enums.VeterinarioType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "veterinarios")
public class Veterinario implements Serializable {
    @Id
    @Column(name = "veterinario_uuid",length = 36,nullable = false)
    private String id;

    @Column(name = "veterinario_especialidad",length = 3)
    @NotEmpty @NotNull
    private String especialidad;

    @Column(name = "veterinario_cc",length = 11)
    @NotNull @NotEmpty
    private String cedula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centro_uuid",foreignKey = @ForeignKey(name = "fk_veterinario_centro_uuid"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private CentroAtencion centro;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="usuario_email",referencedColumnName = "usuario_email",foreignKey = @ForeignKey(name = "fk_veterinario_usuario_email"),unique = true)
    @NotNull
    private Usuario usuario;
    public void update(Veterinario data){
        setCedula(data.getCedula());
        setEspecialidad(data.getEspecialidad());
    }
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}
    public CentroAtencion getCentro() {return centro;}
    public void setCentro(CentroAtencion centro) {this.centro = centro;}
    public VeterinarioType getEspecialidad() {return VeterinarioType.value(especialidad);}
    public void setEspecialidad(VeterinarioType especialidad) {this.especialidad = especialidad.getType();}
    public String getCedula() {return cedula;}
    public void setCedula(String cedula) {this.cedula = cedula;}
    public String getDireccion(){return getCentro().getDireccion();}
    public String getCiudad(){return  getCentro().getCiudad();}

    private static final long serialVersionUID = 1L;
}
