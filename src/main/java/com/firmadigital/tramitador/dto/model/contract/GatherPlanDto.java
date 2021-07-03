package com.firmadigital.tramitador.dto.model.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GatherPlanDto {

    private ContractDto contractDto;
    private GatherFrequencyDto gatherFrequencyDto;
    private WasteTypeDto wasteTypeDto;
    private double volume;
    private UnitDto unitDto;
    private String days;
}
