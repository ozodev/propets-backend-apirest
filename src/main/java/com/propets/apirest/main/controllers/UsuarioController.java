package com.propets.apirest.main.controllers;

import com.propets.apirest.main.models.entity.Autenticacion;
import com.propets.apirest.main.models.entity.Usuario;
import com.propets.apirest.main.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/usuario", method = RequestMethod.GET, consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity<Usuario> show(@ModelAttribute Autenticacion auth){
        Usuario user = usuarioService.findByEmail(auth.getEmail());
        if(user == null) return null;
        if(!user.isAuth(auth.getPassword())) return null;
        return new ResponseEntity<Usuario>(user,HttpStatus.OK);
    }
}
