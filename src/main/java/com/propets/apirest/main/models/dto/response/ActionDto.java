package com.propets.apirest.main.models.dto.response;

import com.propets.apirest.main.models.dto.ResponseDto;
import com.propets.apirest.main.models.enums.ResponseType;

import lombok.Getter;

@Getter
public class ActionDto extends ResponseDto {
    
    private String message;

    public ActionDto(ResponseType response) {
        this.message = response.getMessage();
    }

}
