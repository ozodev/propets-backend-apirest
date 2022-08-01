package com.propets.apirest.main.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.propets.apirest.main.models.entity.User;
import com.propets.apirest.main.models.entity.atributos.Role;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UserDto extends ResponseDto {
    @Email
    @NotNull
    @Getter
    @Setter
    private String email;
    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String name;
    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String lastname;
    @NotNull
    @NotEmpty
    @NotBlank
    @Getter
    @Setter
    private String phone;
    @NotNull
    @NotEmpty
    @NotBlank
    @Setter
    private String password;
    @Getter
    @Setter
    private Boolean enabled;
    @Getter
    @Setter
    private List<Role> roles;

    public UserDto() {
    }

    public UserDto(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.phone = user.getPhone();
        this.enabled = user.getEnabled();
        this.roles = user.getRoles();
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }

    private static final long serialVersionUID = 1L;
}