package com.firmadigital.tramitador.service.contract;

import com.firmadigital.tramitador.dto.model.contract.PaymentTypeDto;

import java.util.List;
import java.util.Optional;

public interface PaymentTypeService {

    PaymentTypeDto findPaymentTypeById(Long id);
    PaymentTypeDto createPaymentType(PaymentTypeDto paymentTypeDto);
    PaymentTypeDto updatePaymentType(PaymentTypeDto paymentTypeDto);
    List<PaymentTypeDto> findAllPaymentTypes();
}
