package com.propets.apirest.main.models.dao;

import com.propets.apirest.main.models.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICitaDao extends JpaRepository<Cita,String>, JpaSpecificationExecutor<Cita> {

    @Query(value = "select * from citas as c where c.mascota_uuid= ?1",nativeQuery = true)
    Optional<Cita> findByMascota(String mascota);
}
