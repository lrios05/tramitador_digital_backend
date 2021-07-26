package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.contract.ContractPartDto;
import com.firmadigital.tramitador.model.contract.Contract;
import org.springframework.stereotype.Component;

@Component
public class ContractPartMapper {
    public static ContractPartDto toContractPartDto (Contract contract) {

        return new ContractPartDto()
                .setContractId(contract.getId())
                .setContractCode(contract.getContractCode());

    }
}
