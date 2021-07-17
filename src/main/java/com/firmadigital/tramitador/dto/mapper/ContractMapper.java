package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.contract.ContractDto;
import com.firmadigital.tramitador.model.contract.Contract;
import org.springframework.stereotype.Component;

@Component
public class ContractMapper {

    public static ContractDto toContractDto (Contract contract) {

        return new ContractDto()
                .setContractId(contract.getId())
                .setCustomerDto(CustomerMapper.toCustomerDto(contract.getCustomer()))
                .setContractCode(contract.getContractCode())
                .setInitDate(contract.getInitDate())
                .setEndDate(contract.getEndDate())
                .setServiceOfferDto(ServiceOfferMapper.toServiceOfferDto(contract.getServiceOffer()))
                .setTotalCost(contract.getTotalCost())
                .setPaymentTypeDto(PaymentTypeMapper.toPaymentTypeDto(contract.getPaymentType()))
                .setPaymentFrequencyDto(PaymentFrequencyMapper.toPaymentFrequencyDto(contract.getPaymentFrequency()))
                .setPayments(contract.getPayments())
                .setAmount(contract.getAmount())
                .setGatherFrequencyDto(GatherFrequencyMapper.toGatherFrequencyDto(contract.getGatherFrequency()))
                .setWasteTypeDto(WasteTypeMapper.toWasteTypeDto(contract.getWasteType()))
                .setVolume(contract.getVolume())
                .setUnitDto(UnitMapper.toUnitDto(contract.getUnit()))
                .setDays(contract.getDays())
                .setStatus(contract.getStatus());
    }
}
