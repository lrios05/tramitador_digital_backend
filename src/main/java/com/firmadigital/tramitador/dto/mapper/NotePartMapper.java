package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.tracking.NoteDto;
import com.firmadigital.tramitador.dto.model.tracking.NotePartDto;
import com.firmadigital.tramitador.model.tracking.Note;
import com.firmadigital.tramitador.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class NotePartMapper {
    public static NotePartDto toNotePartDto (Note note) {

        return new NotePartDto()
                .setNoteId(note.getId())
                .setCreatedAt(note.getCreatedOn())
                .setContractPartDto(ContractPartMapper.toContractPartDto(note.getContract()))
                .setSequence(note.getNumber())
                .setYear(note.getYear())
                .setSubject(note.getSubject())
                .setUser(getFullName(note.getUser()));
    }

    private static String getFullName(User user){
        return user.getMaternal() != null ? user.getName().concat(" ").concat(user.getPaternal()).concat(" ").concat(user.getMaternal()) : "";
    }
}
