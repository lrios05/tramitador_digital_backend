package com.firmadigital.tramitador.repository.tracking;

import com.firmadigital.tramitador.model.tracking.Detail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailRepository extends CrudRepository<Detail, Long> {

    @Override
    Optional<Detail> findById(Long id);

    @Query("SELECT d FROM Detail d WHERE d.note.id = ?1")
    List<Detail> findByNoteId(Long id);

    List<Detail> findAll();
}
