package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.contract.PaymentFrequencyDto;
import com.firmadigital.tramitador.model.contract.PaymentFrequency;
import org.springframework.stereotype.Component;

@Component
public class PaymentFrequencyMapper {

    public static PaymentFrequencyDto toPaymentFrequencyDto(PaymentFrequency paymentFrequency) {

        return new PaymentFrequencyDto()
                .setPaymentId(paymentFrequency.getId())
                .setFrequency(paymentFrequency.getFrequency());
    }
}
