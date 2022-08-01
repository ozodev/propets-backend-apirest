package com.propets.apirest.main.services.colors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.propets.apirest.main.models.dao.atributos.IColorDao;
import com.propets.apirest.main.models.entity.atributos.Color;

public class ColorServiceImplement implements ColorService {

    @Autowired
    private IColorDao colorDao;

    @Override
    public List<Color> findAll() {
        return colorDao.findAll();
    }

    @Override
    public Color findById(Long id) {
        return colorDao.findById(id).orElse(null);
    }

    @Override
    public Color save(Color color) {
        return colorDao.save(color);
    }

    @Override
    public void disable(Color color) {
        color.setEnabled(false);
        save(color);
    }
    
}
