package com.firmadigital.tramitador.repository.customer;

import com.firmadigital.tramitador.model.customer.Activity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {

    @Override
    Optional<Activity> findById(Long id);

    @Query("SELECT a FROM Activity a WHERE a.activityGroup.id = ?1")
    List<Activity> findActivityByGroupId(Long id);

    List<Activity> findAll();
}
