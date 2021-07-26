package com.firmadigital.tramitador.service.tracking;

import com.firmadigital.tramitador.dto.mapper.DetailMapper;
import com.firmadigital.tramitador.dto.mapper.DetailPartMapper;
import com.firmadigital.tramitador.dto.model.tracking.DetailDto;
import com.firmadigital.tramitador.dto.model.tracking.DetailPartDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.tracking.Detail;
import com.firmadigital.tramitador.model.tracking.Instruction;
import com.firmadigital.tramitador.model.tracking.Note;
import com.firmadigital.tramitador.model.user.User;
import com.firmadigital.tramitador.repository.tracking.DetailRepository;
import com.firmadigital.tramitador.repository.tracking.InstructionRepository;
import com.firmadigital.tramitador.repository.tracking.NoteRepository;
import com.firmadigital.tramitador.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.firmadigital.tramitador.exception.EntityType.DETAIL;
import static com.firmadigital.tramitador.exception.EntityType.NOTE;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class DetailServiceImpl implements DetailService{

    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private InstructionRepository instructionRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public DetailDto findById(Long id) {
        Optional<Detail> detail = detailRepository.findById(id);
        if (detail.isPresent()) {
            return DetailMapper.toDetailDto(detail.get());
        }
        throw exception(DETAIL, ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public List<DetailPartDto> findByNoteId(Long noteId) {
        List<DetailPartDto> detailPartDtoList = new ArrayList<>();
        detailRepository.findByNoteId(noteId)
                .forEach(detail -> {
                    detailPartDtoList.add(DetailPartMapper.toDetailPartDto(detail));
                });
        return detailPartDtoList;

       // throw exception(DETAIL, ENTITY_NOT_FOUND, noteId.toString());
    }

    @Override
    public DetailDto createDetail(Long noteId, Long fromId, Long toId, DetailDto detailDto) {
        Optional<Note> note = noteRepository.findById(noteId);
        Optional<Instruction> instruction = instructionRepository.findById(detailDto.getInstructionDto().getInstructionId());

        if (note.isPresent()) {

            Detail detail = new Detail()
                    .setNote(note.get())
                    .setComment(detailDto.getComment())
                    .setPriority(detailDto.getPriority())
                    .setDeadline(detailDto.getDeadline())
                    .setFromUser(getUser(fromId))
                    .setToUser(getUser(toId))
                    .setSendDate(detailDto.getSendDate())
                    .setReceiveDate(detailDto.getReceiveDate())
                    .setSendStatus(detailDto.getSendStatus())
                    .setReceivedStatus(detailDto.getReceivedStatus())
                    .setInstruction(instruction.get());

            return DetailMapper.toDetailDto(detailRepository.save(detail));
        }
        throw exception(NOTE, ENTITY_NOT_FOUND, noteId.toString());
    }

    private User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public DetailDto updateDetail(Long id, DetailDto detailDto) {
        return null;
    }

    @Override
    public List<DetailDto> findAll() {
        List<DetailDto> detailDtoList = new ArrayList<>();
        detailRepository.findAll()
                .forEach(detail -> {
                    detailDtoList.add(DetailMapper.toDetailDto(detail));
                });
        return detailDtoList;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
