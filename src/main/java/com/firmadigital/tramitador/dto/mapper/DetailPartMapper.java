package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.tracking.DetailPartDto;
import com.firmadigital.tramitador.model.tracking.Detail;
import org.springframework.stereotype.Component;

@Component
public class DetailPartMapper {
    public static DetailPartDto toDetailPartDto(Detail detail){
        return new DetailPartDto()
                .setNoteId(detail.getNote().getId())
                .setComment(detail.getComment())
                .setPriority(detail.getPriority())
                .setFromUserDto(UserRoleMapper.toUserRoleDto(detail.getFromUser()))
                .setToUserDto(UserRoleMapper.toUserRoleDto(detail.getToUser()))
                .setSendDate(detail.getSendDate())
                .setReceiveDate(detail.getReceiveDate())
                .setSendStatus(detail.getSendStatus())
                .setReceivedStatus(detail.getReceivedStatus())
                .setInstruction(detail.getInstruction().getInstruction());
    }
}
