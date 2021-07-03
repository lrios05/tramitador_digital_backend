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
public class ServiceOfferRequest {

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String service;

    private String description;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long serviceTypeId;
}
