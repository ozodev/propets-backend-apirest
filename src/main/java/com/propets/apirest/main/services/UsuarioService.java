package com.propets.apirest.main.services;

import com.propets.apirest.main.models.dao.IUsuarioDao;
import com.propets.apirest.main.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Transactional(readOnly = true)
    public List<Usuario> findAll(){
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Transactional
    public Usuario findByEmail(String email){
        return usuarioDao.findById(email).orElse(null);
    }
}
