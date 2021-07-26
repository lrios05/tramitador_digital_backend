package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.customer.BusinessResponseDto;
import com.firmadigital.tramitador.model.customer.Business;
import org.springframework.stereotype.Component;

@Component
public class BusinessResponseMapper {

    public static BusinessResponseDto toBusinessResponseDto(Business business) {
        return new BusinessResponseDto()
                .setNit(business.getNit())
                .setName(business.getName())
                .setMobile(business.getMobile())
                .setPhone(business.getPhone())
                .setEmail(business.getEmail())
                .setAddress(business.getAddress());
    }
}
