package com.propets.apirest.main.services.colors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.propets.apirest.main.models.dao.atributos.IColorDao;
import com.propets.apirest.main.models.entity.atributos.Color;

@Service
public class ColorServiceImplement implements ColorService {

    @Autowired
    private IColorDao colorDao;

    @Override
    @Transactional(readOnly = true)
    public List<Color> findAll() {
        return colorDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Color findById(Long id) {
        return colorDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Color save(Color color) {
        return colorDao.save(color);
    }

    @Override
    @Transactional
    public void disable(Color color) {
        color.setEnabled(false);
        save(color);
    }

    @Override
    @Transactional(readOnly = true)
    public Color findByName(String name) {
        return colorDao.findByName(name).orElse(null);
    }

}
