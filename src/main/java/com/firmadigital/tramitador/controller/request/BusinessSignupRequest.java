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
public class BusinessSignupRequest {

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String nit;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String name;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String mobile;

    private String phone;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String email;

    private String website;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String address;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long typeId;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long activityId;
}
