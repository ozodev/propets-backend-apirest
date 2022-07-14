package com.propets.apirest.main.services;

import com.propets.apirest.main.models.dao.IMascotaDao;
import com.propets.apirest.main.models.entity.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MascotaService {

    @Autowired
    private IMascotaDao mascotaDao;

    @Transactional
    public Mascota save(Mascota mascota){
        return mascotaDao.save(mascota);
    }

    @Transactional
    public Mascota findById(String id){
        return mascotaDao.findById(id).orElse(null);
    }

    @Transactional
    public void delete(Mascota mascota){mascotaDao.delete(mascota);}
}
