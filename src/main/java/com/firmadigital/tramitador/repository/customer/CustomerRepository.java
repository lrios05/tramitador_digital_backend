package com.firmadigital.tramitador.repository.customer;

import com.firmadigital.tramitador.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Override
    Optional<Customer> findById(Long id);

    Customer findCustomerByDni(String dni);

    Customer findCustomerByEmail(String email);

    Page<Customer> findAll(Pageable pageable);
}
