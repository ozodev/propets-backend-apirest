package com.propets.apirest.main.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.propets.apirest.main.models.Enums.ColorType;
import com.propets.apirest.main.models.Enums.RazaType;
import com.propets.apirest.main.models.Enums.SizeType;
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
    @NotNull
    private int raza;
    @Column( name = "mascota_tamaño",length = 100,nullable = false)
    @NotNull
    private double size;
    @Column(name = "mascota_peso",length = 2,nullable = false)
    @NotNull @Positive
    private double peso;
    @Column(name = "mascota_color",length = 10,nullable = false)
    @NotNull
    private int color;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="usuario_email",foreignKey = @ForeignKey(name = "fk_mascota_usuario_email"))
    private Usuario usuario;

    @OneToOne(mappedBy = "mascota")
    private Cita cita;

    public Mascota(){}

    public Mascota(MascotaData data,Usuario usuario){
        this.id= UUID.randomUUID().toString();
        this.usuario=usuario;
        this.update(data);
    }
    public void update(MascotaData data){
        this.nombre= data.getNombre();
        this.raza= RazaType.valueOf(data.getRaza().toUpperCase()).getRaza();
        this.peso = data.getPeso();
        this.color = ColorType.valueOf(data.getColor().toUpperCase()).getColor();
        this.size = SizeType.valueOf(data.getSize().toUpperCase()).getSize();
    }

    public Cita getCita() {return cita;}
    public void setCita(Cita cita) {this.cita = cita;}
    public Usuario getUsuario(){return this.usuario;}
    public void setUsuario(Usuario usuario){this.usuario=usuario;}
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public RazaType getRaza() {return RazaType.valueOf(this.raza);}
    public void setRaza(RazaType type) {this.raza = type.getRaza();}
    public SizeType getSize() {return SizeType.valueOf(this.size);}
    public void setSize(SizeType size) {this.size = size.getSize();}
    public double getPeso() {return peso;}
    public void setPeso(double peso) {this.peso = peso;}
    public ColorType getColor() {return ColorType.valueOf(this.color);}
    public void setColor(ColorType color) {this.color = color.getColor();}

    private static final long serialVersionUID = 1L;
}