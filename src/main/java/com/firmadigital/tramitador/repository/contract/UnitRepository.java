package com.firmadigital.tramitador.repository.contract;

import com.firmadigital.tramitador.model.contract.GatherFrequency;
import com.firmadigital.tramitador.model.serviceoffer.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitRepository extends CrudRepository<Unit, Long> {

    @Override
    Optional<Unit> findById(Long id);

    List<Unit> findAll();
}
