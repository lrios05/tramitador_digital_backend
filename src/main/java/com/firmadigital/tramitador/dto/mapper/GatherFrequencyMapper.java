package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.contract.GatherFrequencyDto;
import com.firmadigital.tramitador.model.contract.GatherFrequency;
import org.springframework.stereotype.Component;

@Component
public class GatherFrequencyMapper {

    public static GatherFrequencyDto toGatherFrequencyDto(GatherFrequency gatherFrequency) {

        return new GatherFrequencyDto()
                .setGFrequencyId(gatherFrequency.getId())
                .setFrequency(gatherFrequency.getFrequency());
    }
}

