package com.firmadigital.tramitador.service.tracking;

import com.firmadigital.tramitador.dto.mapper.InstructionMapper;
import com.firmadigital.tramitador.dto.model.tracking.InstructionDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.tracking.Instruction;
import com.firmadigital.tramitador.repository.tracking.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.firmadigital.tramitador.exception.EntityType.INSTRUCTION;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class InstructionServiceImpl implements InstructionService{

    @Autowired
    private InstructionRepository instructionRepository;

    @Override
    public InstructionDto findById(Long id) {
        Optional<Instruction> instruction = instructionRepository.findById(id);

        if (instruction.isPresent()) {
            return InstructionMapper.toInstructionDto(instruction.get());
        }

        throw exception(INSTRUCTION, ENTITY_NOT_FOUND, "Tipo de Unidad: " + id);
    }

    @Override
    public InstructionDto createInstruction(InstructionDto instructionDto) {

        Instruction instruction = new Instruction()
                .setInstruction(instructionDto.getInstruction());

        return InstructionMapper.toInstructionDto(instructionRepository.save(instruction));
    }

    @Override
    public InstructionDto updateInstruction(Long id, InstructionDto instructionDto) {
        return null;
    }

    @Override
    public List<InstructionDto> findAll() {
        List<InstructionDto> instructionDtoList = new ArrayList<>();
        instructionRepository.findAll()
                .forEach(instruction -> {
                    instructionDtoList.add(InstructionMapper.toInstructionDto(instruction));
                });

        return instructionDtoList;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
