package com.propets.apirest.main.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageService {
    public static ResponseEntity<Map<String, Object>> sendErrorMessage(String message,String[] errors,String path, HttpStatus httpStatus){
        Map<String, Object> request = new HashMap<>();
        request.put("timestamp", new Timestamp(System.currentTimeMillis()));
        request.put("status",httpStatus.value());
        request.put("error",errors);
        request.put("message",message);
        request.put("path",path);
        return new ResponseEntity<Map<String, Object>>(request, httpStatus);
    }
    public static ResponseEntity<Map<String, Object>> sendErrorMessage(String message,String error,String path, HttpStatus httpStatus){
        Map<String, Object> request = new HashMap<>();
        request.put("timestamp", new Timestamp(System.currentTimeMillis()));
        request.put("status",httpStatus.value());
        request.put("error",error);
        request.put("message",message);
        request.put("path",path);
        return new ResponseEntity<Map<String, Object>>(request, httpStatus);
    }
    public static ResponseEntity<Map<String, Object>> sendDeleteMessage(String message,String entityName,String entityId,String path, HttpStatus httpStatus){
        Map<String, Object> request = new HashMap<>();
        request.put("timestamp", new Timestamp(System.currentTimeMillis()));
        request.put("status",httpStatus.value());
        request.put("message",message);
        request.put(entityName,entityId);
        request.put("path",path);
        return new ResponseEntity<Map<String, Object>>(request, httpStatus);
    }
    public static ResponseEntity<Map<String, Object>> sendExistMessage(String message,String entityName,String entityId,String path, HttpStatus httpStatus){
        Map<String, Object> request = new HashMap<>();
        request.put("timestamp", new Timestamp(System.currentTimeMillis()));
        request.put("status",httpStatus.value());
        request.put("message",message);
        request.put(entityName,entityId);
        request.put("path",path);
        return new ResponseEntity<Map<String, Object>>(request, httpStatus);
    }
    public static ResponseEntity<Map<String, Object>> sendCreateEntity(String message,String entityName,String entityId,String path, HttpStatus httpStatus){
        Map<String, Object> request = new HashMap<>();
        request.put("timestamp", new Timestamp(System.currentTimeMillis()));
        request.put("status",httpStatus.value());
        request.put("message",message);
        request.put(entityName,entityId);
        request.put("path",path);
        return new ResponseEntity<Map<String, Object>>(request, httpStatus);
    }
    public static ResponseEntity<Map<String, Object>> errorMessage(BindingResult validationResult){
        Map<String, Object> error = new HashMap<>();
        List<String> errorList = validationResult.getFieldErrors().stream().map(err -> "El Campo '"+ err.getField() + "' "+err.getDefaultMessage()).collect(Collectors.toList());
        error.put("errores",errorList);
        return new ResponseEntity<Map<String, Object>>(error,HttpStatus.BAD_REQUEST);
    }
}
