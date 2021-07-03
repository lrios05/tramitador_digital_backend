package com.firmadigital.tramitador.service.contract;

import com.firmadigital.tramitador.dto.model.contract.PaymentFrequencyDto;

import java.util.List;
import java.util.Optional;

public interface PaymentFrequencyService {

    PaymentFrequencyDto findPaymentFrequencyById(Long id);
    PaymentFrequencyDto createPaymentFrequency(PaymentFrequencyDto paymentFrequencyDto);
    PaymentFrequencyDto updatePaymentFrequency(PaymentFrequencyDto paymentFrequencyDto);
    List<PaymentFrequencyDto> findAllPaymentFrequencies();
}
