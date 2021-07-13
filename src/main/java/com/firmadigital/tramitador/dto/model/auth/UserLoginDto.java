package com.firmadigital.tramitador.dto.model.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
public class UserLoginDto {

    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
