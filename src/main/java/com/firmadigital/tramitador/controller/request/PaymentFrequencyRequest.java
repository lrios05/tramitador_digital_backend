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
public class PaymentFrequencyRequest {

    private Long paymentId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String frequency;
}
