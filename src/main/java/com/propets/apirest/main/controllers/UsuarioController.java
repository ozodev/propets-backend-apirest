package com.propets.apirest.main.controllers;

import com.propets.apirest.main.models.entity.Veterinario;
import com.propets.apirest.main.models.objects.Authentication;
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
import java.util.Objects;

import static com.propets.apirest.main.services.MessageService.errorMessage;
import static com.propets.apirest.main.services.MessageService.sendMessageError;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping(value = "/usuario", consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity<?> show(@Valid @ModelAttribute Authentication auth, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Usuario user = usuarioService.findByEmail(auth.getEmail());
        if(Objects.isNull(user)) return MessageService.loginError();
        if(!user.isAuth(auth.getPassword())) return MessageService.loginError();
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/usuario",consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity<?> createUser(@Valid @ModelAttribute UsuarioData data,BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        if(existUsuario(data.getEmail()))return sendMessageError("El Usuario "+data.getEmail()+" ya Existe",HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(usuarioService.save(new Usuario(data)),HttpStatus.CREATED);
    }
    @PutMapping(value ="/usuario", consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity<?> updateUser(@Valid @ModelAttribute UsuarioData data,BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        if(!existUsuario(data.getEmail()))return sendMessageError("El Usuario "+data.getEmail()+" no Existe",HttpStatus.UNAUTHORIZED);
        Usuario user = usuarioService.findByEmail(data.getEmail());
        if(!user.isAuth(data.getPassword())) return MessageService.loginError();
        user.getPersona().update(data);
        return new ResponseEntity<>(usuarioService.save(user),HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/usuario",consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity<?> deleteUser(@Valid @ModelAttribute UsuarioAuth data,BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        if(!existUsuario(data.getEmail()))return sendMessageError("El Usuario "+data.getEmail()+" no Existe",HttpStatus.UNAUTHORIZED);
        Usuario user = usuarioService.findByEmail(data.getEmail());
        if(!user.isAuth(data.getPassword())) return MessageService.loginError();
        usuarioService.delete(user);
        return MessageService.sendMessage("Usuario "+data.getEmail()+" fue Eliminado",HttpStatus.ACCEPTED);
    }

    private Boolean existUsuario(String email){return !Objects.isNull(usuarioService.findByEmail(email));}
}
