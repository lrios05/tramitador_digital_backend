package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.upload.AttacheDto;
import com.firmadigital.tramitador.model.upload.Attache;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AttacheMapper {

    public static AttacheDto toAttacheDto(Attache attache) {

        return new AttacheDto()
                .setAttacheId(attache.getId())
                .setContractDto(ContractMapper.toContractDto(attache.getContract()))
                .setUserDto(UserMapper.toUserDto(attache.getUser()))
                .setOriginUrl(attache.getOriginUrl())
                .setDocumentDtoList(attache.getDocuments()
                    .stream().map(document -> {
                        return DocumentMapper.toDocumentDto(document);
                        }).collect(Collectors.toList())
                );
    };
}
