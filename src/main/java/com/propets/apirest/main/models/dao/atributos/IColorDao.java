package com.propets.apirest.main.models.dao.atributos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.propets.apirest.main.models.entity.atributos.Color;

public interface IColorDao extends JpaRepository<Color, Long>, JpaSpecificationExecutor<Color> {

    @Query("SELECT c FROM Color c WHERE c.name = :name")
    Optional<Color> findByName(@Param("name") String name);
}