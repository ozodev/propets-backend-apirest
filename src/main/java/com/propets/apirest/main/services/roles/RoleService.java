package com.propets.apirest.main.services.roles;

import com.propets.apirest.main.models.entity.User;
import com.propets.apirest.main.models.entity.atributos.Role;

public interface RoleService {
    Role findRole(Long id);

    Boolean isAdmin(User user);
}
