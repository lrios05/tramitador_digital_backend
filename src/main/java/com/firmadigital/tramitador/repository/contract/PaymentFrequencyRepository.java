package com.firmadigital.tramitador.repository.contract;

import com.firmadigital.tramitador.model.contract.PaymentFrequency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentFrequencyRepository extends CrudRepository<PaymentFrequency, Long> {

    @Override
    Optional<PaymentFrequency> findById(Long id);

    List<PaymentFrequency> findAll();
}
