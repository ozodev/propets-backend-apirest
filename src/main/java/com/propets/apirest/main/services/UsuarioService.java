package com.propets.apirest.main.services;

import com.propets.apirest.main.models.dao.IUsuarioDao;
import com.propets.apirest.main.models.entity.Usuario;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
public class UsuarioService implements UserDetailsService {
    @Autowired
    private IUsuarioDao usuarioDao;
    @Transactional(readOnly = true)
    public List<Usuario> findAll(){
        return (List<Usuario>) usuarioDao.findAll();
    }
    @Transactional(readOnly = true)
    public Usuario findByEmail(String email){
        return usuarioDao.findById(email).orElse(null);
    }
    @Transactional
    public Usuario save(Usuario usuario){return usuarioDao.save(usuario);}
    @Transactional
    public void delete(Usuario usuario){usuarioDao.delete(usuario);}
    @Transactional
    public void delete(String id){usuarioDao.delete(findByEmail(id));}
    @Transactional(readOnly = true)
    public Boolean exist(String email){return !Objects.isNull(findByEmail(email));}
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = findByEmail(username);
        List<GrantedAuthority> authorities = user.getRoles().stream().map( role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
        return new User(user.getEmail(),user.getPassword(),user.getEnabled(),true,true,true,authorities);
    }
    @Transactional(readOnly = true)
    public Usuario findByToken(String authorization){
        String token = authorization.split(" ")[1];
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        JSONObject data = new JSONObject(payload);
        String username = data.getString("user_name");
        return findByEmail(username);
    }
}