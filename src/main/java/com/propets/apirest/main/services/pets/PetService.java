package com.propets.apirest.main.services.pets;

import com.propets.apirest.main.models.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PetService {

    public Pet save(Pet pet);
    public Pet findById(String id);
    public void delete(Pet pet);
    public List<Pet> findAll();
    public Page<Pet> findAll(Pageable pageable);
    public Page<Pet> findAllByEmail(String email,Pageable pageable);
}
