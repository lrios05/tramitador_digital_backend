package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.contract.PaymentTypeDto;
import com.firmadigital.tramitador.model.contract.PaymentType;
import org.springframework.stereotype.Component;

@Component
public class PaymentTypeMapper {

    public static PaymentTypeDto toPaymentTypeDto(PaymentType paymentType) {

        return new PaymentTypeDto()
                .setPayTypeId(paymentType.getId())
                .setPayType(paymentType.getPayType());
    }
}
