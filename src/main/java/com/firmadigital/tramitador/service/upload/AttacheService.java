package com.firmadigital.tramitador.service.upload;

import com.firmadigital.tramitador.dto.model.upload.AttacheDto;
import com.firmadigital.tramitador.dto.model.upload.AttachePartDto;

import java.util.List;

public interface AttacheService {

    AttacheDto findByAttacheId(Long id);
    AttachePartDto findByContractCode(String code);
    AttacheDto createAttache(Long contractId, Long userId, AttacheDto attacheDto);
    AttacheDto updateAttache(AttacheDto attacheDto);
    List<AttacheDto> findAllAttaches();
}
