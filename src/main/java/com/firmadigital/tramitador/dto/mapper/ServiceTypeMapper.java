package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.serviceoffer.ServiceTypeDto;
import com.firmadigital.tramitador.model.serviceoffer.ServiceType;
import org.springframework.stereotype.Component;

@Component
public class ServiceTypeMapper {

    public static ServiceTypeDto toServiceTypeDto(ServiceType serviceType){

        return new ServiceTypeDto()
                .setServiceTypeId(serviceType.getId())
                .setServiceType(serviceType.getServiceType())
                .setDescription(serviceType.getDescription());
    }
}
