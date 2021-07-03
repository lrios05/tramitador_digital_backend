package com.firmadigital.tramitador.repository.customer;

import com.firmadigital.tramitador.model.customer.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {

    @Override
    Optional<Activity> findById(Long id);

    List<Activity> findAll();
}
