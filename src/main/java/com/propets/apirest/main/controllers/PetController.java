package com.propets.apirest.main.controllers;

import com.propets.apirest.main.models.dto.*;
import com.propets.apirest.main.models.entity.Pet;
import com.propets.apirest.main.models.entity.User;
import com.propets.apirest.main.services.messages.MessageServiceImplement;
import com.propets.apirest.main.services.pets.PetServiceImplement;
import com.propets.apirest.main.services.roles.RoleServiceImplement;
import com.propets.apirest.main.services.users.UserServiceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PetController {
    @Autowired
    private UserServiceImplement userService;
    @Autowired
    private PetServiceImplement petService;
    @Autowired
    private RoleServiceImplement roleService;
    @Autowired
    private MessageServiceImplement messageService;

    @Secured({ "ROLE_VETERINARY", "ROLE_ADMIN" })
    @GetMapping(value = "/pet/{id}")
    public @ResponseBody ResponseEntity<?> getPetById(@PathVariable String id) {
        Pet pet = petService.findById(id);
        if (Objects.isNull(pet))
            return messageService.sendExistMessage("La Mascota No Existe", "pet", id, PATH + "/" + id,
                    HttpStatus.BAD_REQUEST);
        PetDto response = new PetDto();
        response.update(pet);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    @GetMapping(value = "/pets/{size}/{page}")
    public @ResponseBody ResponseEntity<PageDto> getPagePets(@RequestHeader("authorization") String authorization,
            @PathVariable Integer size, @PathVariable Integer page) {
        User user = userService.findByToken(authorization);
        Page<Pet> pets;
        if (user.getRoles().contains(roleService.findRole(1L)))
            pets = petService.findAll(PageRequest.of(page, size));
        else
            pets = petService.findAllByEmail(user.getEmail(), PageRequest.of(page, size));
        PageDto response = new PageDto(pets);
        response.setContent(pets.getContent().stream().map(PetDto::new).collect(Collectors.toList()));
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/pets/{email}/{size}/{page}")
    public @ResponseBody ResponseEntity<PageDto> getPagePetsByEmail(@PathVariable String email,
            @PathVariable Integer size, @PathVariable Integer page) {
        Page<Pet> pets = petService.findAllByEmail(email, PageRequest.of(page, size));
        PageDto response = new PageDto(pets);
        response.setContent(pets.getContent().stream().map(PetDto::new).collect(Collectors.toList()));
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @PostMapping(value = "/pet")
    public @ResponseBody ResponseEntity<?> createPet(@RequestHeader("authorization") String authorization,
            @Valid @ModelAttribute PetDto data, BindingResult validationResult) {
        if (validationResult.hasErrors())
            return messageService.errorMessage(validationResult);
        User user = userService.findByToken(authorization);
        Pet pet = new Pet(data);
        pet.setId(UUID.randomUUID().toString());
        pet.setUser(user);
        return new ResponseEntity<>(new PetDto(petService.save(pet)), HttpStatus.CREATED);
    }

    @Secured("ROLE_USER")
    @PutMapping(value = "/pet/{id}")
    public @ResponseBody ResponseEntity<?> update(@RequestHeader("authorization") String authorization,
            @PathVariable String id, @Valid @ModelAttribute PetDto data, BindingResult validationResult) {
        if (validationResult.hasErrors())
            return messageService.errorMessage(validationResult);
        Pet pet = petService.findById(id);
        if (Objects.isNull(pet))
            return messageService.sendErrorMessage("Mascota no existe", "Mascota not Found", PATH + "/" + id,
                    HttpStatus.BAD_REQUEST);
        User user = userService.findByToken(authorization);
        if (!pet.getUser().getEmail().equals(user.getEmail()))
            return messageService.sendErrorMessage("No es propietario", "Not Authorized", PATH + "/" + id,
                    HttpStatus.UNAUTHORIZED);
        pet.update(data);
        return new ResponseEntity<>(new PetDto(petService.save(pet)), HttpStatus.ACCEPTED);
    }

    @Secured("ROLE_USER")
    @DeleteMapping(value = "/pet/{id}")
    public @ResponseBody ResponseEntity<?> delete(@RequestHeader("authorization") String authorization,
            @PathVariable String id) {
        User user = userService.findByToken(authorization);
        Pet pet = petService.findById(id);
        if (Objects.isNull(pet))
            return messageService.sendErrorMessage("Mascota no existe", "Mascota not Found", PATH + "/" + id,
                    HttpStatus.BAD_REQUEST);
        if (!(pet.getUser().getEmail().equals(user.getEmail()) || userService.isAdmin(user)))
            return messageService.sendErrorMessage("No es propietario", "Not Authorized", PATH + "/" + id,
                    HttpStatus.UNAUTHORIZED);
        pet.setUser(null);
        petService.delete(pet);
        return messageService.sendDeleteMessage("Mascota Eliminada con Exito", "pet", id, PATH + "/" + id,
                HttpStatus.OK);
    }

    private static final String PATH = "/api/pet";
}