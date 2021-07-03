package com.firmadigital.tramitador.service.customer;

import com.firmadigital.tramitador.dto.model.customer.BusinessTypeDto;

import java.util.List;

public interface BusinessTypeService {

    BusinessTypeDto findById(Long typeId);

    BusinessTypeDto createBusinessType(BusinessTypeDto businessTypeDto);

    BusinessTypeDto updateBusinessType(BusinessTypeDto businessTypeDto);

    List<BusinessTypeDto> listAllBusinessTypes();
}
