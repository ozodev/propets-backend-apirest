package com.propets.apirest.main.models.dao.atributos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.propets.apirest.main.models.entity.atributos.Size;

public interface ISizeDao extends JpaRepository<Size, Long>, JpaSpecificationExecutor<Size> {
    @Query("SELECT s FROM Size s WHERE s.name = :name")
    Optional<Size> findByName(@Param("name") String name);
}
