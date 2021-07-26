package com.firmadigital.tramitador.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttacheRequest {

    private Long userId;
    private String contractCode;
    //private String originUrl;
    private String subject;
    private String priority;
    private int days;
    private Date deadLine;
    private Long fromUserId;
    private Long toUserId;
    private Long instructionId;
    private List<String> documents = new ArrayList<>();
}
