package com.firmadigital.tramitador.service.serviceoffer;

import com.firmadigital.tramitador.dto.mapper.ServiceTypeMapper;
import com.firmadigital.tramitador.dto.model.serviceoffer.ServiceTypeDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.serviceoffer.ServiceType;
import com.firmadigital.tramitador.repository.serviceoffer.ServiceTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.firmadigital.tramitador.exception.EntityType.SERVICE_TYPE;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class ServiceTypeServiceImpl implements ServiceTypeService{

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ServiceTypeDto findServiceTypeById(Long id) {
        Optional<ServiceType> serviceType = serviceTypeRepository.findById(id);

        if (serviceType.isPresent()) {
            return modelMapper.map(serviceType.get(), ServiceTypeDto.class);
        }
        throw exception(SERVICE_TYPE, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public List<ServiceTypeDto> findAllServiceTypes() {
        return serviceTypeRepository.findAll().stream().map(serviceType -> {
            return ServiceTypeMapper.toServiceTypeDto(serviceType);
        }).collect(Collectors.toList());
    }

    @Override
    public ServiceTypeDto createServiceType(ServiceTypeDto serviceTypeDto) {

        ServiceType serviceType = new ServiceType()
                .setServiceType(serviceTypeDto.getServiceType())
                .setDescription(serviceTypeDto.getDescription());

        return ServiceTypeMapper.toServiceTypeDto(serviceTypeRepository.save(serviceType));
    }

    @Override
    public ServiceTypeDto updateServiceType(ServiceTypeDto serviceTypeDto) {
        return null;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
