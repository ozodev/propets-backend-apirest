package com.propets.apirest.main.services;

import com.propets.apirest.main.models.dao.ICentroAtencionDao;
import com.propets.apirest.main.models.entity.CentroAtencion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CentroAtencionService {

    @Autowired
    private ICentroAtencionDao centroAtencionDao;

    @Transactional
    public CentroAtencion save(CentroAtencion centroAtencion){
        return centroAtencionDao.save(centroAtencion);
    }
    @Transactional(readOnly = true)
    public List<CentroAtencion> findAll(){
        return (List<CentroAtencion>) centroAtencionDao.findAll();
    }
    @Transactional
    public void delete(CentroAtencion centroAtencion){centroAtencionDao.delete(centroAtencion);}
    @Transactional(readOnly = true)
    public CentroAtencion findById(String id){return centroAtencionDao.findById(id).orElse(null);}
}
