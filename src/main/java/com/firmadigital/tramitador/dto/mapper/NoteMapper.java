package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.tracking.NoteDto;
import com.firmadigital.tramitador.model.tracking.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    public static NoteDto toNoteDto (Note note) {

        return new NoteDto()
                .setNoteId(note.getId())
                .setContractDto(ContractMapper.toContractDto(note.getContract()))
                .setSequence(note.getNumber())
                .setYear(note.getYear())
                .setSubject(note.getSubject())
                .setStatus(note.getStatus())
                .setUserDto(UserMapper.toUserDto(note.getUser()));
    }
}
