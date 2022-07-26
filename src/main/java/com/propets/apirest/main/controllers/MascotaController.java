package com.propets.apirest.main.controllers;

import com.propets.apirest.main.models.Enums.ColorType;
import com.propets.apirest.main.models.Enums.RazaType;
import com.propets.apirest.main.models.Enums.SizeType;
import com.propets.apirest.main.models.entity.Mascota;
import com.propets.apirest.main.models.entity.Usuario;
import com.propets.apirest.main.services.MascotaService;
import com.propets.apirest.main.services.MessageService;
import com.propets.apirest.main.services.UsuarioService;
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
public class MascotaController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MascotaService mascotaService;
    @Secured("ROLE_USER")
    @GetMapping(value = "/mascota")
    public @ResponseBody ResponseEntity<?> show(@RequestHeader("authorization") String authorization){
        Usuario user = usuarioService.findByToken(authorization);
        return new ResponseEntity<>(user.getMascotas(),HttpStatus.ACCEPTED);
    }
    @Secured({"ROLE_VETERINARIO","ROLE_ADMIN"})
    @GetMapping(value = "/mascota/{id}")
    public @ResponseBody ResponseEntity<?> showById(@PathVariable String id){
        Mascota mascota = mascotaService.findById(id);
        if(Objects.isNull(mascota)) return MessageService.sendExistMessage("La Mascota No Existe","mascota",id,"/api/mascota/"+id,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(mascota,HttpStatus.OK);
    }

    @GetMapping(value = "/mascota/raza")
    public @ResponseBody ResponseEntity<?> showRazas(){
        List<Map<String, String>> razas = new ArrayList<>();
        for(RazaType razaType:RazaType.values()) {
            Map<String, String> raza = new HashMap<>();
            raza.put("title",razaType.getTitulo());
            raza.put("value",razaType.toString());
            razas.add(raza);
        }
        return new ResponseEntity<>(razas,HttpStatus.OK);
    }
    @GetMapping(value = "/mascota/color")
    public @ResponseBody ResponseEntity<?> showColores(){return new ResponseEntity<>(ColorType.values(),HttpStatus.OK);}
    @GetMapping(value = "/mascota/size")
    public @ResponseBody ResponseEntity<?> showSize(){return new ResponseEntity<>(SizeType.values(),HttpStatus.OK);}
    @Secured("ROLE_USER")
    @PostMapping(value = "/mascota")
    public @ResponseBody ResponseEntity<?> create(@RequestHeader("authorization") String authorization,@Valid @ModelAttribute Mascota data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Usuario user = usuarioService.findByToken(authorization);
        data.setUsuario(user);
        data.setId(UUID.randomUUID().toString());
        user.addMascotas(data);
        mascotaService.save(data);
        return new ResponseEntity<>(usuarioService.save(user),HttpStatus.CREATED);
    }
    @Secured("ROLE_USER")
    @PutMapping(value ="/mascota/{id}")
    public @ResponseBody ResponseEntity<?> update(@RequestHeader("authorization") String authorization,@PathVariable String id,@Valid @ModelAttribute Mascota data,BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Usuario user = usuarioService.findByToken(authorization);
        Mascota mascota = mascotaService.findById(id);
        if(Objects.isNull(mascota)) return MessageService.sendErrorMessage("Mascota no existe","Mascota not Found","/api/mascota/"+id,HttpStatus.BAD_REQUEST);
        if(!mascota.getUsuario().getEmail().equals(user.getEmail())) return MessageService.sendErrorMessage("No es propietario","Not Authorized","/api/mascota/"+id,HttpStatus.UNAUTHORIZED);
        mascota.update(data);
        return new ResponseEntity<>(mascotaService.save(mascota), HttpStatus.ACCEPTED);
    }
    @Secured("ROLE_USER")
    @DeleteMapping(value = "/mascota/{id}")
    public @ResponseBody ResponseEntity<?> delete(@RequestHeader("authorization") String authorization,@PathVariable String id){
        Usuario user = usuarioService.findByToken(authorization);
        Mascota mascota = mascotaService.findById(id);
        if(Objects.isNull(mascota)) return MessageService.sendErrorMessage("Mascota no existe","Mascota not Found","/api/mascota/"+id,HttpStatus.BAD_REQUEST);
        if(!mascota.getUsuario().getEmail().equals(user.getEmail())) return MessageService.sendErrorMessage("No es propietario","Not Authorized","/api/mascota/"+id,HttpStatus.UNAUTHORIZED);
        mascotaService.delete(mascota);
        return MessageService.sendDeleteMessage("Mascota Eliminada con Exito","Mascota",id,"/api/mascota/"+id,HttpStatus.OK);
    }
}