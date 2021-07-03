package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.contract.GatherPlanDto;
import com.firmadigital.tramitador.model.contract.GatherPlan;
import org.springframework.stereotype.Component;

@Component
public class GatherPlanMapper {

    public static GatherPlanDto toGatherPlanDto(GatherPlan gatherPlan) {

        return new GatherPlanDto()
                //.setContractDto(ContractMapper.toContractDto(gatherPlan.getContract()))
                .setGatherFrequencyDto(GatherFrequencyMapper.toGatherFrequencyDto(gatherPlan.getGatherFrequency()))
                .setWasteTypeDto(WasteTypeMapper.toWasteTypeDto(gatherPlan.getWasteType()))
                .setVolume(gatherPlan.getVolume())
                .setUnitDto(UnitMapper.toUnitDto(gatherPlan.getUnit()))
                .setDays(gatherPlan.getDays());
    }
}
