package com.firmadigital.tramitador.dto.mapper.views;

import com.firmadigital.tramitador.dto.model.views.BusinessInfoDto;
import com.firmadigital.tramitador.model.customer.Business;
import org.springframework.stereotype.Component;

@Component
public class BusinessInfoMapper {

    public static BusinessInfoDto toBusinessInfoDto(Business business) {

        return new BusinessInfoDto()
                .setCustomerInfoDto(CustomerInfoMapper.toCustomerInfoDto(business.getCustomer()))
                .setNit(business.getNit())
                .setName(business.getName())
                .setPhone(business.getPhone())
                .setAddress(business.getAddress())
                .setBusinessType(business.getBusinessType().getBusinessType())
                .setActivity(business.getActivity().getActivity());
    }
}
