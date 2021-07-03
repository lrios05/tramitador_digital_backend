package com.firmadigital.tramitador.repository.contract;

import com.firmadigital.tramitador.model.contract.WasteType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WasteTypeRepository extends CrudRepository<WasteType, Long> {

    @Override
    Optional<WasteType> findById(Long id);

    List<WasteType> findAll();
}
