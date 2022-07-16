package com.propets.apirest.main.controllers;

import com.propets.apirest.main.models.entity.*;
import com.propets.apirest.main.models.objects.CitaData;
import com.propets.apirest.main.models.objects.CitaDelete;
import com.propets.apirest.main.services.*;
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

    @GetMapping(value = "/cita")
    public @ResponseBody ResponseEntity<?> show(){
        return new ResponseEntity<>(citaService.findAll(), HttpStatus.OK);
    }
    @PostMapping(value = "/cita")
    public @ResponseBody ResponseEntity<?> create(@Valid @ModelAttribute CitaData data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Usuario user = usuarioService.findByEmail(data.getUsuario());
        if(Objects.isNull(user)) return sendMessageError("El Usuario "+data.getUsuario()+" no Existe",HttpStatus.UNAUTHORIZED);
        Mascota mascota = mascotaService.findById(data.getMascota());
        if(Objects.isNull(mascota)) return sendMessageError("La Mascota "+data.getMascota()+" no Existe",HttpStatus.UNAUTHORIZED);
        Veterinario veterinario = veterinarioService.findById(data.getVeterinario());
        if(Objects.isNull(veterinario)) return sendMessageError("El Veterinario "+data.getVeterinario()+" no Existe",HttpStatus.UNAUTHORIZED);
        CentroAtencion centroAtencion = centroAtencionService.findById(data.getCentro());
        if(Objects.isNull(centroAtencion)) return sendMessageError("El Centro de Atencion "+data.getCentro()+" no Existe",HttpStatus.UNAUTHORIZED);
        Cita prevCita = citaService.findByMascota(mascota);
        if(!Objects.isNull(prevCita)) return sendMessageError("La Mascota "+data.getMascota()+" ya tiene una Cita",HttpStatus.UNAUTHORIZED);
        Cita cita = new Cita(data);
        cita.setUsuario(user);
        cita.setMascota(mascota);
        cita.setVeterinario(veterinario);
        cita.setCentroAtencion(centroAtencion);
        return new ResponseEntity<>(citaService.save(cita),HttpStatus.ACCEPTED);
    }
    @PutMapping(value = "/cita")
    public @ResponseBody ResponseEntity<?> update(@Valid @ModelAttribute CitaData data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Usuario user = usuarioService.findByEmail(data.getUsuario());
        if(Objects.isNull(user)) return sendMessageError("El Usuario "+data.getUsuario()+" no Existe",HttpStatus.UNAUTHORIZED);
        Mascota mascota = mascotaService.findById(data.getMascota());
        if(Objects.isNull(mascota)) return sendMessageError("La Mascota "+data.getMascota()+" no Existe",HttpStatus.UNAUTHORIZED);
        Veterinario veterinario = veterinarioService.findById(data.getVeterinario());
        if(Objects.isNull(veterinario)) return sendMessageError("El Veterinario "+data.getVeterinario()+" no Existe",HttpStatus.UNAUTHORIZED);
        CentroAtencion centroAtencion = centroAtencionService.findById(data.getCentro());
        if(Objects.isNull(centroAtencion)) return sendMessageError("El Centro de Atencion "+data.getCentro()+" no Existe",HttpStatus.UNAUTHORIZED);
        Cita cita = citaService.findByMascota(mascota);
        if(Objects.isNull(cita)) return sendMessageError("La Mascota "+data.getMascota()+" no tiene una Cita",HttpStatus.UNAUTHORIZED);
        cita.update(data);
        cita.setUsuario(user);
        cita.setMascota(mascota);
        cita.setVeterinario(veterinario);
        cita.setCentroAtencion(centroAtencion);
        return new ResponseEntity<>(citaService.save(cita),HttpStatus.ACCEPTED);
    }
    @DeleteMapping(value = "/cita")
    public @ResponseBody ResponseEntity<?> delete(@Valid @ModelAttribute CitaDelete data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Mascota mascota = mascotaService.findById(data.getMascota());
        if(Objects.isNull(mascota)) return sendMessageError("La Mascota "+data.getMascota()+" no Existe",HttpStatus.UNAUTHORIZED);
        Cita cita = citaService.findByMascota(mascota);
        if(Objects.isNull(cita)) return sendMessageError("La Mascota "+data.getMascota()+" no tiene una Cita",HttpStatus.UNAUTHORIZED);
        citaService.delete(cita);
        Map<String, String> result = new HashMap<>();
        result.put("message","Cita fue Eliminada");
        result.put("mascota_uuid", data.getMascota());
        return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
    }

}
