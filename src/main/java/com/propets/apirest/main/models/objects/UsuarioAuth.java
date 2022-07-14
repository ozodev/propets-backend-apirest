package com.propets.apirest.main.models.objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UsuarioAuth {
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    @Size(min = 8,max = 16)
    private String password;

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}
}
