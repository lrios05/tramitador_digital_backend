package com.firmadigital.tramitador.service.customer;

import com.firmadigital.tramitador.dto.mapper.BusinessTypeMapper;
import com.firmadigital.tramitador.dto.model.customer.BusinessTypeDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.customer.BusinessType;
import com.firmadigital.tramitador.repository.customer.BusinessTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.firmadigital.tramitador.exception.EntityType.BUSINESS_TYPE;
import static com.firmadigital.tramitador.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class BusinessTypeServiceImpl implements BusinessTypeService{

    @Autowired
    private BusinessTypeRepository typeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BusinessTypeDto findById(Long typeId) {
        Optional<BusinessType> businessType = typeRepository.findById(typeId);

        if (businessType.isPresent()) {
            return modelMapper.map(businessType.get(), BusinessTypeDto.class);
        }

        throw exception(BUSINESS_TYPE, ENTITY_NOT_FOUND, typeId.toString());
    }

    @Override
    public BusinessTypeDto createBusinessType(BusinessTypeDto businessTypeDto) {

        BusinessType businessType = typeRepository.
                findBusinessTypeByBusinessType(businessTypeDto.getBusinessType());

        if (businessType == null) {
            businessType = new BusinessType()
                    .setBusinessType(businessTypeDto.getBusinessType())
                    .setDescription(businessTypeDto.getDescription());

            return BusinessTypeMapper.toBusinessTypeDto(typeRepository.save(businessType));
        }

        throw exception(BUSINESS_TYPE, DUPLICATE_ENTITY, businessType.getBusinessType());
    }

    @Override
    public BusinessTypeDto updateBusinessType(BusinessTypeDto businessTypeDto) {
        return null;
    }

    @Override
    public List<BusinessTypeDto> listAllBusinessTypes() {

        List<BusinessTypeDto> businessTypeDtoList = new ArrayList<>();
         typeRepository.findAll()
                 .forEach(businessType -> {
                    businessTypeDtoList.add(BusinessTypeMapper.toBusinessTypeDto(businessType));
                 });

        return businessTypeDtoList;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
