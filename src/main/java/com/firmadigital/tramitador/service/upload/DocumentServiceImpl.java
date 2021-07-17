package com.firmadigital.tramitador.service.upload;

import com.firmadigital.tramitador.dto.model.upload.DocumentDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentServiceImpl implements DocumentService{
    @Override
    public DocumentDto findByDocumentId(Long id) {
        return null;
    }

    @Override
    public DocumentDto createDocument(Long attacheId, DocumentDto documentDto) {
        return null;
    }

    @Override
    public DocumentDto updateDocument(DocumentDto documentDto) {
        return null;
    }

    @Override
    public List<DocumentDto> findAllByAttacheId(Long attacheId) {
        return null;
    }

    @Override
    public List<DocumentDto> findAllDocuments() {
        return null;
    }
}
