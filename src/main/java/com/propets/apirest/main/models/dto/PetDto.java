package com.propets.apirest.main.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.propets.apirest.main.models.entity.Pet;

@Getter
@Setter
public class PetDto extends ResponseDto {
    
    private String id;
    @NotNull
    @NotBlank
    @NotEmpty
    private String name;
    private UserDto user;
    @NotNull
    @Positive
    private int peso;
    @NotNull
    @NotBlank
    @NotEmpty
    private String color;
    @NotNull
    @NotBlank
    @NotEmpty
    private String race;
    @NotNull
    @NotBlank
    @NotEmpty
    private String size;
    private boolean enabled;

    public PetDto() {
    }

    @PrePersist
    private void preCreate(){
        if(Objects.nonNull(getId()))
            return;
        setId(UUID.randomUUID().toString());
        setEnabled(true);
    }

    public PetDto(Pet pet) {
        this.update(pet);
    }

    public void update(Pet pet) {
        setId(pet.getId());
        setName(pet.getName());
        setPeso(pet.getPeso());
        setColor(pet.getColor().getName());
        setSize(pet.getSize().getName());
        setRace(pet.getRace().getName());
        setUser(new UserDto(pet.getUser()));
        setEnabled(pet.isEnabled());
    }

    private static final long serialVersionUID = 1L;
}
