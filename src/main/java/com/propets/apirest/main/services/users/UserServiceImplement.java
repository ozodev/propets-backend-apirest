package com.propets.apirest.main.services.users;

import com.propets.apirest.main.models.dao.IUserDao;
import com.propets.apirest.main.models.dto.UserDto;
import com.propets.apirest.main.models.entity.User;
import com.propets.apirest.main.services.roles.RoleServiceImplement;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImplement implements UserDetailsService, UserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private RoleServiceImplement roleService;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userDao.findById(email).orElse(null);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id).orElse(null);
    }

    @Transactional
    public User save(User usuario) {
        return userDao.save(usuario);
    }

    @Transactional
    public void delete(User usuario) {
        userDao.delete(usuario);
    }

    @Transactional
    public void delete(String id) {
        userDao.delete(findByEmail(id));
    }

    @Transactional(readOnly = true)
    public Boolean exist(String email) {
        return Objects.nonNull(findByEmail(email));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findById(username);
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                user.getEnabled(), true, true, true, authorities);
    }

    @Transactional(readOnly = true)
    public User findByToken(String authorization) {
        String token = authorization.split(" ")[1];
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        JSONObject data = new JSONObject(payload);
        String username = data.getString("user_name");
        return findByEmail(username);
    }

    @Override
    public Boolean isAdmin(User user) {
        return user.getRoles().contains(roleService.findRole(1L));
    }

    @Override
    public UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setLastname(user.getLastname());
        userDto.setPhone(user.getPhone());
        userDto.setEnabled(user.getEnabled());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}