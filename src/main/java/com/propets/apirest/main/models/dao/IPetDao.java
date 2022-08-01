package com.propets.apirest.main.models.dao;

import com.propets.apirest.main.models.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPetDao extends JpaRepository<Pet,String>, JpaSpecificationExecutor<Pet> {

    @Query(value = "select p from Pet p where p.user.email = :email")
    public Page<Pet> findAllByEmail(@Param("email") String email, Pageable pageable);
}
