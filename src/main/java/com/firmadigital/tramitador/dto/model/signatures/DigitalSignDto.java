package com.firmadigital.tramitador.dto.model.signatures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.firmadigital.tramitador.dto.model.contract.ContractDto;
import com.firmadigital.tramitador.dto.model.user.UserDto;
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
public class DigitalSignDto {

    private Long signId;
    private ContractDto contractDto;
    private UserDto userDto;
    private String document;
}
