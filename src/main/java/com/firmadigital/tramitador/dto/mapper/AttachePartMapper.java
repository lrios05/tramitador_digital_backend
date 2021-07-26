package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.upload.AttacheDto;
import com.firmadigital.tramitador.dto.model.upload.AttachePartDto;
import com.firmadigital.tramitador.model.upload.Attache;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AttachePartMapper {
    public static AttachePartDto toAttachePartDto(Attache attache) {

        return new AttachePartDto()
                .setAttacheId(attache.getId())
                .setContractPartDto(ContractPartMapper.toContractPartDto(attache.getContract()))
                .setUserRoleDto(UserRoleMapper.toUserRoleDto(attache.getUser()))
                .setOriginUrl(attache.getOriginUrl())
                .setDocumentDtoList(attache.getDocuments()
                        .stream().map(document -> {
                            return DocumentMapper.toDocumentDto(document);
                        }).collect(Collectors.toList())
                );
    };
}
