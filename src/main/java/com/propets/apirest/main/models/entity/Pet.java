package com.propets.apirest.main.models.entity;

import com.propets.apirest.main.models.entity.atributos.Color;
import com.propets.apirest.main.models.entity.atributos.Race;
import com.propets.apirest.main.models.entity.atributos.Size;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "pets")
public class Pet {

    @Id
    @Column(length = 36)
    private String id;

    @Column(length = 20)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "email", foreignKey = @ForeignKey(name = "FK_USER_PET"))
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_PET_RACE"), nullable = false)
    private Race race;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_PET_COLOR"), nullable = false)
    private Color color;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_PET_SIZE"), nullable = false)
    private Size size;

    @Column
    private int peso;

    private boolean enabled;
}