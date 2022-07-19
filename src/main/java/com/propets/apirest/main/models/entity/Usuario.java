package com.propets.apirest.main.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @Email
    @NotEmpty
    @Column(name = "usuario_email",length = 60)
    private String email;
    @Column(name = "usuario_password",length = 60)
    @NotEmpty
    private String password;
    private Boolean enabled;
    @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Persona persona;
    @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL)
    private Veterinario veterinario;
    @OneToMany(mappedBy = "usuario")
    private List<Mascota> mascotas = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();
    public List<Mascota> getMascotas(){return this.mascotas;}
    public void setMascotas(List<Mascota> mascotas) {this.mascotas = mascotas;}
    public void addMascotas(Mascota mascota){
        this.mascotas.add(mascota);
        mascota.setUsuario(this);
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {return password;}
    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean getEnabled() {return enabled;}
    public void setEnabled(Boolean enabled) {this.enabled = enabled;}

    public List<Role> getRoles() {return roles;}
    public void setRoles(List<Role> roles) {this.roles = roles;}
    public void addRole(Role role){this.roles.add(role);}
    public void removeRole(Role role){this.roles.remove(role);}

    public Persona getPersona() {return persona;}
    public void setPersona(Persona persona) {this.persona = persona;}
    public Veterinario getVeterinario() {return veterinario;}
    public void setVeterinario(Veterinario veterinario) {this.veterinario = veterinario;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getNombre(){
        if(Objects.isNull(getPersona())) return null;
        return getPersona().getNombre();
    }
    public String getApellido(){
        if(Objects.isNull(getPersona())) return null;
        return getPersona().getApellido();
    }
    public String getTelefono(){
        if(Objects.isNull(getPersona())) return null;
        return getPersona().getTelefono();
    }
    private static final long serialVersionUID = 1L;
}