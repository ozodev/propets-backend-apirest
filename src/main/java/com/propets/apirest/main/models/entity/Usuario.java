package com.propets.apirest.main.models.entity;

import com.propets.apirest.main.models.Enums.RoleType;
import com.propets.apirest.main.models.objects.UsuarioData;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    @Id
    @Email
    @NotEmpty
    @Column(name = "usuario_email",length = 60)
    private String email;
    @Column(name = "usuario_password",length = 16)
    @NotEmpty
    @Size(min = 8,max = 16)
    private String password;
    @Column(name = "usuario_rol",length = 1)
    @NotNull
    private int rol;
    @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL)
    private Persona persona;
    @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL)
    private Veterinario veterinario;
    @OneToMany(mappedBy = "usuario")
    private List<Mascota> mascotas = new ArrayList<>();
    public Usuario(){}
    public Usuario(UsuarioData data){
        this.email=data.getEmail();
        this.rol = RoleType.USUARIO.getRol();
        this.password = data.getPassword();
        this.persona = new Persona(data,this);
    }
    public boolean isAuth(String password){return this.password.equals(password);}
    public List<Mascota> getMascotas(){return this.mascotas;}
    public void addMascotas(Mascota mascota){
        this.mascotas.add(mascota);
        mascota.setUsuario(this);
    }
    public void setPersona(Persona persona) {this.persona = persona;}
    public Persona getPersona() {return persona;}
    public Veterinario getVeterinario() {return veterinario;}
    public void setVeterinario(Veterinario veterinario) {this.veterinario = veterinario;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public RoleType getRol() {return RoleType.valueOf(this.rol);}
    public void setRol(RoleType rol) {this.rol = rol.getRol();}

    public Boolean isAdmin(){return RoleType.valueOf(this.rol).equals(RoleType.ADMIN);}
    public Boolean isVeterinario(){return RoleType.valueOf(this.rol).equals(RoleType.VETERINARIO);}

    private static final long serialVersionUID = 1L;
}