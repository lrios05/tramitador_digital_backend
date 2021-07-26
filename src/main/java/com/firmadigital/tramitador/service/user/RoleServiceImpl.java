package com.firmadigital.tramitador.service.user;

import com.firmadigital.tramitador.dto.mapper.RoleMapper;
import com.firmadigital.tramitador.dto.model.user.RoleDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.user.Role;
import com.firmadigital.tramitador.model.user.UserRoles;
import com.firmadigital.tramitador.repository.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.firmadigital.tramitador.exception.EntityType.ROLE;
import static com.firmadigital.tramitador.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDto findByRole(UserRoles userRoles) {
        Role role  = roleRepository.findByRole(userRoles);

        if (role != null) {
            return RoleMapper.toRoleDto(role);
        }
        throw exception(ROLE, ENTITY_NOT_FOUND, userRoles.toString());
    }

    @Override
    public RoleDto findRoleById(Long roleId) {
        Optional<Role> role  = roleRepository.findById(roleId);

        if (role.isPresent()) {
            return RoleMapper.toRoleDto(role.get());
        }
        throw exception(ROLE, ENTITY_NOT_FOUND, roleId.toString());
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        UserRoles userRole = UserRoles.valueOf(roleDto.getRole());
        Role role = roleRepository.findByRole(userRole);

        if (role == null) {
            role = new Role()
                    .setRole(userRole)
                    .setDescription(roleDto.getDescription())
                    .setStatus('V');

            return RoleMapper.toRoleDto(roleRepository.save(role));
        }
        throw exception(ROLE, DUPLICATE_ENTITY, userRole.toString());
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto) {
        return null;
    }

    @Override
    public void deleteRoleById(Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isPresent()){
            roleRepository.deleteById(roleId);
        }
    }

    @Override
    public List<RoleDto> findAllRoles() {
        List<RoleDto> roleDtoList = new ArrayList<>();
        roleRepository.findAll()
                .forEach(role -> {
                    roleDtoList.add(RoleMapper.toRoleDto(role));
                });

        return roleDtoList;
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
