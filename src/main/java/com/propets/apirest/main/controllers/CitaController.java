package com.propets.apirest.main.controllers;

import com.propets.apirest.main.models.Enums.StatusType;
import com.propets.apirest.main.models.entity.*;
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
public class CitaController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CentroAtencionService centroAtencionService;
    @Autowired
    private MascotaService mascotaService;
    @Autowired
    private VeterinarioService veterinarioService;
    @Autowired
    private CitaService citaService;
    @Autowired RoleService roleService;

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/cita")
    public @ResponseBody ResponseEntity<?> show(){
        return new ResponseEntity<>(citaService.findAll(), HttpStatus.OK);
    }
    @Secured("ROLE_VETERINARIO")
    @GetMapping(value = "/cita/veterinario")
    public @ResponseBody ResponseEntity<?> showByVeterinario(@RequestHeader("authorization") String authorization){
        Usuario user = usuarioService.findByToken(authorization);
        Veterinario veterinario = user.getVeterinario();
        return new ResponseEntity<>(citaService.findByVeterinario(veterinario),HttpStatus.ACCEPTED);
    }
    @Secured("ROLE_USER")
    @PostMapping(value = "/cita")
    public @ResponseBody ResponseEntity<?> create(@RequestHeader("authorization") String authorization,@Valid @ModelAttribute Cita data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Cita cita = citaService.findByDataAndStatus(data, StatusType.PENDIENTE);
        if(!Objects.isNull(cita)) return MessageService.sendErrorMessage("Cita a esa hora ya fue agendada","Cita Existente","/api/cita",HttpStatus.BAD_REQUEST);
        Usuario user = usuarioService.findByToken(authorization);
        data.setUsuario(user);
        data.setId(UUID.randomUUID().toString());
        return new ResponseEntity<>(citaService.save(data),HttpStatus.ACCEPTED);
    }
    @Secured("ROLE_ADMIN")
    @PostMapping(value = "/cita/{mascota}")
    public @ResponseBody ResponseEntity<?> createByMascota(@PathVariable String mascota,@Valid @ModelAttribute Cita data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Cita cita = citaService.findByDataAndStatus(data, StatusType.PENDIENTE);
        if(!Objects.isNull(cita)) return MessageService.sendErrorMessage("Cita a esa hora ya fue agendada","Cita Existente","/api/cita",HttpStatus.BAD_REQUEST);
        Usuario user = mascotaService.findById(mascota).getUsuario();
        data.setMascota(mascotaService.findById(mascota));
        data.setUsuario(user);
        data.setId(UUID.randomUUID().toString());
        return new ResponseEntity<>(citaService.save(data),HttpStatus.ACCEPTED);
    }
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @PutMapping(value = "/cita/{id}")
    public @ResponseBody ResponseEntity<?> update(@PathVariable String id,@RequestHeader("authorization") String authorization,@Valid @ModelAttribute Cita data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Usuario user = usuarioService.findByToken(authorization);
        Cita cita = citaService.findById(id);
        if(!cita.getUsuario().getEmail().equals(user.getEmail()) || !user.getRoles().contains(roleService.findRole(1L))) return MessageService.sendErrorMessage("Sin autorizacion","Not Authorized","/api/cita/"+id,HttpStatus.BAD_REQUEST);
        cita.update(data);
        return new ResponseEntity<>(citaService.save(cita),HttpStatus.ACCEPTED);
    }
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @DeleteMapping(value = "/cita/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable String id,@RequestHeader("authorization") String authorization, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Usuario user = usuarioService.findByToken(authorization);
        Cita cita = citaService.findById(id);
        if(!cita.getUsuario().getEmail().equals(user.getEmail()) || !user.getRoles().contains(roleService.findRole(1L))) return MessageService.sendErrorMessage("No Eres dueño de esta cita","Cita not Found","/api/cita/"+id,HttpStatus.BAD_REQUEST);
        citaService.delete(cita);
        return MessageService.sendDeleteMessage("Cita Eliminada","cita",id,"/api/cita/"+id,HttpStatus.ACCEPTED);
    }
}
