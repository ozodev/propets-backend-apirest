package com.propets.apirest.main.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "centro_atencion")
public class CentroAtencion implements Serializable {

    @Id
    @Column(name = "centro_uuid",length = 36)
    private String id;
    @Column(name = "centro_ciudad",length = 30,nullable = false)
    @NotEmpty @NotNull
    private String ciudad;
    @Column(name = "centro_direccion",length = 45,nullable = false)
    @NotNull @NotEmpty
    private String direccion;
    public void update(CentroAtencion data){
        this.ciudad = data.getCiudad();
        this.direccion = data.getDireccion();
    }
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getCiudad() {return ciudad;}
    public void setCiudad(String ciudad) {this.ciudad = ciudad;}
    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}

    private static final long serialVersionUID = 1L;
}
