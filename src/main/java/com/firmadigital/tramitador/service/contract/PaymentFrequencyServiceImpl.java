package com.firmadigital.tramitador.service.contract;

import com.firmadigital.tramitador.dto.model.contract.GatherFrequencyDto;
import com.firmadigital.tramitador.dto.model.contract.PaymentFrequencyDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.contract.GatherFrequency;
import com.firmadigital.tramitador.model.contract.PaymentFrequency;
import com.firmadigital.tramitador.repository.contract.PaymentFrequencyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.firmadigital.tramitador.exception.EntityType.GATHER_FREQUENCY;
import static com.firmadigital.tramitador.exception.EntityType.PAYMENT_FREQUENCY;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class PaymentFrequencyServiceImpl implements PaymentFrequencyService{

    @Autowired
    private PaymentFrequencyRepository paymentFrequencyRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PaymentFrequencyDto findPaymentFrequencyById(Long id) {
        Optional<PaymentFrequency> paymentFrequency = paymentFrequencyRepository.findById(id);

        if (paymentFrequency.isPresent()) {
            return modelMapper.map(paymentFrequency.get(), PaymentFrequencyDto.class);
        }

        throw exception(PAYMENT_FREQUENCY, ENTITY_NOT_FOUND, "Frecuencia de pags: " + id);
    }

    @Override
    public PaymentFrequencyDto createPaymentFrequency(PaymentFrequencyDto paymentFrequencyDto) {
        return null;
    }

    @Override
    public PaymentFrequencyDto updatePaymentFrequency(PaymentFrequencyDto paymentFrequencyDto) {
        return null;
    }

    @Override
    public List<PaymentFrequencyDto> findAllPaymentFrequencies() {
        return null;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
