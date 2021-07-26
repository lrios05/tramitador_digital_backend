package com.firmadigital.tramitador.dto.model.upload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.firmadigital.tramitador.dto.model.contract.ContractPartDto;
import com.firmadigital.tramitador.dto.model.user.UserRoleDto;
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
public class AttachePartDto {

    private Long attacheId;
    private ContractPartDto contractPartDto;
    private UserRoleDto userRoleDto;
    private String originUrl;
    private List<DocumentDto> documentDtoList = new ArrayList<>();
}
