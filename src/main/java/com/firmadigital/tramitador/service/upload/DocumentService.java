package com.firmadigital.tramitador.service.upload;

import com.firmadigital.tramitador.dto.model.upload.DocumentDto;

import java.util.List;

public interface DocumentService {

    DocumentDto findByDocumentId(Long id);
    DocumentDto createDocument(Long attacheId, DocumentDto documentDto);
    DocumentDto updateDocument(DocumentDto documentDto);
    List<DocumentDto> findAllByAttacheId(Long attacheId);
    List<DocumentDto> findAllDocuments();
}
