package com.propets.apirest.main.controllers;

import com.propets.apirest.main.models.entity.Persona;
import com.propets.apirest.main.models.entity.Usuario;
import com.propets.apirest.main.services.MessageService;
import com.propets.apirest.main.services.RoleService;
import com.propets.apirest.main.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Objects;
import java.util.UUID;

import static com.propets.apirest.main.services.MessageService.errorMessage;
@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    @Secured("ROLE_USER")
    @GetMapping(value = "/usuario")
    public @ResponseBody ResponseEntity show(@RequestHeader("authorization") String authorization){
        Usuario user = usuarioService.findByToken(authorization);
        return new ResponseEntity(user,HttpStatus.ACCEPTED);
    }
    @PostMapping(value = "/usuario")
    public @ResponseBody ResponseEntity<?> create(@Valid @ModelAttribute Usuario data,BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        if(usuarioService.exist(data.getEmail())) return userExist(data.getEmail());
        data.setPassword(passwordEncoder.encode(data.getPassword()));
        data.addRole(roleService.findRole(3L));
        data.setEnabled(true);
        usuarioService.save(data);
        return MessageService.sendCreateEntity("Usuario Registrado con Exito","user",data.getEmail(),"/api/usuario",HttpStatus.CREATED);
    }
    @Secured("ROLE_USER")
    @PutMapping(value ="/usuario/persona")
    public @ResponseBody ResponseEntity<?> update(@RequestHeader("authorization") String authorization, @Valid @ModelAttribute Persona data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Usuario user = usuarioService.findByToken(authorization);
        if(Objects.isNull(user.getPersona())){
           if(Objects.isNull(data.getId())) data.setId(UUID.randomUUID().toString());
           user.setPersona(data);
           data.setUsuario(user);
        }else user.getPersona().update(data);
        return new ResponseEntity<>(usuarioService.save(user),HttpStatus.CREATED);
    }
    @Secured("ROLE_ADMIN")
    @PutMapping(value ="/usuario/{email}/persona")
    public @ResponseBody ResponseEntity<?> updateByAdmin(@PathVariable String email,@Valid @ModelAttribute Persona data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Usuario user = usuarioService.findByEmail(email);
        if(Objects.isNull(user.getPersona())){
            if(Objects.isNull(data.getId())) data.setId(UUID.randomUUID().toString());
            user.setPersona(data);
            data.setUsuario(user);
        }else user.getPersona().update(data);
        return new ResponseEntity<>(usuarioService.save(user),HttpStatus.CREATED);
    }
    @Secured("ROLE_USER")
    @DeleteMapping(value = "/usuario")
    public @ResponseBody ResponseEntity<?> delete(@RequestHeader("authorization") String authorization){
        Usuario user = usuarioService.findByToken(authorization);
        user.setRoles(null);
        usuarioService.delete(user);
        return MessageService.sendDeleteMessage("Usuario Eliminado","usuario",user.getEmail(),"/api/usuario",HttpStatus.ACCEPTED);
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/usuario/{email}")
    public @ResponseBody ResponseEntity<?> deleteByAdmin(@PathVariable String email){
        Usuario user = usuarioService.findByEmail(email);
        user.setRoles(null);
        usuarioService.delete(user);
        return MessageService.sendDeleteMessage("Usuario Eliminado","usuario",user.getEmail(),"/api/usuario",HttpStatus.ACCEPTED);
    }
    private ResponseEntity userExist(String email){return MessageService.sendExistMessage("El usuario ya existe","usuario",email,"/api/usuario",HttpStatus.BAD_REQUEST);}
    private ResponseEntity userNotExist(String email){return MessageService.sendExistMessage("El usuario no existe","usuario",email,"/api/usuario"+email+"/persona",HttpStatus.BAD_REQUEST);}
}