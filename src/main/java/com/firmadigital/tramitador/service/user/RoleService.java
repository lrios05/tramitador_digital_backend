package com.firmadigital.tramitador.service.user;

import com.firmadigital.tramitador.dto.model.user.RoleDto;

public interface RoleService {

    RoleDto findByRole(RoleDto roleDto);
}
