package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.user.RoleDto;
import com.firmadigital.tramitador.model.user.Role;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public static RoleDto toRoleDto(Role role){
        return new RoleDto()
                .setRoleId(role.getId())
                .setRole(role.getRole().toString())
                .setDescription(role.getDescription())
                .setStatus(role.getStatus())
                .setUsers(role.getUsers()
                    .stream().map(user -> {
                        return UserRoleMapper.toUserRoleDto(user);
                        }).collect(Collectors.toList())
                );
    }
}
