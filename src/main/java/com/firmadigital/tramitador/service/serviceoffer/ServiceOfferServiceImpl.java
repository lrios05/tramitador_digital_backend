package com.firmadigital.tramitador.service.serviceoffer;

import com.firmadigital.tramitador.dto.mapper.ServiceOfferMapper;
import com.firmadigital.tramitador.dto.model.serviceoffer.ServiceOfferDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.serviceoffer.ServiceOffer;
import com.firmadigital.tramitador.model.serviceoffer.ServiceType;
import com.firmadigital.tramitador.repository.serviceoffer.ServiceOfferRepository;
import com.firmadigital.tramitador.repository.serviceoffer.ServiceTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.firmadigital.tramitador.exception.EntityType.SERVICE;
import static com.firmadigital.tramitador.exception.EntityType.SERVICE_TYPE;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class ServiceOfferServiceImpl implements ServiceOfferService{

    @Autowired
    ServiceOfferRepository serviceOfferRepository;

    @Autowired
    ServiceTypeRepository serviceTypeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ServiceOfferDto findById(Long id) {
        Optional<ServiceOffer> serviceOffer = serviceOfferRepository.findById(id);

        if (serviceOffer.isPresent()) {
            return modelMapper.map(serviceOffer.get(), ServiceOfferDto.class);
        }

        throw exception(SERVICE, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public List<ServiceOfferDto> findServiceOfferByServiceTypeId(Long id) {
        return serviceOfferRepository.findServiceOfferByServiceTypeId(id).stream().map(serviceOffer -> {
            return ServiceOfferMapper.toServiceOfferDto(serviceOffer);
        }).collect(Collectors.toList());
    }


    @Override
    public List<ServiceOfferDto> findAllServiceOffer() {
        return serviceOfferRepository.findAll().stream().map(serviceOffer -> {
            return ServiceOfferMapper.toServiceOfferDto(serviceOffer);
        }).collect(Collectors.toList());
    }

    @Override
    public ServiceOfferDto createServiceOffer(ServiceOfferDto serviceOfferDto) {
        Long serviceTypeId = serviceOfferDto.getServiceTypeDto().getServiceTypeId();
        Optional<ServiceType> serviceType = serviceTypeRepository.findById(serviceTypeId);

        if (serviceType.isPresent()) {
            ServiceOffer serviceOffer = new ServiceOffer()
                    .setService(serviceOfferDto.getService())
                    .setDescription(serviceOfferDto.getDescription())
                    .setServiceType(serviceType.get());

            return ServiceOfferMapper.toServiceOfferDto(serviceOfferRepository.save(serviceOffer));
        }

        throw exception(SERVICE_TYPE, ENTITY_NOT_FOUND, "Service type: " + serviceTypeId);
    }

    @Override
    public ServiceOfferDto updateServiceOffer(ServiceOfferDto serviceOfferDto) {
        return null;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
