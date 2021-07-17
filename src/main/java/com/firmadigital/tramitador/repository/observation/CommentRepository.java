package com.firmadigital.tramitador.repository.observation;

import com.firmadigital.tramitador.model.observation.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    @Override
    Optional<Comment> findById(Long id);

    List<Comment> findAll();
}
