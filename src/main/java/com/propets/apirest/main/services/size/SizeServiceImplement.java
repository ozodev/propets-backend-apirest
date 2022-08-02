package com.propets.apirest.main.services.size;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.propets.apirest.main.models.dao.atributos.ISizeDao;
import com.propets.apirest.main.models.entity.atributos.Size;

@Service
public class SizeServiceImplement implements SizeServices {

    @Autowired
    private ISizeDao sizeDao;

    @Override
    @Transactional(readOnly = true)
    public List<Size> findAll() {
        return sizeDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Size findById(Long id) {
        return sizeDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Size save(Size size) {
        return sizeDao.save(size);
    }

    @Override
    @Transactional
    public void disable(Size size) {
        size.setEnabled(false);
        save(size);
    }

    @Override
    @Transactional(readOnly = true)
    public Size findByName(String name){
        return sizeDao.findByName(name).orElse(null);
    }
}