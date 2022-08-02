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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.propets.apirest.main.models.dto.ResponseDto;
import com.propets.apirest.main.models.dto.response.ActionDto;
import com.propets.apirest.main.models.dto.response.SizeDto;
import com.propets.apirest.main.models.entity.atributos.Size;
import com.propets.apirest.main.models.enums.ResponseType;
import com.propets.apirest.main.services.messages.MessageServiceImplement;
import com.propets.apirest.main.services.size.SizeServiceImplement;

@RestController
@RequestMapping("/api")
public class SizeController {
    @Autowired
    private SizeServiceImplement sizeService;

    @Autowired
    private MessageServiceImplement messageService;

    @GetMapping("/size")
    public @ResponseBody ResponseEntity<List<SizeDto>> getRaces() {
        List<SizeDto> races = sizeService.findAll().stream().map(SizeDto::new).collect(Collectors.toList());
        return new ResponseEntity<>(races, HttpStatus.OK);
    }

    @GetMapping("/size/{id}")
    public @ResponseBody ResponseEntity<ResponseDto> getRaceById(@PathVariable("id") String id,
            HttpServletRequest request) {
        Long sizeId;
        try {
            sizeId = Long.parseLong(id);
        } catch (Exception e) {
            return messageService.entityNotFound(id, request.getRequestURI());
        }
        Size size = sizeService.findById(sizeId);
        if (Objects.isNull(size))
            return messageService.entityNotFound(id, request.getRequestURI());
        return new ResponseEntity<>(new SizeDto(size), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/size")
    public @ResponseBody ResponseEntity<ResponseDto> createRace(@Valid @ModelAttribute SizeDto sizeDto,
            BindingResult validationResult, HttpServletRequest request) {
        if (validationResult.hasErrors())
            return messageService.invalidFields(validationResult, request.getRequestURI());
        Size size = new Size(sizeDto);
        size.setEnabled(true);
        SizeDto response = new SizeDto(sizeService.save(size));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/size/{id}")
    public @ResponseBody ResponseEntity<ResponseDto> updateRace(@PathVariable("id") String id,
            @Valid @ModelAttribute SizeDto sizeDto,
            BindingResult validationResult, HttpServletRequest request) {
        Long sizeId;
        try {
            sizeId = Long.parseLong(id);
        } catch (Exception e) {
            return messageService.entityNotFound(id, request.getRequestURI());
        }
        if (validationResult.hasErrors())
            return messageService.invalidFields(validationResult, request.getRequestURI());
        Size size = sizeService.findById(sizeId);
        if (Objects.isNull(size))
            return messageService.entityNotFound(id, request.getRequestURI());
        size.setName(sizeDto.getName());
        size.setTitle(sizeDto.getTitle());
        SizeDto response = new SizeDto(sizeService.save(size));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/size/{id}")
    public @ResponseBody ResponseEntity<ResponseDto> deleteRace(@PathVariable("id") String id,
            HttpServletRequest request) {
        Long sizeId;
        try {
            sizeId = Long.parseLong(id);
        } catch (Exception e) {
            return messageService.entityNotFound(id, request.getRequestURI());
        }
        Size size = sizeService.findById(sizeId);
        if (Objects.isNull(size))
            return messageService.entityNotFound(id, request.getRequestURI());
        sizeService.disable(size);
        return new ResponseEntity<>(new ActionDto(ResponseType.ENTITY_DISABLE), HttpStatus.ACCEPTED);
    }
}
