package com.propets.apirest.main.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/api/centro_atencion",
                        "/api/veterinario",
                        "/api/pet/**",
                        "/api/race",
                        "/api/race/**",
                        "/api/color",
                        "/api/color/**",
                        "/api/size",
                        "/api/size/**",
                        "/api/cita/tipo",
                        "/api/cita/franja")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                .anyRequest().authenticated();
    }
}