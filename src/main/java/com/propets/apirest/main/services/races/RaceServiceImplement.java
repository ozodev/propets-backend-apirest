package com.propets.apirest.main.services.races;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.propets.apirest.main.models.dao.atributos.IRaceDao;
import com.propets.apirest.main.models.entity.atributos.Race;

@Service
public class RaceServiceImplement implements RaceService {

    @Autowired
    private IRaceDao raceDao;

    @Override
    @Transactional(readOnly = true)
    public List<Race> findAll() {
        return raceDao.findAll();
    }

    @Override
    public Race findById(Long id) {
        return raceDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Race save(Race race) {
        return raceDao.save(race);
    }

    @Transactional
    @Override
    public void disable(Race race) {
        race.setEnabled(false);
        save(race);
    }
    @Override
    @Transactional(readOnly = true)
    public Race findByName(String name){
        return raceDao.findByName(name).orElse(null);
    }

}