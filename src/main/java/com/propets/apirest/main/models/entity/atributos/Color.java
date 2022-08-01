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
@Table(name = "colors")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(length = 30, nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(length = 30, nullable = false)
    @Getter
    @Setter
    private String title;

    @Column()
    @Getter
    @Setter
    private boolean enabled;

    public Color() {
    }

    public Color(ColorDto raceDto) {
        setId(raceDto.getId());
        setName(raceDto.getName());
        setTitle(raceDto.getTitle());
        setEnabled(raceDto.isEnabled());
    }
}
