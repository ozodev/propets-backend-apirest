package com.propets.apirest.main.models.dao;

import com.propets.apirest.main.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

}
