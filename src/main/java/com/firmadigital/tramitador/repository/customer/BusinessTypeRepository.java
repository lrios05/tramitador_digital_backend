package com.firmadigital.tramitador.repository.customer;

import com.firmadigital.tramitador.model.customer.BusinessType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessTypeRepository extends CrudRepository<BusinessType, Long> {

    @Override
    Optional<BusinessType> findById(Long aLong);

    BusinessType findBusinessTypeByBusinessType(String businessType);

    List<BusinessType> findAll();
}
