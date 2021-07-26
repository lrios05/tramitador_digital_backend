package com.firmadigital.tramitador.service.signatures;

import com.firmadigital.tramitador.dto.model.signatures.DigitalSignDto;

import java.util.List;

public interface DigitalSignService {

    DigitalSignDto findById(Long id);
    List<DigitalSignDto> findByContractId(Long id);
    DigitalSignDto createDigitalSign(DigitalSignDto digitalSignDto);
    List<DigitalSignDto> findAll();
}
