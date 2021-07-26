package com.firmadigital.tramitador.service.tracking;

import com.firmadigital.tramitador.dto.mapper.NoteMapper;
import com.firmadigital.tramitador.dto.mapper.NotePartMapper;
import com.firmadigital.tramitador.dto.model.tracking.NoteDto;
import com.firmadigital.tramitador.dto.model.tracking.NotePartDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.contract.Contract;
import com.firmadigital.tramitador.model.tracking.Note;
import com.firmadigital.tramitador.model.user.User;
import com.firmadigital.tramitador.repository.contract.ContractRepository;
import com.firmadigital.tramitador.repository.tracking.NoteRepository;
import com.firmadigital.tramitador.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.firmadigital.tramitador.exception.EntityType.CONTRACT;
import static com.firmadigital.tramitador.exception.EntityType.NOTE;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public NoteDto findById(Long id) {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isPresent()) {
            return NoteMapper.toNoteDto(note.get());
        }
        throw exception(NOTE, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public NotePartDto findByNoteId(Long id) {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isPresent()) {
            return NotePartMapper.toNotePartDto(note.get());
        }
        throw exception(NOTE, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public NoteDto findByContractId(Long id) {
        Optional<Note> note = noteRepository.findByContractId(id);
        if (note.isPresent()) {
            return NoteMapper.toNoteDto(note.get());
        }
        throw exception(NOTE, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public NoteDto findByContractCode(String code) {
        Optional<Note> note = noteRepository.findByContractCode(code);
        if (note.isPresent()) {
            return NoteMapper.toNoteDto(note.get());
        }
        throw exception(NOTE, ENTITY_NOT_FOUND, code);
    }

    @Override
    public NoteDto findNoteByNumber(Long number) {
        Optional<Note> note = noteRepository.findNoteByNumber(number);
        if (note.isPresent()) {
            return NoteMapper.toNoteDto(note.get());
        }
        throw exception(NOTE, ENTITY_NOT_FOUND, number.toString());
    }

    @Override
    public List<NoteDto> findNoteByStatus(String status) {

        List<NoteDto> noteDtoList = new ArrayList<>();
        noteRepository.findNoteByStatus(status)
                .forEach(note -> {
                    noteDtoList.add(NoteMapper.toNoteDto(note));
                });
        return noteDtoList;

//        List<Note> note = noteRepository.findNoteByStatus(status);
////        if (note.isPresent()) {
////            return NoteMapper.toNoteDto(note.get());
////        }
//        throw exception(NOTE, ENTITY_NOT_FOUND, status);
    }

    @Override
    public NoteDto findByNumberAndStatus(Long number, String status) {
        System.out.println("Si llegan datos:" + number + " " + status);
        Optional<Note> note = noteRepository.findByNumberAndStatus(number, status);
        if (note.isPresent()) {
            return NoteMapper.toNoteDto(note.get());
        }
        throw exception(NOTE, ENTITY_NOT_FOUND, number.toString());
    }

    @Override
    public NoteDto findByContractAndStatus(String code, String status) {
        Optional<Note> note = noteRepository.findByContractAndStatus(code, status);
        if (note.isPresent()) {
            return NoteMapper.toNoteDto(note.get());
        }
        throw exception(NOTE, ENTITY_NOT_FOUND, code);
    }

    @Override
    public Long countAllNotes() {
        return noteRepository.count() + 1;
    }

    @Override
    public NoteDto createNote(Long contractId, Long userId, NoteDto noteDto) {
        Optional<Contract> contract = contractRepository.findById(contractId);

        if (contract.isPresent()) {
            Optional<User> user = userRepository.findById(userId);

            Note note = new Note()
                    .setContract(contract.get())
                    .setNumber(noteDto.getSequence())
                    .setYear(noteDto.getYear())
                    .setSubject(noteDto.getSubject())
                    .setStatus(noteDto.getStatus())
                    .setUser(user.get());

            return NoteMapper.toNoteDto(noteRepository.save(note));
        }

        throw exception(CONTRACT, ENTITY_NOT_FOUND, "Contract ID" + contractId);
    }

    @Override
    public NoteDto updateNote(Long id, NoteDto noteDto) {
        return null;
    }

    @Override
    public NoteDto deleteNoteById(Long id) {
        return null;
    }

    @Override
    public List<NoteDto> findAll() {
        List<NoteDto> noteDtoList = new ArrayList<>();
        noteRepository.findAll()
                .forEach(note -> {
                    noteDtoList.add(NoteMapper.toNoteDto(note));
                });
        return noteDtoList;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
