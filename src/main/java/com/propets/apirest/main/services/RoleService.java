package com.propets.apirest.main.services;

import com.propets.apirest.main.models.dao.IRoleDao;
import com.propets.apirest.main.models.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class RoleService {
    @Autowired
    private IRoleDao roleDao;
    @Transactional(readOnly = true)
    public Role findRole(Long id){return roleDao.findById(id).orElse(null);}
}
