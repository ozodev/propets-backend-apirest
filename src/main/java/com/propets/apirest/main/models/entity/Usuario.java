package com.propets.apirest.main.models.entity;

import com.propets.apirest.main.models.objects.UsuarioData;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

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
    @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL)
    private Persona persona;
    public Usuario(){}
    public Usuario(UsuarioData data){
        this.email=data.getEmail();
        this.password = data.getPassword();
        this.persona = new Persona(data,this);
    }
    public void setPersona(Persona persona) {this.persona = persona;}
    public Persona getPersona() {return persona;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public void setPassword(String password) {this.password = password;}

    public boolean isAuth(String password){return this.password.equals(password);}

    private static final long serialVersionUID = 1L;
}