package com.propets.apirest.main.models.dao;

import com.propets.apirest.main.models.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IMascotaDao extends JpaRepository<Mascota,String>, JpaSpecificationExecutor<Mascota> {
}
