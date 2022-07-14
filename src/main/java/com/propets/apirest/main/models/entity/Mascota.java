package com.propets.apirest.main.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.propets.apirest.main.models.objects.MascotaData;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "mascotas")
public class Mascota implements Serializable {

    @Id
    @Column(name = "mascota_uuid",length = 36,nullable = false)
    private String id;
    @Column(name = "mascota_nombre",length = 20,nullable = false)
    @NotNull @NotEmpty
    private String nombre;
    @Column(name = "mascota_raza",length = 3,nullable = false)
    @NotNull @Positive
    private int raza;
    @Column( name = "mascota_tamaño",length = 100,nullable = false)
    @NotNull @Positive
    private double size;
    @Column(name = "mascota_peso",length = 2,nullable = false)
    @NotNull @Positive
    private double peso;
    @Column(name = "mascota_color",length = 10,nullable = false)
    @NotNull @Positive
    private int color;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="usuario_email",foreignKey = @ForeignKey(name = "fk_mascota_usuario_email"))
    private Usuario usuario;

    public Mascota(){}

    public Mascota(MascotaData data,Usuario usuario){
        this.id= UUID.randomUUID().toString();
        this.usuario=usuario;
        this.update(data);
    }
    public void update(MascotaData data){
        this.nombre= data.getNombre();
        this.raza= data.getRaza();
        this.peso = data.getPeso();
        this.color = data.getColor();
        this.size = data.getSize();
    }

    public Usuario getUsuario(){return this.usuario;}
    public void setUsuario(Usuario usuario){this.usuario=usuario;}
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public int getRaza() {return raza;}
    public void setRaza(int raza) {this.raza = raza;}
    public double getSize() {return size;}
    public void setSize(double size) {this.size = size;}
    public double getPeso() {return peso;}
    public void setPeso(double peso) {this.peso = peso;}
    public int getColor() {return color;}
    public void setColor(int color) {this.color = color;}

    private static final long serialVersionUID = 1L;
}