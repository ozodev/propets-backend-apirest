package com.propets.apirest.main.models.entity.atributos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.propets.apirest.main.models.dto.response.SizeDto;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "sizes")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String title;

    @Column()
    private boolean enabled;

    public Size() {
    }

    public Size(SizeDto sizeDto) {
        setId(sizeDto.getId());
        setName(sizeDto.getName());
        setTitle(sizeDto.getTitle());
        setEnabled(sizeDto.isEnabled());
    }

}
