package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.customer.BusinessDto;
import com.firmadigital.tramitador.model.customer.Business;
import org.springframework.stereotype.Component;

@Component
public class BusinessMapper {

    public static BusinessDto toBusinessDto(Business business) {

        return new BusinessDto()
                .setCustomerDto(CustomerMapper.toCustomerDto(business.getCustomer()))
                .setNit(business.getNit())
                .setName(business.getName())
                .setMobile(business.getMobile())
                .setPhone(business.getPhone())
                .setEmail(business.getEmail())
                .setWebsite(business.getWebsite())
                .setAddress(business.getAddress())
                .setBusinessTypeDto(BusinessTypeMapper.toBusinessTypeDto(business.getBusinessType()))
                .setActivityDto(ActivityMapper.toActivityDto(business.getActivity()));
    }
}
