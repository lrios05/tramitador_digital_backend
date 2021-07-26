package com.firmadigital.tramitador.dto.mapper.views;

import com.firmadigital.tramitador.dto.model.views.ContractInfoDto;
import com.firmadigital.tramitador.model.contract.Contract;
import org.springframework.stereotype.Component;

@Component
public class ContractInfoMapper {

    public static ContractInfoDto toContractInfoDto (Contract contract) {

        return new ContractInfoDto()
                .setContractId(contract.getId())
                .setCustomerInfo(CustomerInfoMapper.toCustomerInfoDto(contract.getCustomer()))
                .setContractCode(contract.getContractCode())
                .setInitDate(contract.getInitDate())
                .setEndDate(contract.getEndDate())
                .setServiceType(contract.getServiceOffer().getServiceType().getServiceType())
                .setService(contract.getServiceOffer().getService())
                .setTotalCost(contract.getTotalCost())
                .setPaymentType(contract.getPaymentType().getPayType())
                .setPaymentFrequency(contract.getPaymentFrequency().getFrequency())
                .setPayments(contract.getPayments())
                .setAmount(contract.getAmount())
                .setGatherFrequency(contract.getGatherFrequency().getFrequency())
                .setWasteType(contract.getWasteType().getWaste())
                .setVolume(contract.getVolume())
                .setUnit(contract.getUnit().getUnit())
                .setDays(contract.getDays());
    }
}
