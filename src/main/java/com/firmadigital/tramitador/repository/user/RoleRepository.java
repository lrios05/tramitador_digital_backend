package com.firmadigital.tramitador.repository.user;

import com.firmadigital.tramitador.model.user.Role;
import com.firmadigital.tramitador.model.user.UserRoles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByRole(UserRoles role);
}
