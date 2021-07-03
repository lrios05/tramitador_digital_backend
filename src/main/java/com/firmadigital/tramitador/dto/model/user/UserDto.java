package com.firmadigital.tramitador.dto.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private String email;
    private String password;
    private String name;
    private String paternal;
    private String maternal;
    private boolean isAdmin;
    Collection<RoleDto> roles;

    public String getFullName() {
        return maternal != null ? name.concat(" ").concat(paternal).concat(maternal) : "";
    }

}
