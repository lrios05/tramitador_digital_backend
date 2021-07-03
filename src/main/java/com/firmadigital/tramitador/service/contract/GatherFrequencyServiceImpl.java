package com.firmadigital.tramitador.service.contract;

import com.firmadigital.tramitador.dto.model.contract.GatherFrequencyDto;
import com.firmadigital.tramitador.dto.model.contract.UnitDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.contract.GatherFrequency;
import com.firmadigital.tramitador.model.serviceoffer.Unit;
import com.firmadigital.tramitador.repository.contract.GatherFrequencyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.firmadigital.tramitador.exception.EntityType.GATHER_FREQUENCY;
import static com.firmadigital.tramitador.exception.EntityType.UNIT;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class GatherFrequencyServiceImpl implements GatherFrequencyService{

    @Autowired
    private GatherFrequencyRepository gatherFrequencyRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public GatherFrequencyDto findGatherFrequencyById(Long id) {
        Optional<GatherFrequency> gatherFrequency = gatherFrequencyRepository.findById(id);

        if (gatherFrequency.isPresent()) {
            return modelMapper.map(gatherFrequency.get(), GatherFrequencyDto.class);
        }

        throw exception(GATHER_FREQUENCY, ENTITY_NOT_FOUND, "Frecuencia de recojos: " + id);
    }

    @Override
    public GatherFrequencyDto createGatherFrequency(GatherFrequencyDto gatherFrequencyDto) {
        return null;
    }

    @Override
    public GatherFrequencyDto updateGatherFrequency(GatherFrequencyDto gatherFrequencyDto) {
        return null;
    }

    @Override
    public List<GatherFrequencyDto> findAllGatherFrequencies() {
        return null;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
