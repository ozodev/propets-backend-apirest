package com.propets.apirest.main.models.dto.response;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.propets.apirest.main.models.dto.ResponseDto;
import com.propets.apirest.main.models.entity.atributos.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SizeDto extends ResponseDto {

    private Long id;
    @NotBlank
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private String title;
    private boolean enabled;

    public SizeDto() {
    }

    public SizeDto(Size size) {
        if (Objects.nonNull(size.getId()))
            setId(size.getId());
        setName(size.getName());
        setTitle(size.getTitle());
        setEnabled(size.isEnabled());
    }
}