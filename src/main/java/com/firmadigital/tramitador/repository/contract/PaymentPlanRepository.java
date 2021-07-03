package com.firmadigital.tramitador.repository.contract;

import com.firmadigital.tramitador.model.contract.PaymentPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentPlanRepository extends CrudRepository<PaymentPlan, Long> {

    @Override
    Optional<PaymentPlan> findById(Long id);
}
