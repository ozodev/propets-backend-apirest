package com.propets.apirest.main.models.dao;

import com.propets.apirest.main.models.entity.CentroAtencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICentroAtencionDao extends JpaRepository<CentroAtencion,String>, JpaSpecificationExecutor<CentroAtencion> {
}
