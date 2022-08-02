package com.propets.apirest.main.services.races;

import java.util.List;

import com.propets.apirest.main.models.entity.atributos.Race;

public interface RaceService {

    List<Race> findAll();

    Race findById(Long id);

    Race save(Race race);

    void disable(Race race);

    Race findByName(String name);
}
