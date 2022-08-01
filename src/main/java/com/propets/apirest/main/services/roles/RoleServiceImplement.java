package com.propets.apirest.main.services.roles;

import com.propets.apirest.main.models.dao.atributos.IRoleDao;
import com.propets.apirest.main.models.entity.atributos.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImplement implements RoleService {
    @Autowired
    private IRoleDao roleDao;

    @Transactional(readOnly = true)
    public Role findRole(Long id) {
        return roleDao.findById(id).orElse(null);
    }
}
