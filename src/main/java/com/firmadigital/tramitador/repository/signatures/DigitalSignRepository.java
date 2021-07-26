package com.firmadigital.tramitador.repository.signatures;

import com.firmadigital.tramitador.model.signatures.DigitalSign;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DigitalSignRepository extends CrudRepository<DigitalSign, Long> {

    @Override
    Optional<DigitalSign> findById(Long id);
    @Query("SELECT d FROM DigitalSign d WHERE d.contract.id = ?1")
    List<DigitalSign> findByContractId(Long id);
    List<DigitalSign> findAll();
}
