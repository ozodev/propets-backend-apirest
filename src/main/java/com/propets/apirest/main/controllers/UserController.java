package com.propets.apirest.main.controllers;

import com.propets.apirest.main.models.dto.ResponseDto;
import com.propets.apirest.main.models.dto.UserDto;
import com.propets.apirest.main.models.dto.response.ActionDto;
import com.propets.apirest.main.models.entity.User;
import com.propets.apirest.main.models.enums.ResponseType;
import com.propets.apirest.main.services.messages.MessageServiceImplement;
import com.propets.apirest.main.services.roles.RoleServiceImplement;
import com.propets.apirest.main.services.users.UserServiceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.Objects;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserServiceImplement userServiceImplement;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoleServiceImplement roleService;
    @Autowired
    private MessageServiceImplement messageService;

    @Secured("ROLE_USER")
    @GetMapping(value = "/user")
    public @ResponseBody ResponseEntity<ResponseDto> getUser(
            @RequestHeader("authorization") String authorization) {
        User user = userServiceImplement.findByToken(authorization);
        UserDto response = userServiceImplement.getUserDto(user);
        userServiceImplement.save(user);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Secured({ "ROLE_ADMIN", "ROLE_VETERINARY" })
    @GetMapping(value = "/user/{email}")
    public @ResponseBody ResponseEntity<ResponseDto> getUserByID(@PathVariable String email,
            HttpServletRequest request) {
        User user = userServiceImplement.findByEmail(email);
        if (Objects.isNull(user))
            return messageService.entityNotFound(request.getRequestURI());
        UserDto response = userServiceImplement.getUserDto(user);
        userServiceImplement.save(user);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/user")
    public @ResponseBody ResponseEntity<ResponseDto> createUser(@Valid @ModelAttribute UserDto data,
            BindingResult validationResult, HttpServletRequest request) {
        if (validationResult.hasErrors())
            return messageService.invalidFields(validationResult, request.getRequestURI());
        if (Boolean.TRUE.equals(userServiceImplement.exist(data.getEmail())))
            return messageService.entityExist(request.getRequestURI());
        User user = new User(data);
        user.addRole(roleService.findRole(3L));
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        UserDto response = userServiceImplement.getUserDto(userServiceImplement.save(user));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Secured("ROLE_USER")
    @PutMapping(value = "/user")
    public @ResponseBody ResponseEntity<ResponseDto> updateUser(
            @RequestHeader("authorization") String authorization,
            @Valid @ModelAttribute UserDto data, BindingResult validationResult, HttpServletRequest request) {
        if (validationResult.hasErrors())
            return messageService.invalidFields(validationResult, request.getRequestURI());
        if (Boolean.FALSE.equals(userServiceImplement.exist(data.getEmail())))
            return messageService.entityNotFound(request.getRequestURI());
        User user = userServiceImplement.findByToken(authorization);
        user.update(data);
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        UserDto response = userServiceImplement.getUserDto(userServiceImplement.save(user));
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/user/{email}")
    public @ResponseBody ResponseEntity<ResponseDto> updateUserById(@PathVariable String email,
            @Valid @ModelAttribute UserDto data, BindingResult validationResult, HttpServletRequest request) {
        if (validationResult.hasErrors())
            return messageService.invalidFields(validationResult, request.getRequestURI());
        if (Boolean.FALSE.equals(userServiceImplement.exist(email)))
            return messageService.entityNotFound(request.getRequestURI());
        User user = userServiceImplement.findById(email);
        user.update(data);
        UserDto response = userServiceImplement.getUserDto(userServiceImplement.save(user));
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Secured("ROLE_USER")
    @DeleteMapping(value = "/user")
    public @ResponseBody ResponseEntity<ResponseDto> deleteUser(
            @RequestHeader("authorization") String authorization) {
        User user = userServiceImplement.findByToken(authorization);
        user.setRoles(null);
        userServiceImplement.save(user);
        userServiceImplement.delete(user);
        return new ResponseEntity<>(new ActionDto(ResponseType.ENTITY_DELETE), HttpStatus.ACCEPTED);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/user/{email}")
    public @ResponseBody ResponseEntity<ResponseDto> deleteUserById(@PathVariable String email,
            HttpServletRequest request) {
        User user = userServiceImplement.findByEmail(email);
        if (Objects.isNull(user))
            return messageService.entityNotFound(request.getRequestURI());
        user.setRoles(null);
        userServiceImplement.save(user);
        userServiceImplement.delete(user);
        return new ResponseEntity<>(new ActionDto(ResponseType.ENTITY_DELETE), HttpStatus.ACCEPTED);
    }
}