package com.propets.apirest.main.models.entity;

public class Autenticacion {
    private String email,password;
    private Boolean status;

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public Boolean getStatus() {return status;}
    public void setStatus(Boolean status) {this.status = status;}
}
