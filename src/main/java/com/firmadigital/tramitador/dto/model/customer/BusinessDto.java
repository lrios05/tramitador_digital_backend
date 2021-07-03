package com.firmadigital.tramitador.dto.model.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.firmadigital.tramitador.model.customer.Activity;
import com.firmadigital.tramitador.model.customer.BusinessType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessDto {

    private CustomerDto customerDto;
    private String nit;
    private String name;
    private String mobile;
    private String phone;
    private String email;
    private String website;
    private String address;
    private BusinessTypeDto businessTypeDto;
    private ActivityDto activityDto;
}
