package com.propets.apirest.main.services.users;

import com.propets.apirest.main.models.dto.UserDto;
import com.propets.apirest.main.models.entity.User;
import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByEmail(String email);

    User findById(String id);

    User save(User user);

    void delete(User user);

    void delete(String id);

    Boolean exist(String email);

    User findByToken(String authorization);

    Boolean isAdmin(User user);

    UserDto getUserDto(User user);
}
