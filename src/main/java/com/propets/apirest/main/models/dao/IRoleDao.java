package com.propets.apirest.main.models.dao;

import com.propets.apirest.main.models.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IRoleDao extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role> {
}
