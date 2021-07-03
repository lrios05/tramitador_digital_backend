package com.firmadigital.tramitador.service.contract;

import com.firmadigital.tramitador.dto.model.contract.UnitDto;
import com.firmadigital.tramitador.dto.model.contract.WasteTypeDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.contract.WasteType;
import com.firmadigital.tramitador.model.serviceoffer.Unit;
import com.firmadigital.tramitador.repository.contract.UnitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.firmadigital.tramitador.exception.EntityType.UNIT;
import static com.firmadigital.tramitador.exception.EntityType.WASTE_TYPE;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class UnitServiceImpl implements UnitService{

    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public UnitDto findUnitById(Long unitId) {
        Optional<Unit> unit = unitRepository.findById(unitId);

        if (unit.isPresent()) {
            return modelMapper.map(unit.get(), UnitDto.class);
        }

        throw exception(UNIT, ENTITY_NOT_FOUND, "Tipo de Unidad: " + unitId);
    }

    @Override
    public UnitDto createUnit(UnitDto unitDto) {
        return null;
    }

    @Override
    public UnitDto updateUnit(UnitDto unitDto) {
        return null;
    }

    @Override
    public List<Unit> findAllUnits() {
        return null;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
