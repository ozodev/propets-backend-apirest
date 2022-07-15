package com.propets.apirest.main.controllers;

import com.propets.apirest.main.models.entity.CentroAtencion;
import com.propets.apirest.main.models.entity.Usuario;
import com.propets.apirest.main.models.objects.CentroAtencionData;
import com.propets.apirest.main.models.objects.UsuarioAuth;
import com.propets.apirest.main.services.CentroAtencionService;
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

import static com.propets.apirest.main.services.MessageService.errorMessage;
import static com.propets.apirest.main.services.MessageService.sendMessageError;

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
        if(Objects.isNull(centroAtencion))return sendMessageError("El Centro de Atencion "+id+" no Existe",HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(centroAtencion, HttpStatus.OK);
    }

    @PostMapping(value = "/centro_atencion",consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity<?> createCentroAtencion(@Valid @ModelAttribute CentroAtencionData data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        if(!existUsuario(data.getEmail()))return sendMessageError("El Usuario "+data.getEmail()+" no Existe",HttpStatus.UNAUTHORIZED);
        Usuario user = usuarioService.findByEmail(data.getEmail());
        if(!user.isAuth(data.getPassword())) return MessageService.loginError();
        if(!user.isAdmin()) return sendMessageError("El Usuario "+data.getEmail()+" no tiene permiso",HttpStatus.UNAUTHORIZED);
        CentroAtencion centroAtencion = new CentroAtencion(data);
        return new ResponseEntity<>(centroAtencionService.save(centroAtencion), HttpStatus.CREATED);
    }

    @PutMapping(value = "/centro_atencion/{id}",consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity updateCentroAtencion(@PathVariable String id,@Valid @ModelAttribute CentroAtencionData data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        if(!existUsuario(data.getEmail()))return sendMessageError("El Usuario "+data.getEmail()+" no Existe",HttpStatus.UNAUTHORIZED);
        Usuario user = usuarioService.findByEmail(data.getEmail());
        if(!user.isAuth(data.getPassword())) return MessageService.loginError();
        if(!user.isAdmin()) return sendMessageError("El Usuario "+data.getEmail()+" no tiene permiso",HttpStatus.UNAUTHORIZED);
        CentroAtencion centroAtencion = centroAtencionService.findById(id);
        if(Objects.isNull(centroAtencion)) return sendMessageError("El Centro "+id+" no Existe",HttpStatus.BAD_REQUEST);
        centroAtencion.update(data);
        return new ResponseEntity<>(centroAtencionService.save(centroAtencion), HttpStatus.ACCEPTED);
    }
    @DeleteMapping(value = "/centro_atencion/{id}",consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity deleteCentroAtencion(@PathVariable String id, @Valid @ModelAttribute UsuarioAuth data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        if(!existUsuario(data.getEmail()))return sendMessageError("El Usuario "+data.getEmail()+" no Existe",HttpStatus.UNAUTHORIZED);
        Usuario user = usuarioService.findByEmail(data.getEmail());
        if(!user.isAuth(data.getPassword())) return MessageService.loginError();
        if(!user.isAdmin()) return sendMessageError("El Usuario "+data.getEmail()+" no tiene permiso",HttpStatus.UNAUTHORIZED);
        CentroAtencion centroAtencion = centroAtencionService.findById(id);
        if(Objects.isNull(centroAtencion)) return sendMessageError("El Centro "+id+" no Existe",HttpStatus.BAD_REQUEST);
        centroAtencionService.delete(centroAtencion);
        Map<String, String> result = new HashMap<>();
        result.put("message","Centro Eliminado");
        result.put("centro_uuid", id);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }
    private Boolean existUsuario(String email){return !Objects.isNull(usuarioService.findByEmail(email));}
}
