package com.firmadigital.tramitador.service.contract;

import com.firmadigital.tramitador.dto.mapper.GatherPlanMapper;
import com.firmadigital.tramitador.dto.model.contract.GatherPlanDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.contract.Contract;
import com.firmadigital.tramitador.model.contract.GatherFrequency;
import com.firmadigital.tramitador.model.contract.GatherPlan;
import com.firmadigital.tramitador.model.contract.WasteType;
import com.firmadigital.tramitador.model.serviceoffer.Unit;
import com.firmadigital.tramitador.repository.contract.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.firmadigital.tramitador.exception.EntityType.CONTRACT;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class GatherPlanServiceImpl implements GatherPlanService{

    @Autowired
    ContractRepository contractRepository;
    @Autowired
    GatherPlanRepository gatherPlanRepository;
    @Autowired
    GatherFrequencyRepository gatherFrequencyRepository;
    @Autowired
    WasteTypeRepository wasteTypeRepository;
    @Autowired
    UnitRepository unitRepository;

    @Override
    public GatherPlanDto findGatherPlanById(Long id) {
        return null;
    }

    @Override
    public GatherPlanDto createGatherPlan(GatherPlanDto gatherPlanDto, Long contractId) {

        Optional<Contract> contract = contractRepository.findById(contractId);

        if (contract.isPresent()) {
            Optional<GatherFrequency> gatherFrequency = gatherFrequencyRepository
                    .findById(gatherPlanDto.getGatherFrequencyDto().getGatherId());

            Optional<WasteType> wasteType = wasteTypeRepository
                    .findById(gatherPlanDto.getWasteTypeDto().getWasteId());

            Optional<Unit> unit = unitRepository.findById(gatherPlanDto.getUnitDto().getUnitId());

            GatherPlan gatherPlan = new GatherPlan()
                    //.setContract(contract.get())
                    .setGatherFrequency(gatherFrequency.get())
                    .setWasteType(wasteType.get())
                    .setVolume(gatherPlanDto.getVolume())
                    .setUnit(unit.get())
                    .setDays(gatherPlanDto.getDays());

            return GatherPlanMapper.toGatherPlanDto(gatherPlanRepository.save(gatherPlan));
        }

        throw exception(CONTRACT, ENTITY_NOT_FOUND, "Contrato ID: " + contractId);
    }

    @Override
    public GatherPlanDto updateGatherPlan(GatherPlanDto gatherPlanDto, Long contractId) {
        return null;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
