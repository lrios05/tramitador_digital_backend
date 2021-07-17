package com.firmadigital.tramitador.dto.model.tracking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.firmadigital.tramitador.dto.model.user.UserDto;
import com.firmadigital.tramitador.model.tracking.Instruction;
import com.firmadigital.tramitador.model.tracking.Note;
import com.firmadigital.tramitador.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailDto {

    private NoteDto noteDto;
    private String comment;
    private String priority;
    private UserDto fromUserDto;
    private UserDto toUserDto;
    private Date sendDate;
    private Date receiveDate;
    private String receivedStatus;
    private String sendStatus;
    private InstructionDto instructionDto;
}
