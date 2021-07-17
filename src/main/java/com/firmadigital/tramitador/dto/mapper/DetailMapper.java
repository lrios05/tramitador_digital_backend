package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.tracking.DetailDto;
import com.firmadigital.tramitador.model.tracking.Detail;
import org.springframework.stereotype.Component;

@Component
public class DetailMapper {
    public static DetailDto toDetailDto(Detail detail) {

        return new DetailDto()
                .setNoteDto(NoteMapper.toNoteDto(detail.getNote()))
                .setPriority(detail.getPriority())
                .setFromUserDto(UserMapper.toUserDto(detail.getFromUser()))
                .setToUserDto(UserMapper.toUserDto(detail.getToUser()))
                .setSendDate(detail.getSendDate())
                .setReceiveDate(detail.getReceiveDate())
                .setSendStatus(detail.getSendStatus())
                .setReceivedStatus(detail.getReceivedStatus())
                .setInstructionDto(InstructionMapper.toInstructionDto(detail.getInstruction()));
    }
}
