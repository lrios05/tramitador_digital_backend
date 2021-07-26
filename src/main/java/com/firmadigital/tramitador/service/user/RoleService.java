package com.firmadigital.tramitador.service.user;

import com.firmadigital.tramitador.dto.model.user.RoleDto;
import com.firmadigital.tramitador.model.user.UserRoles;

import java.util.List;

public interface RoleService {

    RoleDto findByRole(UserRoles userRoles);
    RoleDto findRoleById(Long roleId);
    RoleDto createRole(RoleDto roleDto);
    RoleDto updateRole(RoleDto roleDto);
    void deleteRoleById(Long roleId);
    List<RoleDto> findAllRoles();
}
