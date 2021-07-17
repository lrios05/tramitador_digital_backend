package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.observation.CommentDto;
import com.firmadigital.tramitador.model.observation.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public static CommentDto toCommentDto(Comment comment) {

        return new CommentDto()
                .setComment(comment.getComment())
                .setStatus(comment.getStatus());
    }
}
