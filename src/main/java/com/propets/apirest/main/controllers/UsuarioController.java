package com.propets.apirest.main.controllers;

import com.propets.apirest.main.models.entity.Autenticacion;
import com.propets.apirest.main.models.entity.Usuario;
import com.propets.apirest.main.models.entity.UsuarioData;
import com.propets.apirest.main.services.MessageService;
import com.propets.apirest.main.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @RequestMapping(value = "/usuario", method = RequestMethod.GET, consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity<?> show(@Valid @ModelAttribute Autenticacion auth, BindingResult validationResult){
        if(validationResult.hasErrors()){
            Map<String, Object> error = new HashMap<>();
            List<String> errorList = validationResult.getFieldErrors().stream().map(err -> "El Campo '"+ err.getField() + "' "+err.getDefaultMessage()).collect(Collectors.toList());
            error.put("errores",errorList);
            return new ResponseEntity<Map<String, Object>>(error,HttpStatus.BAD_REQUEST);
        }
        Usuario user = usuarioService.findByEmail(auth.getEmail());
        if(Objects.isNull(user)) return MessageService.loginError();
        if(!user.isAuth(auth.getPassword())) return MessageService.loginError();
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/usuario",method = RequestMethod.POST,consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity<?> createUser(@ModelAttribute UsuarioData data){

        return null;
    }
}
