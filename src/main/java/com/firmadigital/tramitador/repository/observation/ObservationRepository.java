package com.firmadigital.tramitador.repository.observation;

import com.firmadigital.tramitador.model.observation.Observation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObservationRepository extends CrudRepository<Observation, Long> {

    @Override
    Optional<Observation> findById(Long id);

    @Query("SELECT o FROM Observation o WHERE o.contract.contractCode = ?1")
    Optional<Observation> findByContractCode(String code);

    @Query("SELECT o FROM Observation o WHERE o.status = ?1")
    List<Observation> findAllByStatus(String status);

    @Query("SELECT o FROM Observation o WHERE o.contract.contractCode = ?1 and o.status = ?2")
    Optional<Observation> findByContractCodeAndStatus(String code, String status);

    List<Observation> findAll();
}
