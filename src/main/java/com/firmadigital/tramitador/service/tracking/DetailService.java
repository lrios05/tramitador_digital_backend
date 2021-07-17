package com.firmadigital.tramitador.service.tracking;

import com.firmadigital.tramitador.dto.model.tracking.DetailDto;

import java.util.List;

public interface DetailService {

    DetailDto findById(Long id);
    List<DetailDto> findByNoteId(Long noteId);
    DetailDto createDetail(Long noteId, Long fromId, Long toId, DetailDto detailDto);
    DetailDto updateDetail(Long id, DetailDto detailDto);
    List<DetailDto> findAll();
}
