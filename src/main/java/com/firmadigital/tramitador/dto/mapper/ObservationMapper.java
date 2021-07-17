package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.observation.ObservationDto;
import com.firmadigital.tramitador.model.observation.Observation;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ObservationMapper {

    public static ObservationDto toObservationDto(Observation observation) {

        return new ObservationDto()
                .setContractDto(ContractMapper.toContractDto(observation.getContract()))
                .setUserDto(UserMapper.toUserDto(observation.getUser()))
                .setStatus(observation.getStatus())
                .setCommentDtoList(observation.getComments()
                        .stream().map(comment -> {
                            return CommentMapper.toCommentDto(comment);
                        }).collect(Collectors.toList())
                );
    }
}
