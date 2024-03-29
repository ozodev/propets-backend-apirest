package com.propets.apirest.main.models.dao.atributos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.propets.apirest.main.models.entity.atributos.Race;

public interface IRaceDao extends JpaRepository<Race, Long>, JpaSpecificationExecutor<Race> {

    @Query("SELECT r FROM Race r WHERE r.name = :name")
    Optional<Race> findByName(@Param("name") String name);
}
