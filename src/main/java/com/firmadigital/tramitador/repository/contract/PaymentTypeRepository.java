package com.firmadigital.tramitador.repository.contract;

import com.firmadigital.tramitador.model.contract.PaymentType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentTypeRepository extends CrudRepository<PaymentType, Long> {

    @Override
    Optional<PaymentType> findById(Long id);

    List<PaymentType> findAll();
}
