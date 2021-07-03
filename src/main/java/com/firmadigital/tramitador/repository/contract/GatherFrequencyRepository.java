package com.firmadigital.tramitador.repository.contract;

import com.firmadigital.tramitador.model.contract.GatherFrequency;
import com.firmadigital.tramitador.model.contract.PaymentFrequency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GatherFrequencyRepository extends CrudRepository<GatherFrequency, Long> {

    @Override
    Optional<GatherFrequency> findById(Long id);

    List<GatherFrequency> findAll();
}
