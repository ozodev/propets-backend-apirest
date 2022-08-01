package com.propets.apirest.main.models.dto;

import lombok.Getter;
import lombok.Setter;

import com.propets.apirest.main.models.entity.Pet;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class PetDto implements Serializable {
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    @NotEmpty
    @NotEmpty
    private String name;
    @Getter
    @Setter
    private UserDto user;

    public PetDto() {
    }

    public PetDto(Pet pet) {
        this.update(pet);
    }

    public void update(Pet pet) {
        setId(pet.getId());
        setName(pet.getName());
        setUser(new UserDto(pet.getUser()));
    }

    private static final long serialVersionUID = 1L;
}
