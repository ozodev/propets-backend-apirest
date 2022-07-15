package com.propets.apirest.main.models.entity;

import com.propets.apirest.main.models.objects.CentroAtencionData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "centro_atencion")
public class CentroAtencion implements Serializable {

    @Id
    @Column(name = "centro_uuid",length = 36,nullable = false)
    @NotEmpty @NotNull
    private String id;
    @Column(name = "centro_ciudad",length = 30,nullable = false)
    @NotEmpty @NotNull
    private String ciudad;
    @Column(name = "centro_direccion",length = 45,nullable = false)
    @NotNull @NotEmpty
    private String direccion;

    public CentroAtencion(){}

    public CentroAtencion(CentroAtencionData data){
        this.id = UUID.randomUUID().toString();
        this.update(data);
    }

    public void update(CentroAtencionData data){
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
