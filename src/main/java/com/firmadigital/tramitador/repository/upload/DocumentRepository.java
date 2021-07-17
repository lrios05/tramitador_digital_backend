package com.firmadigital.tramitador.repository.upload;

import com.firmadigital.tramitador.model.upload.Document;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {

    @Override
    Optional<Document> findById(Long id);

    List<Document> findAll();

}
