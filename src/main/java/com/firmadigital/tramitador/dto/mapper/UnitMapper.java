package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.contract.UnitDto;
import com.firmadigital.tramitador.model.serviceoffer.Unit;
import org.springframework.stereotype.Component;

@Component
public class UnitMapper {

    public static UnitDto toUnitDto(Unit unit) {

        return new UnitDto()
                .setUniteId(unit.getId())
                .setUnit(unit.getUnit())
                .setDescription(unit.getDescription());
    }
}
