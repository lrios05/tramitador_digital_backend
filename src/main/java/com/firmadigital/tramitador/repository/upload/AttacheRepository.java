package com.firmadigital.tramitador.repository.upload;

import com.firmadigital.tramitador.model.upload.Attache;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttacheRepository extends CrudRepository<Attache, Long> {

    @Override
    Optional<Attache> findById(Long id);

    @Query("SELECT a FROM Attache a WHERE a.contract.contractCode = ?1")
    Optional<Attache> findByContractCode(String code);
    List<Attache> findAll();
}
