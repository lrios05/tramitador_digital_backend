package com.firmadigital.tramitador.service.contract;

import com.firmadigital.tramitador.dto.model.contract.GatherPlanDto;

public interface GatherPlanService {

    GatherPlanDto findGatherPlanById(Long id);
    GatherPlanDto createGatherPlan(GatherPlanDto gatherPlanDto, Long contractId);
    GatherPlanDto updateGatherPlan(GatherPlanDto gatherPlanDto, Long contractId);
}
