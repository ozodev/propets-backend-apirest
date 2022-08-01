package com.propets.apirest.main.models.entity;

import com.propets.apirest.main.models.dto.UserDto;
import com.propets.apirest.main.models.entity.atributos.Role;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(length = 60, nullable = false)
    @Getter
    @Setter
    private String email;
    @Column(length = 60, nullable = false)
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private Boolean enabled;
    @Column(length = 35, nullable = false)
    @Getter
    @Setter
    private String name;
    @Column(length = 35, nullable = false)
    @Getter
    @Setter
    private String lastname;
    @Column(length = 10, nullable = false)
    @Getter
    @Setter
    private String phone;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Getter
    @Setter
    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    public User(UserDto data) {
        setEmail(data.getEmail());
        setName(data.getName());
        setLastname(data.getLastname());
        setPhone(data.getPhone());
    }

    public void update(User data) {
        setName(data.getName());
        setLastname(data.getLastname());
        setPhone(data.getPhone());
    }

    public void update(UserDto data) {
        setName(data.getName());
        setLastname(data.getLastname());
        setPhone(data.getPhone());
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }
}