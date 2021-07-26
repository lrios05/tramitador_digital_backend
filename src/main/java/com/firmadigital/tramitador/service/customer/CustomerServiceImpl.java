package com.firmadigital.tramitador.service.customer;

import com.firmadigital.tramitador.dto.mapper.BusinessMapper;
import com.firmadigital.tramitador.dto.mapper.CustomerMapper;
import com.firmadigital.tramitador.dto.mapper.views.CustomerInfoMapper;
import com.firmadigital.tramitador.dto.model.customer.BusinessDto;
import com.firmadigital.tramitador.dto.model.customer.CustomerDto;
import com.firmadigital.tramitador.dto.model.views.CustomerInfoDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.customer.Customer;
import com.firmadigital.tramitador.model.user.User;
import com.firmadigital.tramitador.repository.customer.CustomerRepository;
import com.firmadigital.tramitador.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.firmadigital.tramitador.exception.EntityType.CUSTOMER;
import static com.firmadigital.tramitador.exception.ExceptionType.*;

@Component
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomerDto findById(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isPresent()) {
            return modelMapper.map(customer.get(), CustomerDto.class);
        }

        throw exception(CUSTOMER, ENTITY_NOT_FOUND, customerId.toString());
    }

    @Override
    public CustomerInfoDto findCustomerInfoById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {
            return CustomerInfoMapper.toCustomerInfoDto(customer.get());
        }

        throw exception(CUSTOMER, ENTITY_NOT_FOUND, id.toString());
    }

    @Transactional
    public CustomerDto findCustomerByDni(String dni) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findCustomerByDni(dni));

        if (customer.isPresent()) {
            return modelMapper.map(customer.get(), CustomerDto.class);
        }

        throw exception(CUSTOMER, ENTITY_NOT_FOUND, dni);
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {

        Customer customer = customerRepository.findCustomerByDni(customerDto.getDni());

        if (customer == null) {
            User user = userRepository.findByEmail(customerDto.getUserDto().getEmail());

            customer = new Customer()
                    .setCustomerCode(createCustomerCode())
                    .setDni(customerDto.getDni())
                    .setName(customerDto.getName())
                    .setPaternal(customerDto.getPaternal())
                    .setMaternal(customerDto.getMaternal())
                    .setMobile(customerDto.getMobile())
                    .setPhone(customerDto.getPhone())
                    .setEmail(customerDto.getEmail())
                    .setAddress(customerDto.getAddress())
                    .setUser(user);

            return CustomerMapper.toCustomerDto(customerRepository.save(customer));
        }

        throw exception(CUSTOMER, DUPLICATE_ENTITY, customerDto.getDni());
    }

    private String createCustomerCode(){
        Long quantity = customerRepository.count() + 1;
        int year = YearMonth.now().getYear();
        String newCode = quantity.toString() + "-" + year;

        return newCode;
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findCustomerByDni(customerDto.getDni()));

        if (customer.isPresent()) {
            Customer customerUpdate = new Customer()
                    .setDni(customerDto.getDni())
                    .setName(customerDto.getName())
                    .setPaternal(customerDto.getPaternal())
                    .setMaternal(customerDto.getMaternal())
                    .setMobile(customerDto.getMobile())
                    .setPhone(customerDto.getPhone())
                    .setEmail(customerDto.getEmail())
                    .setAddress(customerDto.getAddress());

            return CustomerMapper.toCustomerDto(customerRepository.save(customerUpdate));
        }

        throw exception(CUSTOMER, ENTITY_NOT_FOUND, customerDto.getDni());
    }

    @Override
    public Page<CustomerDto> findAllCustomers(Pageable pageable) {
        Page<Customer> customerPage = customerRepository.findAll(pageable);

        if (customerPage.hasContent()) {
            Page<CustomerDto> customerDtoPage = customerPage.map(customer -> {
                return CustomerMapper.toCustomerDto(customer);
            });

            return customerDtoPage;
        }
        throw exception(CUSTOMER, NO_DATA_FOUND, "No existen registros");
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
