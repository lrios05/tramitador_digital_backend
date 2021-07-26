package com.firmadigital.tramitador.service.tracking;

import com.firmadigital.tramitador.dto.model.tracking.NoteDto;
import com.firmadigital.tramitador.dto.model.tracking.NotePartDto;
import com.firmadigital.tramitador.model.tracking.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    NoteDto findById(Long id);
    NotePartDto findByNoteId(Long noteId);
    NoteDto findByContractId(Long id);
    NoteDto findByContractCode(String code);
    NoteDto findNoteByNumber(Long number);
    List<NoteDto> findNoteByStatus(String status);
    NoteDto findByNumberAndStatus(Long number, String status);
    NoteDto findByContractAndStatus(String code, String status);
    Long countAllNotes();
    NoteDto createNote(Long contractId, Long userId, NoteDto noteDto);
    NoteDto updateNote(Long id, NoteDto noteDto);
    NoteDto deleteNoteById(Long id);
    List<NoteDto> findAll();
}
