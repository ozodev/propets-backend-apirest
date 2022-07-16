package com.propets.apirest.main.services;

import com.propets.apirest.main.models.dao.IVeterinarioDao;
import com.propets.apirest.main.models.entity.Veterinario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VeterinarioService {

    @Autowired
    private IVeterinarioDao veterinarioDao;

    @Transactional
    public List<Veterinario> findAll(){return veterinarioDao.findAll();}
    @Transactional
    public Veterinario findById(String id){return veterinarioDao.findById(id).orElse(null);}
    @Transactional
    public Veterinario save(Veterinario veterinario){return veterinarioDao.save(veterinario);}
    @Transactional
    public void delete(Veterinario veterinario){veterinarioDao.delete(veterinario);}
}
