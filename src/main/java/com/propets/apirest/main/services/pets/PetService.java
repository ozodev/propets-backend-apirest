package com.propets.apirest.main.services.pets;

import com.propets.apirest.main.models.dto.PetDto;
import com.propets.apirest.main.models.entity.Pet;
import com.propets.apirest.main.models.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PetService {

    Pet save(Pet pet);

    Pet findById(String id);

    void delete(Pet pet);

    List<Pet> findAll();

    Page<Pet> findAll(Pageable pageable);

    Page<Pet> findAllByEmail(String email, Pageable pageable);

    Page<Pet> findAllByAdmin(int size, int page);

    Page<Pet> findAllByAdmin(String email, int size, int page);

    Pet createPetByData(User user, PetDto petDto);

    Pet updatePetByData(Pet pet, PetDto petDto);

    void disablePet(Pet pet);
}
