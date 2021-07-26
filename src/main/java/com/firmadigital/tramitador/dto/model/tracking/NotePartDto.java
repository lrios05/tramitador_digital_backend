package com.firmadigital.tramitador.dto.model.tracking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.firmadigital.tramitador.dto.model.contract.ContractDto;
import com.firmadigital.tramitador.dto.model.contract.ContractPartDto;
import com.firmadigital.tramitador.dto.model.user.UserDto;
import com.firmadigital.tramitador.dto.model.user.UserRoleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class NotePartDto {

    private Long noteId;
    private Date createdAt;
    private ContractPartDto contractPartDto;
    private Long sequence;
    private int year;
    private String subject;
    private String user;
}
