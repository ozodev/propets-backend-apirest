package com.propets.apirest.main.models.dto.response;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.propets.apirest.main.models.dto.ResponseDto;
import com.propets.apirest.main.models.entity.atributos.Race;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RaceDto extends ResponseDto {

    private Long id;

    @NotBlank
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private String title;

    private boolean enabled;

    public RaceDto() {
    }

    public RaceDto(Race race) {
        if(Objects.nonNull(race.getId()))
            setId(race.getId());
        setName(race.getName());
        setTitle(race.getTitle());
        setEnabled(race.isEnabled());
    }
}
