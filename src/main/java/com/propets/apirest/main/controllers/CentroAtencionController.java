package com.propets.apirest.main.controllers;

import com.propets.apirest.main.models.entity.CentroAtencion;
import com.propets.apirest.main.services.CentroAtencionService;
import com.propets.apirest.main.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.propets.apirest.main.services.MessageService.*;

@RestController
@RequestMapping("/api")
public class CentroAtencionController {
    @Autowired
    private CentroAtencionService centroAtencionService;
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping(value = "/centro_atencion")
    public @ResponseBody List<CentroAtencion> show(){
        return centroAtencionService.findAll();
    }
    @GetMapping(value = "/centro_atencion/{id}")
    public @ResponseBody ResponseEntity<?> show(@PathVariable String id){
        CentroAtencion centroAtencion = centroAtencionService.findById(id);
        if(!centroAtencionService.exists(id)) return centroNotFound(id);
        return new ResponseEntity<>(centroAtencion, HttpStatus.OK);
    }
    @Secured("ROLE_ADMIN")
    @PostMapping(value = "/centro_atencion")
    public @ResponseBody ResponseEntity<?> create(@Valid @ModelAttribute CentroAtencion centro, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        return new ResponseEntity<>(centroAtencionService.save(centro), HttpStatus.CREATED);
    }
    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/centro_atencion/{id}")
    public @ResponseBody ResponseEntity<?> update(@PathVariable String id,@Valid @ModelAttribute CentroAtencion data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        if(!centroAtencionService.exists(id)) return centroNotFound(id);
        data.setId(id);
        return new ResponseEntity<>(centroAtencionService.save(data), HttpStatus.ACCEPTED);
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/centro_atencion/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable String id){
        if(!centroAtencionService.exists(id)) return centroNotFound(id);
        centroAtencionService.delete(id);
        return new ResponseEntity<>(sendDeleteMessage("Centro de atencion Eliminado","centroatencion",id,"/api/centro_atencion/"+id,HttpStatus.ACCEPTED), HttpStatus.ACCEPTED);
    }
    private ResponseEntity centroNotFound(String id){
        return sendErrorMessage("El Centro de atencion no Existe","Entity not Found","/api/centro_atencion/"+id,HttpStatus.BAD_GATEWAY);
    }
}
