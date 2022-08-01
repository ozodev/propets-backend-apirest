package com.propets.apirest.main.services.pets;

import com.propets.apirest.main.models.dao.IPetDao;
import com.propets.apirest.main.models.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetServiceImplement implements PetService {

    @Autowired
    private IPetDao petDao;

    @Transactional
    @Override
    public Pet save(Pet pet){return petDao.save(pet);}

    @Transactional(readOnly = true)
    @Override
    public Pet findById(String id){return petDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Pet pet){petDao.delete(pet);}

    @Override
    @Transactional(readOnly = true)
    public List<Pet> findAll(){return petDao.findAll();}

    @Override
    public Page<Pet> findAll(Pageable pageable) {return petDao.findAll(pageable);}

    @Override
    @Transactional(readOnly = true)
    public Page<Pet> findAllByEmail(String email,Pageable pageable) {
        return petDao.findAllByEmail(email,pageable);
    }

}
