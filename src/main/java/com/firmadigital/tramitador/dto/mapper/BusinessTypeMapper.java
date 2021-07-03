package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.customer.BusinessTypeDto;
import com.firmadigital.tramitador.model.customer.BusinessType;
import org.springframework.stereotype.Component;

@Component
public class BusinessTypeMapper {

    public static BusinessTypeDto toBusinessTypeDto(BusinessType businessType) {

        return new BusinessTypeDto()
                .setTypeId(businessType.getId())
                .setBusinessType(businessType.getBusinessType())
                .setDescription(businessType.getDescription());
    }
}
