package com.firmadigital.tramitador.dto.model.auth;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@Accessors(chain = true)
public class JwtDto {

    private String token;
    private String bearer = "Bearer";
    private String userName;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, String userName, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.userName = userName;
        this.authorities = authorities;
    }
}
