package com.propets.apirest.main.services.messages;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.propets.apirest.main.models.dto.ResponseDto;

import java.util.List;

public interface MessageService {

    ResponseEntity<ResponseDto> entityNotFound(String id, String path);

    ResponseEntity<ResponseDto> entityNotFound(String path);

    ResponseEntity<ResponseDto> entityExist(String path);

    ResponseEntity<ResponseDto> invalidFields(BindingResult validationResult, String path);

    List<String> validErros(BindingResult validationResult);
    
    ResponseEntity<ResponseDto> unauthorized(String path);
}
