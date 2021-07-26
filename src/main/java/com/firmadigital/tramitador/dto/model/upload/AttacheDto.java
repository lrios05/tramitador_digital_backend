package com.firmadigital.tramitador.dto.model.upload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.firmadigital.tramitador.dto.model.contract.ContractDto;
import com.firmadigital.tramitador.dto.model.contract.ContractPartDto;
import com.firmadigital.tramitador.dto.model.user.UserDto;
import com.firmadigital.tramitador.dto.model.user.UserRoleDto;
import com.firmadigital.tramitador.model.contract.Contract;
import com.firmadigital.tramitador.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttacheDto {

    private Long attacheId;
    private ContractDto contractDto;
    private UserDto userDto;
    private String originUrl;
    private List<DocumentDto> documentDtoList = new ArrayList<>();
}
