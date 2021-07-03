package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.contract.WasteTypeDto;
import com.firmadigital.tramitador.model.contract.WasteType;
import org.springframework.stereotype.Component;

@Component
public class WasteTypeMapper {

    public static WasteTypeDto toWasteTypeDto(WasteType wasteType) {

        return new WasteTypeDto()
                .setWasteId(wasteType.getId())
                .setWaste(wasteType.getWaste())
                .setDescription(wasteType.getDescription());
    }
}
