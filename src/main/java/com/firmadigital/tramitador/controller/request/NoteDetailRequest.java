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
public class NoteDetailRequest {

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String subject;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long userId;
    private String comment;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String priority;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Date deadLine;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long fromUserId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long toUserId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long instructionId;
}
