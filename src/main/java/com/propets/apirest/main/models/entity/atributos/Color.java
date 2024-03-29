package com.propets.apirest.main.models.entity.atributos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.propets.apirest.main.models.dto.response.ColorDto;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "colors")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String title;

    @Column()
    private boolean enabled;

    public Color() {
    }

    public Color(ColorDto colorDto) {
        setId(colorDto.getId());
        setName(colorDto.getName());
        setTitle(colorDto.getTitle());
        setEnabled(colorDto.isEnabled());
    }
}