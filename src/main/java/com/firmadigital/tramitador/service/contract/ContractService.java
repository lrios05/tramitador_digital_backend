package com.firmadigital.tramitador.service.contract;

import com.firmadigital.tramitador.dto.model.contract.ContractDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Collection;
import java.util.Optional;

public interface ContractService {

    ContractDto findContractById(Long contractId);

    ContractDto findContractByContractCode(String contractCode);

    ContractDto createContract(ContractDto contractDto, Long customerId);

    ContractDto updateContract(ContractDto contractDto);

    Page<ContractDto> findAllContracts(Pageable pageable);
}
