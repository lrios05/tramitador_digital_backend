package com.firmadigital.tramitador.service.serviceoffer;

import com.firmadigital.tramitador.dto.model.serviceoffer.ServiceOfferDto;
import com.firmadigital.tramitador.model.serviceoffer.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ServiceOfferService {

    ServiceOfferDto findById(Long id);

    List<ServiceOfferDto> findAllServiceOffer();

    ServiceOfferDto createServiceOffer(ServiceOfferDto serviceOfferDto);

    ServiceOfferDto updateServiceOffer(ServiceOfferDto serviceOfferDto);
}
