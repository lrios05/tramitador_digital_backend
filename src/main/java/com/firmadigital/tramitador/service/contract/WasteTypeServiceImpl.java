package com.firmadigital.tramitador.service.contract;

import com.firmadigital.tramitador.dto.model.contract.WasteTypeDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.contract.WasteType;
import com.firmadigital.tramitador.repository.contract.WasteTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.firmadigital.tramitador.exception.EntityType.WASTE_TYPE;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class WasteTypeServiceImpl implements WasteTypeService{

    @Autowired
    private WasteTypeRepository wasteTypeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public WasteTypeDto findWasteTypeById(Long id) {
        Optional<WasteType> wasteType = wasteTypeRepository.findById(id);

        if (wasteType.isPresent()) {
            return modelMapper.map(wasteType.get(), WasteTypeDto.class);
        }

        throw exception(WASTE_TYPE, ENTITY_NOT_FOUND, "Tipo de Residuo: " + id);
    }

    @Override
    public WasteTypeDto createWasteType(WasteTypeDto wasteTypeDto) {
        return null;
    }

    @Override
    public WasteTypeDto updateWasteType(WasteTypeDto wasteTypeDto) {
        return null;
    }

    @Override
    public List<WasteTypeDto> listAllWasteTypes() {
        return null;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
