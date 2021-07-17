package com.firmadigital.tramitador.service.tracking;

import com.firmadigital.tramitador.dto.model.tracking.InstructionDto;

import java.util.List;

public interface InstructionService {

    InstructionDto findById(Long id);
    InstructionDto createInstruction(InstructionDto instructionDto);
    InstructionDto updateInstruction(Long id, InstructionDto instructionDto);
    List<InstructionDto> findAll();
}
