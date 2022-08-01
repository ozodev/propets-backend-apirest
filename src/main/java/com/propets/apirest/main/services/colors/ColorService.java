package com.propets.apirest.main.services.colors;

import java.util.List;

import com.propets.apirest.main.models.entity.atributos.Color;

public interface ColorService {
    List<Color> findAll();

    Color findById(Long id);

    Color save(Color color);

    void disable(Color color);
}
