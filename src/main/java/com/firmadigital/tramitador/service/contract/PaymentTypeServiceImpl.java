package com.firmadigital.tramitador.service.contract;

import com.firmadigital.tramitador.dto.model.contract.PaymentTypeDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.contract.PaymentType;
import com.firmadigital.tramitador.repository.contract.PaymentTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.firmadigital.tramitador.exception.EntityType.CONTRACT;
import static com.firmadigital.tramitador.exception.EntityType.PAYMENT_TYPE;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class PaymentTypeServiceImpl implements PaymentTypeService{

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PaymentTypeDto findPaymentTypeById(Long id) {
        Optional<PaymentType> paymentType = paymentTypeRepository.findById(id);

        if (paymentType.isPresent()) {
            return modelMapper.map(paymentType.get(), PaymentTypeDto.class);
        }

        throw exception(PAYMENT_TYPE, ENTITY_NOT_FOUND, "Forma de pago: " + id);
    }

    @Override
    public PaymentTypeDto createPaymentType(PaymentTypeDto paymentTypeDto) {
        return null;
    }

    @Override
    public PaymentTypeDto updatePaymentType(PaymentTypeDto paymentTypeDto) {
        return null;
    }

    @Override
    public List<PaymentTypeDto> findAllPaymentTypes() {
        return null;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
