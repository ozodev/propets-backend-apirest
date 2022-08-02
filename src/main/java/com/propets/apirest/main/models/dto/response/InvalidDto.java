package com.propets.apirest.main.models.dto.response;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.propets.apirest.main.models.dto.ResponseDto;
import com.propets.apirest.main.models.enums.ResponseType;

import lombok.Getter;

@Getter
public class InvalidDto extends ResponseDto {

    private List<String> errors;
    private String message;
    private String timestamp;
    private String path;

    public InvalidDto(ResponseType message, List<String> errors, String path) {
        this.message = message.getMessage();
        this.errors = errors;
        this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
        this.path = path;
    }

    public InvalidDto(ResponseType message, String path) {
        this.message = message.getMessage();
        this.errors = new ArrayList<>();
        this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
        this.path = path;
    }

}
