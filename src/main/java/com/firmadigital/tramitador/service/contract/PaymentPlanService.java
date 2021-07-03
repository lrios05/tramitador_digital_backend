package com.firmadigital.tramitador.service.contract;

import com.firmadigital.tramitador.dto.model.contract.PaymentPlanDto;

import java.util.Optional;

public interface PaymentPlanService {

    PaymentPlanDto findPaymentPlanById(Long contractId);
    PaymentPlanDto createPaymentPlan(PaymentPlanDto paymentPlanDto, Long contractId);
    PaymentPlanDto updatePaymentPlan(PaymentPlanDto paymentPlanDto, Long contractId);
}
