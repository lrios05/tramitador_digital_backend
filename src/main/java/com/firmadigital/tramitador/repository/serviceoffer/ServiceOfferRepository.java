package com.firmadigital.tramitador.repository.serviceoffer;

import com.firmadigital.tramitador.model.serviceoffer.ServiceOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceOfferRepository extends CrudRepository<ServiceOffer, Long> {

    @Override
    Optional<ServiceOffer> findById(Long Id);

    List<ServiceOffer> findAll();
}
