package com.propets.apirest.main.controllers;

import com.propets.apirest.main.models.entity.Mascota;
import com.propets.apirest.main.models.entity.Usuario;
import com.propets.apirest.main.models.objects.MascotaData;
import com.propets.apirest.main.models.objects.MascotaDelete;
import com.propets.apirest.main.services.MascotaService;
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
public class MascotaController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MascotaService mascotaService;

    @GetMapping(value = "/mascota/{id}")
    public @ResponseBody ResponseEntity<?> show(@PathVariable String id){
        Mascota mascota = mascotaService.findById(id);
        if(Objects.isNull(mascota)) return sendMessageError("La Mascota "+id+" no Existe",HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(mascota,HttpStatus.OK);
    }
    @PostMapping(value = "/mascota",consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity<?> createMascota(@Valid @ModelAttribute MascotaData data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        if(Objects.isNull(usuarioService.findByEmail(data.getEmail()))) return sendMessageError("El Usuario "+data.getEmail()+" no Existe",HttpStatus.UNAUTHORIZED);
        Usuario user = usuarioService.findByEmail(data.getEmail());
        if(!user.isAuth(data.getPassword())) return MessageService.loginError();
        Mascota mascota = new Mascota(data,user);
        mascotaService.save(mascota);
        return new ResponseEntity<>(mascota,HttpStatus.CREATED);
    }

    @PutMapping(value ="/mascota", consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity<?> updateMascota(@Valid @ModelAttribute MascotaData data,BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        if(Objects.isNull(usuarioService.findByEmail(data.getEmail())))return sendMessageError("El Usuario "+data.getEmail()+" no Existe",HttpStatus.UNAUTHORIZED);
        Usuario user = usuarioService.findByEmail(data.getEmail());
        if(!user.isAuth(data.getPassword())) return MessageService.loginError();
        if(Objects.isNull(mascotaService.findById(data.getId()))) return sendMessageError("La Mascota de "+data.getEmail()+" no Existe",HttpStatus.UNAUTHORIZED);
        Mascota mascota = mascotaService.findById(data.getId());
        mascota.update(data);
        return new ResponseEntity<>(mascotaService.save(mascota), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/mascota",consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity<?> deleteMascota(@Valid @ModelAttribute MascotaDelete data,BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        if(Objects.isNull(usuarioService.findByEmail(data.getEmail())))return sendMessageError("El Usuario "+data.getEmail()+" no Existe",HttpStatus.UNAUTHORIZED);
        Usuario user = usuarioService.findByEmail(data.getEmail());
        if(!user.isAuth(data.getPassword())) return MessageService.loginError();
        if(Objects.isNull(mascotaService.findById(data.getId()))) return sendMessageError("La Mascota de "+data.getEmail()+" no Existe",HttpStatus.UNAUTHORIZED);
        Mascota mascota = mascotaService.findById(data.getId());
        mascotaService.delete(mascota);
        Map<String, String> result = new HashMap<>();
        result.put("message","Mascota fue Eliminado");
        result.put("usuario_email",user.getEmail());
        result.put("mascota_uuid", data.getId());
        return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
    }
}
