package com.firmadigital.tramitador.dto.model.tracking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailPartDto {

    private Long noteId;
    private String comment;
    private String priority;
    private UserRoleDto fromUserDto;
    private UserRoleDto toUserDto;
    private Date sendDate;
    private Date receiveDate;
    private String receivedStatus;
    private String sendStatus;
    private String instruction;
}
