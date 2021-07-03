package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.contract.PaymentPlanDto;
import com.firmadigital.tramitador.model.contract.PaymentPlan;

public class PaymentPlanMapper {

    public static PaymentPlanDto toPaymentPlanDto(PaymentPlan paymentPlan) {

        return new PaymentPlanDto()
                .setContractDto(ContractMapper.toContractDto(paymentPlan.getContract()))
                .setPaymentTypeDto(PaymentTypeMapper.toPaymentTypeDto(paymentPlan.getPaymentType()))
                .setPaymentFrequencyDto(PaymentFrequencyMapper.toPaymentFrequencyDto(paymentPlan.getPaymentFrequency()))
                .setPayments(paymentPlan.getPayments())
                .setAmount(paymentPlan.getAmount());
    }
}
