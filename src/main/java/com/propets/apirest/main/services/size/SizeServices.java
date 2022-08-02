package com.propets.apirest.main.services.size;

import java.util.List;

import com.propets.apirest.main.models.entity.atributos.Size;

public interface SizeServices {
    List<Size> findAll();

    Size findById(Long id);

    Size save(Size size);

    void disable(Size size);

    Size findByName(String name);
}
