package com.firmadigital.tramitador.dto.mapper;

import com.firmadigital.tramitador.dto.model.user.UserRoleDto;
import com.firmadigital.tramitador.model.user.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserRoleMapper {

    public static UserRoleDto toUserRoleDto(User user) {
        return new UserRoleDto()
                .setUserId(user.getId())
                .setUserName(user.getName() + " " + user.getPaternal() + " " + user.getMaternal())
                .setRole(user.getRoles()
                        .stream().findFirst()
                        .get().getDescription()
                );
    }
}
