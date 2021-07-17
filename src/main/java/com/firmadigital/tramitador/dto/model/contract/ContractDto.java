package com.firmadigital.tramitador.dto.model.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.firmadigital.tramitador.dto.model.customer.CustomerDto;
import com.firmadigital.tramitador.dto.model.serviceoffer.ServiceOfferDto;
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
public class ContractDto {

    private Long contractId;
    private CustomerDto customerDto;
    private String contractCode;
    private Date initDate;
    private Date endDate;
    private ServiceOfferDto serviceOfferDto;
    private double totalCost;
    private PaymentTypeDto paymentTypeDto;
    private PaymentFrequencyDto paymentFrequencyDto;
    private int payments;
    private double amount;
    private GatherFrequencyDto gatherFrequencyDto;
    private WasteTypeDto wasteTypeDto;
    private double volume;
    private UnitDto unitDto;
    private String days;
    private String status;
}
