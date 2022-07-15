package com.propets.apirest.main.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageService {
    public static ResponseEntity<Map<String, Object>> loginError(){return sendMessageError("Usuario o Contraseña Incorrectos",HttpStatus.BAD_REQUEST);}


    public static ResponseEntity<Map<String, Object>> sendMessageError(String message, HttpStatus httpStatus){
        Map<String, Object> error = new HashMap<>();
        error.put("error",message);
        return new ResponseEntity<Map<String, Object>>(error, httpStatus);
    }
    public static ResponseEntity<Map<String, Object>> sendMessage(String message, HttpStatus httpStatus){
        Map<String, Object> error = new HashMap<>();
        error.put("message",message);
        return new ResponseEntity<Map<String, Object>>(error, httpStatus);
    }
    public static ResponseEntity<Map<String, Object>> errorMessage(BindingResult validationResult){
        Map<String, Object> error = new HashMap<>();
        List<String> errorList = validationResult.getFieldErrors().stream().map(err -> "El Campo '"+ err.getField() + "' "+err.getDefaultMessage()).collect(Collectors.toList());
        error.put("errores",errorList);
        return new ResponseEntity<Map<String, Object>>(error,HttpStatus.BAD_REQUEST);
    }
}
