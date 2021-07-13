package com.firmadigital.tramitador.service.contract;

import com.firmadigital.tramitador.dto.mapper.PaymentPlanMapper;
import com.firmadigital.tramitador.dto.model.contract.PaymentPlanDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.contract.Contract;
import com.firmadigital.tramitador.model.contract.PaymentFrequency;
import com.firmadigital.tramitador.model.contract.PaymentPlan;
import com.firmadigital.tramitador.model.contract.PaymentType;
import com.firmadigital.tramitador.repository.contract.ContractRepository;
import com.firmadigital.tramitador.repository.contract.PaymentFrequencyRepository;
import com.firmadigital.tramitador.repository.contract.PaymentPlanRepository;
import com.firmadigital.tramitador.repository.contract.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.firmadigital.tramitador.exception.EntityType.CONTRACT;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class PaymentPlanServiceImpl implements PaymentPlanService{

    @Autowired
    ContractRepository contractRepository;
    @Autowired
    PaymentPlanRepository paymentPlanRepository;
    @Autowired
    PaymentTypeRepository paymentTypeRepository;
    @Autowired
    PaymentFrequencyRepository paymentFrequencyRepository;

    @Override
    public PaymentPlanDto findPaymentPlanById(Long contractId) {
        return null;
    }

    @Override
    public PaymentPlanDto createPaymentPlan(PaymentPlanDto paymentPlanDto, Long contractId) {

        Optional<Contract> contract = contractRepository.findById(contractId);

        if (contract.isPresent()) {
            Optional<PaymentType> paymentType = paymentTypeRepository
                    .findById(paymentPlanDto.getPaymentTypeDto().getPayTypeId());

            Optional<PaymentFrequency> paymentFrequency = paymentFrequencyRepository
                    .findById(paymentPlanDto.getPaymentFrequencyDto().getPaymentId());

            PaymentPlan paymentPlan = new PaymentPlan()
                    .setContract(contract.get())
                    .setPaymentType(paymentType.get())
                    .setPaymentFrequency(paymentFrequency.get())
                    .setPayments(paymentPlanDto.getPayments())
                    .setAmount(paymentPlanDto.getAmount());

            return PaymentPlanMapper.toPaymentPlanDto(paymentPlanRepository.save(paymentPlan));

        }

        throw exception(CONTRACT, ENTITY_NOT_FOUND, "Contracto ID: " + contractId);
    }

    @Override
    public PaymentPlanDto updatePaymentPlan(PaymentPlanDto paymentPlanDto, Long contractId) {
        return null;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
