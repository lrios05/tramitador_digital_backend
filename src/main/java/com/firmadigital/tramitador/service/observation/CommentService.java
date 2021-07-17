package com.firmadigital.tramitador.service.observation;

import com.firmadigital.tramitador.dto.model.observation.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto findCommentById(Long id);
    CommentDto createComment(Long contractId, CommentDto commentDto);
    List<CommentDto> findAllComments();
}
