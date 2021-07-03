package com.firmadigital.tramitador.service.serviceoffer;

import com.firmadigital.tramitador.dto.model.serviceoffer.ServiceTypeDto;

import java.util.List;

public interface ServiceTypeService {

    ServiceTypeDto findServiceTypeById(Long id);

    List<ServiceTypeDto> findAllServiceTypes();

    ServiceTypeDto createServiceType(ServiceTypeDto serviceTypeDto);

    ServiceTypeDto updateServiceType(ServiceTypeDto serviceTypeDto);

}
