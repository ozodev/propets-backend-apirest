package com.propets.apirest.main.controllers;

import com.propets.apirest.main.models.entity.Usuario;
import com.propets.apirest.main.models.entity.Veterinario;
import com.propets.apirest.main.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import static com.propets.apirest.main.services.MessageService.errorMessage;


@RestController
@RequestMapping("/api")
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CentroAtencionService centroAtencionService;
    @Autowired
    private RoleService roleService;
    @GetMapping(value = "/veterinario")
    public @ResponseBody ResponseEntity<?> show(){
        List<Veterinario> veterinarios = veterinarioService.findAll();
        List<Usuario> usuarios = new ArrayList<>();
        for(Veterinario veterinario:veterinarios) usuarios.add(veterinario.getUsuario());
        return new ResponseEntity<>(usuarios,HttpStatus.OK);
    }
    @GetMapping(value = "/veterinario/{id}")
    public @ResponseBody ResponseEntity<?> showById(@PathVariable String id){
        Veterinario veterinario = veterinarioService.findById(id);
        return new ResponseEntity<>(veterinario.getUsuario(),HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "/veterinario")
    public @ResponseBody ResponseEntity<?> create(@Valid @ModelAttribute Veterinario veterinario, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        if(Objects.isNull(veterinario.getCentro().getId())) return MessageService.sendErrorMessage("El Centro no existe","Centro not Found","/api/veterinario",HttpStatus.BAD_REQUEST);
        Usuario user = veterinario.getUsuario();
        if(Objects.isNull(user)) return MessageService.sendExistMessage("El usuario no existe","usuario",veterinario.getUsuario().getEmail(),"/api/usuario"+veterinario.getUsuario().getEmail()+"/persona",HttpStatus.BAD_REQUEST);
        if(user.getRoles().contains(roleService.findRole(2L))) return MessageService.sendExistMessage("El usuario ya es veterinario","usuario",veterinario.getUsuario().getEmail(),"/api/usuario"+veterinario.getUsuario().getEmail()+"/persona",HttpStatus.BAD_REQUEST);
        user.addRole(roleService.findRole(2L));
        veterinario.setId(UUID.randomUUID().toString());
        user.setVeterinario(veterinario);
        return new ResponseEntity<>(usuarioService.save(user),HttpStatus.ACCEPTED);
    }
    @Secured("ROLE_VETERINARIO")
    @PutMapping(value = "/veterinario")
    public @ResponseBody ResponseEntity<?> update(@RequestHeader("authorization") String authorization,@Valid @ModelAttribute Veterinario data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Usuario user = usuarioService.findByToken(authorization);
        Veterinario veterinario = user.getVeterinario();
        veterinario.update(data);
        return new ResponseEntity<>(usuarioService.save(user),HttpStatus.ACCEPTED);
    }
    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/veterinario/{email}")
    public @ResponseBody ResponseEntity<?> updateByAdmin(@Valid @ModelAttribute Veterinario data,@PathVariable String email,BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Usuario user = usuarioService.findByEmail(email);
        Veterinario veterinario = user.getVeterinario();
        veterinario.update(data);
        veterinario.setCentro(data.getCentro());
        return new ResponseEntity<>(usuarioService.save(user),HttpStatus.ACCEPTED);
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/veterinario/{email}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable String email, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Usuario user = usuarioService.findByEmail(email);
        user.setVeterinario(null);
        user.removeRole(roleService.findRole(2L));
        return new ResponseEntity<>(usuarioService.save(user),HttpStatus.ACCEPTED);
    }
}