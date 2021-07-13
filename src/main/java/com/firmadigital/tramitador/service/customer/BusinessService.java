package com.firmadigital.tramitador.service.customer;

import com.firmadigital.tramitador.dto.model.customer.BusinessDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface BusinessService {

    BusinessDto findBusinessById(Long id);

    BusinessDto findBusinessByNit(String nit);

    BusinessDto createBusiness(BusinessDto businessDto);

    BusinessDto updateBusiness(BusinessDto businessDto);

    Page<BusinessDto> findAllBusiness(Pageable pageable);

    List<BusinessDto> findAllById(Long customerId, Pageable pageable);
}
