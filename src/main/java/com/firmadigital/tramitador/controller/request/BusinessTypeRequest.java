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
public class BusinessTypeRequest {

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long typeId;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String businessType;

    private String description;
}
