package com.propets.apirest.main.controllers;

import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.propets.apirest.main.models.dto.PageDto;
import com.propets.apirest.main.models.dto.PetDto;
import com.propets.apirest.main.models.dto.ResponseDto;
import com.propets.apirest.main.models.dto.response.ActionDto;
import com.propets.apirest.main.models.entity.Pet;
import com.propets.apirest.main.models.entity.User;
import com.propets.apirest.main.models.enums.ResponseType;
import com.propets.apirest.main.services.messages.MessageServiceImplement;
import com.propets.apirest.main.services.pets.PetServiceImplement;
import com.propets.apirest.main.services.roles.RoleServiceImplement;
import com.propets.apirest.main.services.users.UserServiceImplement;

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
    public @ResponseBody ResponseEntity<ResponseDto> getPetById(@PathVariable String id, HttpServletRequest request) {
        Pet pet = petService.findById(id);
        if (Objects.isNull(pet))
            return messageService.entityNotFound(id, request.getRequestURI());
        PetDto response = new PetDto();
        response.update(pet);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    @GetMapping(value = "/pet")
    public @ResponseBody ResponseEntity<ResponseDto> getPagePets(@RequestHeader("authorization") String authorization,
            @RequestHeader("pet-page-size") Integer size, @RequestHeader("pet-page") Integer page,
            @RequestHeader(value = "pet-page-email", required = false) String email) {
        User user = userService.findByToken(authorization);
        Page<Pet> pets;
        if (Boolean.TRUE.equals(roleService.isAdmin(user))) {
            pets = petService.findAllByAdmin(email, size, page);
        } else
            pets = petService.findAllByEmail(user.getEmail(), PageRequest.of(page, size));
        PageDto response = new PageDto(pets);
        response.setContent(pets.getContent().stream().map(PetDto::new).collect(Collectors.toList()));
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @PostMapping(value = "/pet")
    public @ResponseBody ResponseEntity<ResponseDto> createPet(@RequestHeader("authorization") String authorization,
            @RequestHeader(value = "user-email", required = false) String email, @Valid @ModelAttribute PetDto data,
            BindingResult validationResult, HttpServletRequest request) {
        if (validationResult.hasErrors())
            return messageService.invalidFields(validationResult, request.getRequestURI());
        User userRequest = userService.findByToken(authorization);
        User user;
        if (Boolean.TRUE.equals(roleService.isAdmin(userRequest) && email != null && !"".equals(email))) {
            user = userService.findByEmail(email);
            if (Objects.isNull(user))
                return messageService.entityNotFound(email, request.getRequestURI());
        } else
            user = userService.findByToken(authorization);
        Pet pet = petService.createPetByData(user, data);
        PetDto response = new PetDto(petService.save(pet));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Secured("ROLE_USER")
    @PutMapping(value = "/pet/{id}")
    public @ResponseBody ResponseEntity<ResponseDto> update(@RequestHeader("authorization") String authorization,
            @PathVariable String id, @Valid @ModelAttribute PetDto data, BindingResult validationResult,
            HttpServletRequest request) {
        if (validationResult.hasErrors())
            return messageService.invalidFields(validationResult, request.getRequestURI());
        Pet pet = petService.findById(id);
        if (Objects.isNull(pet))
            return messageService.entityNotFound(id, request.getRequestURI());
        User user = userService.findByToken(authorization);
        if (!pet.getUser().getEmail().equals(user.getEmail()) && Boolean.FALSE.equals(roleService.isAdmin(user)))
            return messageService.unauthorized(request.getRequestURI());
        petService.updatePetByData(pet, data);
        PetDto response = new PetDto(petService.save(pet));
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Secured("ROLE_USER")
    @DeleteMapping(value = "/pet/{id}")
    public @ResponseBody ResponseEntity<ResponseDto> delete(@RequestHeader("authorization") String authorization,
            @PathVariable String id, HttpServletRequest request) {
        User user = userService.findByToken(authorization);
        Pet pet = petService.findById(id);
        if (Objects.isNull(pet))
            return messageService.entityNotFound(id, request.getRequestURI());
        if (!pet.getUser().getEmail().equals(user.getEmail()) && Boolean.FALSE.equals(roleService.isAdmin(user)))
            return messageService.unauthorized(request.getRequestURI());
        petService.disablePet(pet);
        return new ResponseEntity<>(new ActionDto(ResponseType.ENTITY_DELETE), HttpStatus.ACCEPTED);
    }
}