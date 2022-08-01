package com.propets.apirest.main.controllers.atributos;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.propets.apirest.main.models.dto.ResponseDto;
import com.propets.apirest.main.models.dto.response.ActionDto;
import com.propets.apirest.main.models.dto.response.ColorDto;
import com.propets.apirest.main.models.entity.atributos.Color;
import com.propets.apirest.main.models.enums.ResponseType;
import com.propets.apirest.main.services.colors.ColorServiceImplement;
import com.propets.apirest.main.services.messages.MessageServiceImplement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class ColorController {

    @Autowired
    private ColorServiceImplement colorService;

    @Autowired
    private MessageServiceImplement messageService;

    @GetMapping("/color")
    public @ResponseBody ResponseEntity<List<ColorDto>> getColors() {
        List<ColorDto> races = colorService.findAll().stream().map(ColorDto::new).collect(Collectors.toList());
        return new ResponseEntity<>(races, HttpStatus.OK);
    }

    @GetMapping("/color/{id}")
    public @ResponseBody ResponseEntity<ResponseDto> getColorById(@PathVariable("id") String id,
            HttpServletRequest request) {
        Long colorId;
        try {
            colorId = Long.parseLong(id);
        } catch (Exception e) {
            return messageService.entityNotFound(id, request.getRequestURI());
        }
        Color color = colorService.findById(colorId);
        if (Objects.isNull(color))
            return messageService.entityNotFound(id, request.getRequestURI());
        return new ResponseEntity<>(new ColorDto(color), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/color")
    public @ResponseBody ResponseEntity<ResponseDto> createColor(@Valid @ModelAttribute ColorDto colorDto,
            BindingResult validationResult, HttpServletRequest request) {
        if (validationResult.hasErrors())
            return messageService.invalidFields(validationResult, request.getRequestURI());
        Color color = new Color(colorDto);
        color.setEnabled(true);
        ColorDto response = new ColorDto(colorService.save(color));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/color/{id}")
    public @ResponseBody ResponseEntity<ResponseDto> updateColor(@PathVariable("id") String id,
            @Valid @ModelAttribute ColorDto colorDto,
            BindingResult validationResult, HttpServletRequest request) {
        Long colorId;
        try {
            colorId = Long.parseLong(id);
        } catch (Exception e) {
            return messageService.entityNotFound(id, request.getRequestURI());
        }
        if (validationResult.hasErrors())
            return messageService.invalidFields(validationResult, request.getRequestURI());
        Color color = colorService.findById(colorId);
        if (Objects.isNull(color))
            return messageService.entityNotFound(id, request.getRequestURI());
        color.setName(colorDto.getName());
        color.setTitle(colorDto.getTitle());
        ColorDto response = new ColorDto(colorService.save(color));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/color/{id}")
    public @ResponseBody ResponseEntity<ResponseDto> deleteRace(@PathVariable("id") String id,
            HttpServletRequest request) {
        Long colorId;
        try {
            colorId = Long.parseLong(id);
        } catch (Exception e) {
            return messageService.entityNotFound(id, request.getRequestURI());
        }
        Color color = colorService.findById(colorId);
        if (Objects.isNull(color))
            return messageService.entityNotFound(id, request.getRequestURI());
        colorService.disable(color);
        return new ResponseEntity<>(new ActionDto(ResponseType.ENTITY_DISABLE), HttpStatus.ACCEPTED);
    }

}