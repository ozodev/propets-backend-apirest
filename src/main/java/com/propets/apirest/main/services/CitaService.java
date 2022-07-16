package com.propets.apirest.main.services;

import com.propets.apirest.main.models.dao.ICitaDao;
import com.propets.apirest.main.models.entity.Cita;
import com.propets.apirest.main.models.entity.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CitaService {

    @Autowired
    private ICitaDao citaDao;

    @Transactional
    public List<Cita> findAll(){return citaDao.findAll();}
    @Transactional
    public Cita save(Cita cita){return citaDao.save(cita);}
    @Transactional
    public Cita findByMascota(Mascota mascota){return citaDao.findByMascota(mascota.getId()).orElse(null);}
    @Transactional
    public void delete(Cita cita){citaDao.delete(cita);}
}
