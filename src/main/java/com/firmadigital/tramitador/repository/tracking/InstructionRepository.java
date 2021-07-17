package com.firmadigital.tramitador.repository.tracking;

import com.firmadigital.tramitador.model.tracking.Instruction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructionRepository extends CrudRepository<Instruction, Long> {

    @Override
    Optional<Instruction> findById(Long id);
    List<Instruction> findAll();
}
