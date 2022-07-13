package com.propets.apirest.main.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class MessageService {
    public static ResponseEntity<Map<String, Object>> loginError(){
        Map<String, Object> error = new HashMap<>();
        error.put("error","Usuario o Contraseña Incorrectos");
        return new ResponseEntity<Map<String, Object>>(error, HttpStatus.BAD_REQUEST);
    }
}
