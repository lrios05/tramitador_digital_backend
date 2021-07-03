package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.customer.CustomerDto;
import com.firmadigital.tramitador.model.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public static CustomerDto toCustomerDto(Customer customer){

        return new CustomerDto()
                .setCustomerID(customer.getId())
                .setCustomerCode(customer.getCustomerCode())
                .setDni(customer.getDni())
                .setName(customer.getName())
                .setPaternal(customer.getPaternal())
                .setMaternal(customer.getMaternal())
                .setMobile(customer.getMobile())
                .setPhone(customer.getPhone())
                .setEmail(customer.getEmail())
                .setAddress(customer.getAddress())
                .setUserDto(UserMapper.toUserDto(customer.getUser()));
    }
}
