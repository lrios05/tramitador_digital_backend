package com.firmadigital.tramitador.service.contract;

import com.firmadigital.tramitador.dto.model.contract.ContractDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContractService {

    ContractDto findContractById(Long contractId);

    ContractDto findByContractCode(String code);

    ContractDto findByCodeAndStatus(String code, String status);

    List<ContractDto> findAllByStatus(String status);

    ContractDto createContract(ContractDto contractDto, Long customerId);

    ContractDto updateContract(ContractDto contractDto);

    Page<ContractDto> findAllContracts(Pageable pageable);
}
