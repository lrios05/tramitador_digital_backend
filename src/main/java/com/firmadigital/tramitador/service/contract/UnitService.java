package com.firmadigital.tramitador.service.contract;

import com.firmadigital.tramitador.dto.model.contract.UnitDto;
import com.firmadigital.tramitador.model.serviceoffer.Unit;

import java.util.List;

public interface UnitService {

    UnitDto findUnitById(Long unitId);
    UnitDto createUnit(UnitDto unitDto);
    UnitDto updateUnit(UnitDto unitDto);
    List<UnitDto> findAllUnits();
}
