package com.firmadigital.tramitador.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firmadigital.tramitador.dto.model.tracking.InstructionDto;
import com.firmadigital.tramitador.dto.model.tracking.NoteDto;
import com.firmadigital.tramitador.dto.model.user.UserDto;
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
public class DetailRequest {

    private String comment;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String priority;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long fromUserId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long toUserId;
    private Date sendDate;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long instructionId;
}
