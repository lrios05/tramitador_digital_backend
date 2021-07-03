package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.serviceoffer.ServiceOfferDto;
import com.firmadigital.tramitador.model.serviceoffer.ServiceOffer;

public class ServiceOfferMapper {

    public static ServiceOfferDto toServiceOfferDto(ServiceOffer serviceOffer) {

        return new ServiceOfferDto()
                .setServiceId(serviceOffer.getId())
                .setService(serviceOffer.getService())
                .setDescription(serviceOffer.getDescription())
                .setServiceTypeDto(ServiceTypeMapper.toServiceTypeDto(serviceOffer.getServiceType()));
    }
}
