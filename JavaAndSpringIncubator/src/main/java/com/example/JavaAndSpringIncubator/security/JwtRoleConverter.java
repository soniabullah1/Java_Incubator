package com.example.JavaAndSpringIncubator.security;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class JwtRoleConverter implements Converter<Jwt, Collection> {

    @Override
    public Collection convert(Jwt jwt){
        String roles = (String) jwt.getClaims().get("roles");
        if (roles == null || roles.isEmpty()) {
            return new ArrayList<>();
        }
        return List.of(new SimpleGrantedAuthority(roles));
    }
}
