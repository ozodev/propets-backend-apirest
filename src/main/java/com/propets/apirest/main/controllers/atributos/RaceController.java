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
import com.propets.apirest.main.models.dto.response.RaceDto;
import com.propets.apirest.main.models.entity.atributos.Race;
import com.propets.apirest.main.models.enums.ResponseType;
import com.propets.apirest.main.services.messages.MessageServiceImplement;
import com.propets.apirest.main.services.races.RaceServiceImplement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class RaceController {

    @Autowired
    private RaceServiceImplement raceService;

    @Autowired
    private MessageServiceImplement messageService;

    @GetMapping("/race")
    public @ResponseBody ResponseEntity<List<RaceDto>> getRaces() {
        List<RaceDto> races = raceService.findAll().stream().map(RaceDto::new).collect(Collectors.toList());
        return new ResponseEntity<>(races, HttpStatus.OK);
    }

    @GetMapping("/race/{id}")
    public @ResponseBody ResponseEntity<ResponseDto> getRaceById(@PathVariable("id") String id,
            HttpServletRequest request) {
        Long raceId;
        try {
            raceId = Long.parseLong(id);
        } catch (Exception e) {
            return messageService.entityNotFound(id, request.getRequestURI());
        }
        Race race = raceService.findById(raceId);
        if (Objects.isNull(race))
            return messageService.entityNotFound(id, request.getRequestURI());
        return new ResponseEntity<>(new RaceDto(race), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/race")
    public @ResponseBody ResponseEntity<ResponseDto> createRace(@Valid @ModelAttribute RaceDto raceDto,
            BindingResult validationResult, HttpServletRequest request) {
        if (validationResult.hasErrors())
            return messageService.invalidFields(validationResult, request.getRequestURI());
        Race race = new Race(raceDto);
        race.setEnabled(true);
        RaceDto response = new RaceDto(raceService.save(race));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/race/{id}")
    public @ResponseBody ResponseEntity<ResponseDto> updateRace(@PathVariable("id") String id,
            @Valid @ModelAttribute RaceDto raceDto,
            BindingResult validationResult, HttpServletRequest request) {
        Long raceId;
        try {
            raceId = Long.parseLong(id);
        } catch (Exception e) {
            return messageService.entityNotFound(id, request.getRequestURI());
        }
        if (validationResult.hasErrors())
            return messageService.invalidFields(validationResult, request.getRequestURI());
        Race race = raceService.findById(raceId);
        if (Objects.isNull(race))
            return messageService.entityNotFound(id, request.getRequestURI());
        race.setName(raceDto.getName());
        race.setTitle(raceDto.getTitle());
        RaceDto response = new RaceDto(raceService.save(race));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/race/{id}")
    public @ResponseBody ResponseEntity<ResponseDto> deleteRace(@PathVariable("id") String id,
            HttpServletRequest request) {
        Long raceId;
        try {
            raceId = Long.parseLong(id);
        } catch (Exception e) {
            return messageService.entityNotFound(id, request.getRequestURI());
        }
        Race race = raceService.findById(raceId);
        if (Objects.isNull(race))
            return messageService.entityNotFound(id, request.getRequestURI());
        raceService.disable(race);
        return new ResponseEntity<>(new ActionDto(ResponseType.ENTITY_DISABLE), HttpStatus.ACCEPTED);
    }
}