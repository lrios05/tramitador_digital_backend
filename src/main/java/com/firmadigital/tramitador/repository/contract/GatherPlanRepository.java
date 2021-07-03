package com.firmadigital.tramitador.repository.contract;

import com.firmadigital.tramitador.model.contract.GatherPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GatherPlanRepository extends CrudRepository<GatherPlan, Long> {

    @Override
    Optional<GatherPlan> findById(Long id);
}
