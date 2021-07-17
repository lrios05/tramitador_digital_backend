package com.firmadigital.tramitador.repository.customer;

import com.firmadigital.tramitador.model.customer.Business;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessRepository extends CrudRepository<Business, Long> {

    Optional<Business> findById(Long id);
    @Query("SELECT b FROM Business b WHERE b.customer.id = ?1")
    Optional<Business> findByCustomerId(Long id);
    Business findBusinessByNit(String nit);
    Page<Business> findAll(Pageable pageable);
    List<Business> findAllById(Long customerId, Pageable pageable);
}
