package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.tracking.InstructionDto;
import com.firmadigital.tramitador.model.tracking.Instruction;
import org.springframework.stereotype.Component;

@Component
public class InstructionMapper {

    public static InstructionDto toInstructionDto(Instruction instruction){

        return new InstructionDto()
                .setInstructionId(instruction.getId())
                .setInstruction(instruction.getInstruction());
    }
}
