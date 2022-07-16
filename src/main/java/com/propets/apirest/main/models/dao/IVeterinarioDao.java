package com.propets.apirest.main.models.dao;

import com.propets.apirest.main.models.entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IVeterinarioDao extends JpaRepository<Veterinario,String>, JpaSpecificationExecutor<Veterinario> {
}
