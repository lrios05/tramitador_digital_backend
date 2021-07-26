package com.firmadigital.tramitador.repository.contract;

import com.firmadigital.tramitador.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long> {

    @Override
    Optional<Contract> findById(Long id);

    @Query("SELECT c FROM Contract c WHERE c.contractCode = ?1")
    Optional<Contract> findByContractCode(String code);
/*
    @Query("SELECT c FROM Contract c WHERE c.customer.user.id = ?1")
    Optional<Contract> findByUserId(Long id);
*/
    @Query("SELECT c FROM Contract c WHERE c.contractCode =  ?1 AND c.status = ?2")
    Optional<Contract> findByCodeAndStatus(String code, String status);

    @Query("SELECT c FROM Contract c WHERE c.status = ?1")
    List<Contract> findAllByStatus(String status);

    Page<Contract> findAll(Pageable pageable);
}
