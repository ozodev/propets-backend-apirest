package com.propets.apirest.main.models.entity;

import com.propets.apirest.main.models.dto.PetDto;
import com.propets.apirest.main.models.entity.atributos.Race;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @Column(length = 36)
    @Getter
    @Setter
    private String id;
    @Column(length = 20)
    @Getter
    @Setter
    private String name;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "email", foreignKey = @ForeignKey(name = "FK_USER_PET"))
    @Getter
    @Setter
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_PET_RACE"), nullable = false)
    @Getter
    @Setter
    private Race race;

    @Column
    @Getter
    @Setter
    private int peso;

    public Pet() {
    }

    public Pet(PetDto data) {
        setName(data.getName());
    }

    public void update(Pet data) {
        setName(data.getName());
    }

    public void update(PetDto data) {
        setName(data.getName());
    }
}