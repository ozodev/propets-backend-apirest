package com.propets.apirest.main.models.dao.atributos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.propets.apirest.main.models.entity.atributos.Race;

public interface IRaceDao extends JpaRepository<Race, Long>, JpaSpecificationExecutor<Race> {
}
