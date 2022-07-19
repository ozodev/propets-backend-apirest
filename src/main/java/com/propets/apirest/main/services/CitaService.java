package com.propets.apirest.main.services;

import com.propets.apirest.main.models.Enums.StatusType;
import com.propets.apirest.main.models.dao.ICitaDao;
import com.propets.apirest.main.models.entity.Cita;
import com.propets.apirest.main.models.entity.Veterinario;
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
    public void delete(Cita cita){citaDao.delete(cita);}
    @Transactional(readOnly = true)
    public List<Cita> findByVeterinario(Veterinario veterinario){return citaDao.findByVeterinario(veterinario.getId());}
    @Transactional(readOnly = true)
    public Cita findById(String id){return citaDao.findById(id).orElse(null);}
    @Transactional
    public void deleteById(String id){citaDao.deleteById(id);}
    @Transactional(readOnly = true)
    public Cita findByDataAndStatus(Cita data, StatusType status){return citaDao.findByDataAndStatus(data.getMascota().getId(),data.getDia(), data.getMes(), data.getYear(),data.getFranja().getFranja(),status.getStatus()).orElse(null);}
}
