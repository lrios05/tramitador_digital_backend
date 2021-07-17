package com.firmadigital.tramitador.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.exception.DataException;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteRequest {

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Date deadLine;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String subject;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long userId;

}
