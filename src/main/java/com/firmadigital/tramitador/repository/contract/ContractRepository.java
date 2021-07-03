package com.firmadigital.tramitador.repository.contract;

import com.firmadigital.tramitador.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long> {

    @Override
    Optional<Contract> findById(Long id);

    Contract findContractByContractCode(String contractCode);

    Page<Contract> findAll(Pageable pageable);
}
