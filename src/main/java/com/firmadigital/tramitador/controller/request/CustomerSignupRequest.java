package com.firmadigital.tramitador.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerSignupRequest {

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String dni;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String name;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String paternal;

    private String maternal;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String phone;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String mobile;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String email;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String address;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long userId;
}
