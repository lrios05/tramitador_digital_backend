package com.firmadigital.tramitador.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractSignupRequest {

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Date initDate;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Date endDate;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long serviceId;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private double totalCost;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long pTypeId;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long pFrequencyId;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private int payments;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private double amount;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long gFrequencyId;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long wasteTypeId;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private double volume;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long unitId;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String days;
}
