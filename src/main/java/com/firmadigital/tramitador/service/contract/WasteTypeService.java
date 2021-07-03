package com.firmadigital.tramitador.service.contract;

import com.firmadigital.tramitador.dto.model.contract.WasteTypeDto;

import java.util.List;
import java.util.Optional;

public interface WasteTypeService {

    WasteTypeDto findWasteTypeById(Long id);
    WasteTypeDto createWasteType(WasteTypeDto wasteTypeDto);
    WasteTypeDto updateWasteType(WasteTypeDto wasteTypeDto);
    List<WasteTypeDto> listAllWasteTypes();

}
