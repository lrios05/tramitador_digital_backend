package com.firmadigital.tramitador.service.customer;

import com.firmadigital.tramitador.dto.mapper.BusinessMapper;
import com.firmadigital.tramitador.dto.mapper.ServiceOfferMapper;
import com.firmadigital.tramitador.dto.model.customer.BusinessDto;
import com.firmadigital.tramitador.dto.model.serviceoffer.ServiceOfferDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.customer.Activity;
import com.firmadigital.tramitador.model.customer.Business;
import com.firmadigital.tramitador.model.customer.BusinessType;
import com.firmadigital.tramitador.model.customer.Customer;
import com.firmadigital.tramitador.model.serviceoffer.ServiceOffer;
import com.firmadigital.tramitador.repository.customer.ActivityRepository;
import com.firmadigital.tramitador.repository.customer.BusinessRepository;
import com.firmadigital.tramitador.repository.customer.BusinessTypeRepository;
import com.firmadigital.tramitador.repository.customer.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.firmadigital.tramitador.exception.EntityType.*;
import static com.firmadigital.tramitador.exception.ExceptionType.*;

@Component
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BusinessTypeRepository typeRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BusinessDto findBusinessById(Long id) {
        Optional<Business> business = businessRepository.findById(id);

        if (business.isPresent()) {
            return modelMapper.map(business.get(), BusinessDto.class);
        }

        throw exception(BUSINESS, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public BusinessDto findBusinessByNit(String nit) {

        Business business = businessRepository.findBusinessByNit(nit);

        if (business != null) {
            return modelMapper.map(business, BusinessDto.class);
        }

        throw exception(BUSINESS, ENTITY_NOT_FOUND, "NIT: " + nit);
    }

    @Override
    public BusinessDto createBusiness(BusinessDto businessDto) {

        Business business = businessRepository.findBusinessByNit(businessDto.getNit());

        if (business == null) {
            Long customerId = businessDto.getCustomerDto().getCustomerId();
            Optional<Customer> customer = customerRepository.findById(customerId);

            if (customer.isPresent()) {

                Optional<BusinessType> businessType = typeRepository
                        .findById(businessDto.getBusinessTypeDto().getTypeId());

                Optional<Activity> activity = activityRepository
                        .findById(businessDto.getActivityDto().getActivityId());

                business = new Business()
                        .setCustomer(customer.get())
                        .setNit(businessDto.getNit())
                        .setName(businessDto.getName())
                        .setMobile(businessDto.getMobile())
                        .setPhone(businessDto.getPhone())
                        .setEmail(businessDto.getEmail())
                        .setWebsite(businessDto.getWebsite())
                        .setAddress(businessDto.getAddress())
                        .setBusinessType(businessType.get())
                        .setActivity(activity.get());

                return BusinessMapper.toBusinessDto(businessRepository.save(business));
            }
            throw exception(CUSTOMER, ENTITY_NOT_FOUND, "CustomerID: " + customerId);

        }

        throw exception(BUSINESS, DUPLICATE_ENTITY, "NIT: " + businessDto.getNit());
    }

    @Override
    public BusinessDto updateBusiness(BusinessDto businessDto) {
        return null;
    }

    @Override
    public Page<BusinessDto> findAllBusiness(Pageable pageable) {

        Page<Business> businessPage = businessRepository.findAll(pageable);

        if (businessPage.hasContent()) {
            Page<BusinessDto> businessDtos = businessPage.map(business -> {
                return BusinessMapper.toBusinessDto(business);
            });
            return businessDtos;
        }
        throw exception(BUSINESS, NO_DATA_FOUND, "No existen registros");
    }

    @Override
    public List<BusinessDto> findAllById(Long customerId, Pageable pageable) {
        return null;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
