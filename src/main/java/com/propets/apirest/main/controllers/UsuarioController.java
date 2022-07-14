package com.propets.apirest.main.controllers;

import com.propets.apirest.main.models.objects.Autenticacion;
import com.propets.apirest.main.models.entity.Usuario;
import com.propets.apirest.main.models.objects.UsuarioAuth;
import com.propets.apirest.main.models.objects.UsuarioData;
import com.propets.apirest.main.services.MessageService;
import com.propets.apirest.main.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.propets.apirest.main.services.MessageService.errorMessage;
import static com.propets.apirest.main.services.MessageService.sendMessageError;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @RequestMapping(value = "/usuario", method = RequestMethod.GET, consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity<?> show(@Valid @ModelAttribute Autenticacion auth, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Usuario user = usuarioService.findByEmail(auth.getEmail());
        if(Objects.isNull(user)) return MessageService.loginError();
        if(!user.isAuth(auth.getPassword())) return MessageService.loginError();
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/usuario",method = RequestMethod.POST,consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity<?> createUser(@Valid @ModelAttribute UsuarioData data,BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        if(!Objects.isNull(usuarioService.findByEmail(data.getEmail())))return sendMessageError("El Usuario "+data.getEmail()+" ya Existe",HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(usuarioService.save(new Usuario(data)),HttpStatus.CREATED);
    }
    @RequestMapping(value ="/usuario",method = RequestMethod.PUT, consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity<?> updateUser(@Valid @ModelAttribute UsuarioData data,BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        if(Objects.isNull(usuarioService.findByEmail(data.getEmail())))return sendMessageError("El Usuario "+data.getEmail()+" no Existe",HttpStatus.UNAUTHORIZED);
        Usuario user = usuarioService.findByEmail(data.getEmail());
        if(!user.isAuth(data.getPassword())) return MessageService.loginError();
        user.getPersona().update(data);
        return new ResponseEntity<>(usuarioService.save(user),HttpStatus.CREATED);
    }
    @RequestMapping(value = "/usuario",method = RequestMethod.DELETE,consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity<?> deleteUser(@Valid @ModelAttribute UsuarioAuth data,BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        if(Objects.isNull(usuarioService.findByEmail(data.getEmail())))return sendMessageError("El Usuario "+data.getEmail()+" no Existe",HttpStatus.UNAUTHORIZED);
        Usuario user = usuarioService.findByEmail(data.getEmail());
        if(!user.isAuth(data.getPassword())) return MessageService.loginError();
        usuarioService.delete(user);
        Map<String, String> result = new HashMap<>();
        result.put("message","Usuario "+data.getEmail()+" fue Eliminado");
        return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
    }
}
