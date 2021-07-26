package com.firmadigital.tramitador.repository.user;

import com.firmadigital.tramitador.model.user.Role;
import com.firmadigital.tramitador.model.user.UserRoles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.role = ?1")
    Role findByRole(UserRoles role);
    @Override
    Optional<Role> findById(Long id);
    List<Role> findAll();
}
