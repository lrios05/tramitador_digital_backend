package com.firmadigital.tramitador.repository.tracking;

import com.firmadigital.tramitador.model.tracking.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {

    @Override
    Optional<Note> findById(Long id);

    @Query("SELECT n FROM Note n WHERE n.contract.id = ?1")
    Optional<Note> findByContractId(Long id);

    @Query("SELECT n FROM Note n WHERE n.number = ?1")
    Optional<Note> findNoteByNumber(int number);

    @Query("SELECT n FROM Note n WHERE n.status = ?1")
    Optional<Note> findNoteByStatus(String status);

    List<Note> findAll();

}
