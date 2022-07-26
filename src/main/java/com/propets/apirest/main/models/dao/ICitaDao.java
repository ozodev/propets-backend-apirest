package com.propets.apirest.main.models.dao;

import com.propets.apirest.main.models.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICitaDao extends JpaRepository<Cita,String>, JpaSpecificationExecutor<Cita> {

    @Query(value = "select * from citas as c where c.veterinario_uuid= ?1",nativeQuery = true)
    List<Cita> findByVeterinario(String veterinario);

    @Query(value = "select * from citas as c where c.mascota_uuid = ?1 and c.cita_dia= ?2 and c.cita_mes = ?3 and c.cita_year = ?4 and c.cita_franja = ?5 and c.cita_status = ?6",nativeQuery = true)
    Optional<Cita> findByDataAndStatus(String mascota, int dia, int mes, int year, int franja, String status);

    @Query(value = "select * from citas as c where c.usuario_email = ?1 ",nativeQuery = true)
    List<Cita> findAllByUsuario(String usuario);
}
