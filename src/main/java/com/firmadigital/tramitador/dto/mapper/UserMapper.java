package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.user.RoleDto;
import com.firmadigital.tramitador.dto.model.user.UserDto;
import com.firmadigital.tramitador.model.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserDto toUserDto(User user){

        return new UserDto()
                .setUserId(user.getId())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setName(user.getName())
                .setPaternal(user.getPaternal())
                .setMaternal(user.getMaternal())
                .setRoles(new HashSet<RoleDto>(user
                        .getRoles()
                        .stream()
                        .map(role -> new ModelMapper().map(role, RoleDto.class))
                        .collect(Collectors.toSet())
                ));
    }
}
