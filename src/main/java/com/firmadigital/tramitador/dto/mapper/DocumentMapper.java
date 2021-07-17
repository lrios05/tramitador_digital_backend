package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.upload.DocumentDto;
import com.firmadigital.tramitador.model.upload.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {

    public static DocumentDto toDocumentDto(Document document) {

        return new DocumentDto()
                .setDocType(document.getDocType())
                .setDocName(document.getDocName());
    }
}
