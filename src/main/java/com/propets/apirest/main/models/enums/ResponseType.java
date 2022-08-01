package com.propets.apirest.main.models.enums;

import lombok.Getter;

public enum ResponseType {
    ENTITY_NOT_FOUND("Entidad no Encontrada"),
    ENTITY_CREATED("Entidad Creada"),
    ENTITY_EXIST("Entidad Existe"),
    INVALID_FIELDS("Campos Invalidos"),
    ENTITY_UPDATE("Entidad Actualizada"),
    ENTITY_DELETE("Entidad Eliminada"),
    ENTITY_DISABLE("Entidad Deshabilitada");

    @Getter
    private String message;

    ResponseType(String message) {
        this.message = message;
    }
}
