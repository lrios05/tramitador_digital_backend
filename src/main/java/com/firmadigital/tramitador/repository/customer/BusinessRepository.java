package com.firmadigital.tramitador.repository.customer;

import com.firmadigital.tramitador.model.customer.Business;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository extends CrudRepository<Business, Long> {

    Business findBusinessByNit(String nit);

    Page<Business> findAll(Pageable pageable);

    List<Business> findAllById(Long customerId, Pageable pageable);

}
