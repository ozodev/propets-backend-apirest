package com.propets.apirest.main.services.messages;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.propets.apirest.main.models.dto.ResponseDto;
import com.propets.apirest.main.models.dto.response.InvalidDto;
import com.propets.apirest.main.models.enums.ResponseType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImplement implements MessageService {

    @Override
    public List<String> validErros(BindingResult validationResult) {
        return validationResult.getFieldErrors().stream()
                .map(err -> "El Campo '" + err.getField() + "' " + err.getDefaultMessage())
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<ResponseDto> entityNotFound(String path) {
        InvalidDto response = new InvalidDto(ResponseType.ENTITY_NOT_FOUND, path);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ResponseDto> entityExist(String path) {
        InvalidDto response = new InvalidDto(ResponseType.ENTITY_EXIST, path);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ResponseDto> invalidFields(BindingResult validationResult, String path) {
        List<String> errors = validErros(validationResult);
        InvalidDto response = new InvalidDto(ResponseType.INVALID_FIELDS, errors, path);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ResponseDto> entityNotFound(String id, String path) {
        List<String> errors = new ArrayList<>();
        errors.add("Id: " + id + " Not Found");
        return new ResponseEntity<>(new InvalidDto(ResponseType.ENTITY_NOT_FOUND, errors, path),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ResponseDto> unauthorized(String path) {
        InvalidDto response = new InvalidDto(ResponseType.UNAUTHORIZED, path);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
