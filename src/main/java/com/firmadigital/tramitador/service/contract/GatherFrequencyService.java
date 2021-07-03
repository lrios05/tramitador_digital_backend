package com.firmadigital.tramitador.service.contract;

import com.firmadigital.tramitador.dto.model.contract.GatherFrequencyDto;

import java.util.List;
import java.util.Optional;

public interface GatherFrequencyService {

    GatherFrequencyDto findGatherFrequencyById(Long id);
    GatherFrequencyDto createGatherFrequency(GatherFrequencyDto gatherFrequencyDto);
    GatherFrequencyDto updateGatherFrequency(GatherFrequencyDto gatherFrequencyDto);
    List<GatherFrequencyDto> findAllGatherFrequencies();
}
