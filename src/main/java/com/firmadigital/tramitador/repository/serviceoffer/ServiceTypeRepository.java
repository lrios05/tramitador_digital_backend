package com.firmadigital.tramitador.repository.serviceoffer;

import com.firmadigital.tramitador.model.serviceoffer.ServiceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceTypeRepository extends CrudRepository<ServiceType, Long> {

    @Override
    Optional<ServiceType> findById(Long id);

    List<ServiceType> findAll();
}
