package com.firmadigital.tramitador.dto.mapper.views;

import com.firmadigital.tramitador.dto.model.views.CustomerInfoDto;
import com.firmadigital.tramitador.model.customer.Customer;
import com.firmadigital.tramitador.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class CustomerInfoMapper {

    public static CustomerInfoDto toCustomerInfoDto(Customer customer){

        return new CustomerInfoDto()
                .setCustomerId(customer.getId())
                .setCustomerCode(customer.getCustomerCode())
                .setDni(customer.getDni())
                .setCustomerName(getCustomerName(customer))
                .setMobile(customer.getMobile())
                .setEmail(customer.getEmail())
                .setAddress(customer.getAddress())
                .setUser(getUserName(customer.getUser()));
    }

    private static String getUserName(User user) {
        return user.getMaternal() != null ? user.getName().concat(" ").concat(user.getPaternal()).concat(" ").concat(user.getMaternal()) : "";
    }

    private static String getCustomerName(Customer customer) {
        return customer.getMaternal() != null ? customer.getName().concat(" ").concat(customer.getPaternal()).concat(" ").concat(customer.getMaternal()) : "";
    }
}
