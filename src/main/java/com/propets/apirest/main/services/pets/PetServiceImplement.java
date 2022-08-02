package com.propets.apirest.main.services.pets;

import com.propets.apirest.main.models.dao.IPetDao;
import com.propets.apirest.main.models.dto.PetDto;
import com.propets.apirest.main.models.entity.Pet;
import com.propets.apirest.main.models.entity.User;
import com.propets.apirest.main.services.colors.ColorServiceImplement;
import com.propets.apirest.main.services.races.RaceServiceImplement;
import com.propets.apirest.main.services.size.SizeServiceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PetServiceImplement implements PetService {

    @Autowired
    private IPetDao petDao;

    @Autowired
    private ColorServiceImplement colorService;

    @Autowired
    private RaceServiceImplement raceService;

    @Autowired
    private SizeServiceImplement sizeService;

    @Transactional
    @Override
    public Pet save(Pet pet) {
        return petDao.save(pet);
    }

    @Transactional(readOnly = true)
    @Override
    public Pet findById(String id) {
        return petDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Pet pet) {
        petDao.delete(pet);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pet> findAll() {
        return petDao.findAll();
    }

    @Override
    public Page<Pet> findAll(Pageable pageable) {
        return petDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Pet> findAllByEmail(String email, Pageable pageable) {
        return petDao.findAllByEmail(email, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Pet> findAllByAdmin(int size, int page) {
        return findAll(PageRequest.of(page, size));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Pet> findAllByAdmin(String email, int size, int page) {
        if (email != null && !"".equals(email)) {
            return findAllByEmail(email, PageRequest.of(page, size));
        } else
            return findAll(PageRequest.of(page, size));
    }

    @Override
    public Pet createPetByData(User user, PetDto petDto) {
        Pet pet = new Pet();
        pet.setId(UUID.randomUUID().toString());
        pet.setEnabled(true);
        updatePetByData(pet, petDto);
        pet.setUser(user);
        return pet;
    }

    @Override
    public Pet updatePetByData(Pet pet, PetDto petDto) {
        pet.setName(petDto.getName());
        pet.setPeso(petDto.getPeso());
        pet.setColor(colorService.findByName(petDto.getColor()));
        pet.setRace(raceService.findByName(petDto.getRace()));
        pet.setSize(sizeService.findByName(petDto.getSize()));
        return pet;
    }

    @Override
    @Transactional
    public void disablePet(Pet pet) {
        pet.setEnabled(false);
        save(pet);
    }

}
