package com.propets.apirest.main.services;

import com.propets.apirest.main.models.dao.ICentroAtencionDao;
import com.propets.apirest.main.models.entity.CentroAtencion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CentroAtencionService {
    @Autowired
    private ICentroAtencionDao centroAtencionDao;
    @Transactional
    public CentroAtencion save(CentroAtencion centroAtencion){
        if(Objects.isNull(centroAtencion.getId())) centroAtencion.setId(UUID.randomUUID().toString());
        return centroAtencionDao.save(centroAtencion);
    }
    @Transactional(readOnly = true)
    public List<CentroAtencion> findAll(){
        return (List<CentroAtencion>) centroAtencionDao.findAll();
    }
    @Transactional
    public void delete(CentroAtencion centroAtencion){centroAtencionDao.delete(centroAtencion);}
    public void delete(String id){delete(findById(id));}
    @Transactional(readOnly = true)
    public CentroAtencion findById(String id){return centroAtencionDao.findById(id).orElse(null);}
    public boolean exists(String id){
        CentroAtencion centroAtencion = findById(id);
        return (Objects.isNull(centroAtencion))? false:true;
    }
}