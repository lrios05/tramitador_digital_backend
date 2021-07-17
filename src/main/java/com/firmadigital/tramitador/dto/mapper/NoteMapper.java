package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.tracking.NoteDto;
import com.firmadigital.tramitador.model.tracking.Note;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class NoteMapper {

    public static NoteDto toNoteDto (Note note) {

        return new NoteDto()
                .setNoteId(note.getId())
                .setContractDto(ContractMapper.toContractDto(note.getContract()))
                .setNumber(note.getNumber())
                .setYear(note.getYear())
                .setDeadline(note.getDeadline())
                .setSubject(note.getSubject())
                .setStatus(note.getStatus())
                .setUserDto(UserMapper.toUserDto(note.getUser()));
    }
}
