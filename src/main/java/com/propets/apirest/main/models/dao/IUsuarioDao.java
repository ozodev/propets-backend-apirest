package com.propets.apirest.main.models.dao;

import com.propets.apirest.main.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IUsuarioDao extends JpaRepository<Usuario,String>, JpaSpecificationExecutor<Usuario> {

}
