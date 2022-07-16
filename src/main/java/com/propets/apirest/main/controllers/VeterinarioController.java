package com.propets.apirest.main.controllers;

import com.propets.apirest.main.models.Enums.RoleType;
import com.propets.apirest.main.models.Enums.VeterinarioType;
import com.propets.apirest.main.models.entity.CentroAtencion;
import com.propets.apirest.main.models.entity.Usuario;
import com.propets.apirest.main.models.entity.Veterinario;
import com.propets.apirest.main.models.objects.VeterinarioAuth;
import com.propets.apirest.main.models.objects.VeterinarioDelete;
import com.propets.apirest.main.services.CentroAtencionService;
import com.propets.apirest.main.services.MessageService;
import com.propets.apirest.main.services.UsuarioService;
import com.propets.apirest.main.services.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.propets.apirest.main.services.MessageService.errorMessage;
import static com.propets.apirest.main.services.MessageService.sendMessageError;


@RestController
@RequestMapping("/api")
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CentroAtencionService centroAtencionService;

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
    @PostMapping(value = "/veterinario")
    public @ResponseBody ResponseEntity<?> create(@Valid @ModelAttribute VeterinarioAuth data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Usuario admin = usuarioService.findByEmail(data.getEmail());
        if(Objects.isNull(admin)) return MessageService.loginError();
        if(!admin.isAuth(data.getPassword())) return MessageService.loginError();
        if(!admin.isAdmin()) return sendMessageError("El Usuario "+data.getEmail()+" no esta Autorizado",HttpStatus.UNAUTHORIZED);
        if(Objects.isNull(centroAtencionService.findById(data.getCentro()))) return sendMessageError("El Centro "+data.getCentro()+" no existe",HttpStatus.UNAUTHORIZED);
        Usuario user = usuarioService.findByEmail(data.getUsuario());
        if(Objects.isNull(user)) return sendMessageError("El Usuario "+data.getUsuario()+" no existe",HttpStatus.UNAUTHORIZED);
        if(user.isVeterinario()) return sendMessageError("El Usuario "+data.getUsuario()+" ya es Veterinario",HttpStatus.UNAUTHORIZED);
        user.setRol(RoleType.VETERINARIO);
        Veterinario veterinario = new Veterinario(data);
        veterinario.setUsuario(user);
        veterinario.setCentroAtencion(centroAtencionService.findById(data.getCentro()));
        user.setVeterinario(veterinario);
        return new ResponseEntity<>(usuarioService.save(user),HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/veterinario")
    public @ResponseBody ResponseEntity<?> update(@Valid @ModelAttribute VeterinarioAuth data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Usuario admin = usuarioService.findByEmail(data.getEmail());
        Usuario user = usuarioService.findByEmail(data.getUsuario());
        if(Objects.isNull(admin)) return MessageService.loginError();
        if(Objects.isNull(user)) return sendMessageError("El Usuario "+data.getUsuario()+" no existe",HttpStatus.UNAUTHORIZED);
        if(!admin.isAuth(data.getPassword()) && !user.isAuth(data.getPassword())) return MessageService.loginError();
        CentroAtencion centroAtencion = centroAtencionService.findById(data.getCentro());
        if(Objects.isNull(centroAtencion)) return sendMessageError("El Centro "+data.getCentro()+" no existe",HttpStatus.UNAUTHORIZED);
        if(!user.isVeterinario()) return sendMessageError("El Usuario "+data.getUsuario()+" no es Veterinario",HttpStatus.UNAUTHORIZED);
        Veterinario veterinario = user.getVeterinario();
        veterinario.setEspecialidad(VeterinarioType.valueOf(data.getEspecialidad()));
        veterinario.setCedula(data.getCedula());
        veterinario.setCentroAtencion(centroAtencion);
        return new ResponseEntity<>(usuarioService.save(user),HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/veterinario")
    public @ResponseBody ResponseEntity<?> delete(@Valid @ModelAttribute VeterinarioDelete data, BindingResult validationResult){
        if(validationResult.hasErrors()) return errorMessage(validationResult);
        Usuario admin = usuarioService.findByEmail(data.getEmail());
        Usuario user = usuarioService.findByEmail(data.getUsuario());
        if(Objects.isNull(admin)) return MessageService.loginError();
        if(Objects.isNull(user)) return sendMessageError("El Usuario "+data.getUsuario()+" no existe",HttpStatus.UNAUTHORIZED);
        if(!admin.isAuth(data.getPassword()) && !user.isAuth(data.getPassword())) return MessageService.loginError();
        if(!user.isVeterinario()) return sendMessageError("El Usuario "+data.getUsuario()+" no es Veterinario",HttpStatus.UNAUTHORIZED);
        Veterinario veterinario = user.getVeterinario();
        user.setVeterinario(null);
        user.setRol(RoleType.USUARIO);
        veterinarioService.delete(veterinario);
        return new ResponseEntity<>(usuarioService.save(user),HttpStatus.ACCEPTED);
    }
}
