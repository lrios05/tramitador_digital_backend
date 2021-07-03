package com.firmadigital.tramitador.service.customer;

import com.firmadigital.tramitador.dto.model.customer.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface CustomerService {

    CustomerDto findById(Long customerId);

    CustomerDto findCustomerByDni(String dni);

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(CustomerDto customerDto);

    Page<CustomerDto> findAllCustomers(Pageable pageable);
}
