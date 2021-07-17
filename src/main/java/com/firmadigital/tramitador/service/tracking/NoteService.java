package com.firmadigital.tramitador.service.tracking;

import com.firmadigital.tramitador.dto.model.tracking.NoteDto;
import com.firmadigital.tramitador.model.tracking.Note;

import java.util.List;

public interface NoteService {

    NoteDto findById(Long id);
    NoteDto findByContractId(Long id);
    NoteDto findNoteByNumber(int number);
    NoteDto findNoteByStatus(String status);
    Long countAllNotes();
    NoteDto createNote(Long contractId, Long userId, NoteDto noteDto);
    NoteDto updateNote(Long id, NoteDto noteDto);
    NoteDto deleteNoteById(Long id);
    List<NoteDto> findAll();
}
