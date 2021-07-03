package com.firmadigital.tramitador.repository.customer;

import com.firmadigital.tramitador.model.customer.ActivityGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityGroupRepository extends CrudRepository<ActivityGroup, Long> {

    @Override
    Optional<ActivityGroup> findById(Long id);

    List<ActivityGroup> findAll();
}
