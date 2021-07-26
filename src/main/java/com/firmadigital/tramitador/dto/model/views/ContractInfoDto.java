package com.firmadigital.tramitador.dto.model.views;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

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
public class ContractInfoDto {

    private Long contractId;
    private CustomerInfoDto customerInfo;
    private String contractCode;
    private Date initDate;
    private Date endDate;
    private String serviceType;
    private String service;
    private double totalCost;
    private String paymentType;
    private String paymentFrequency;
    private int payments;
    private double amount;
    private String gatherFrequency;
    private String wasteType;
    private double volume;
    private String unit;
    private String days;

}
