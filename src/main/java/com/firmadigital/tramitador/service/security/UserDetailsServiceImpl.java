package com.firmadigital.tramitador.service.security;

import com.firmadigital.tramitador.dto.model.user.RoleDto;
import com.firmadigital.tramitador.dto.model.user.UserDto;
import com.firmadigital.tramitador.model.user.PrincipalUser;
import com.firmadigital.tramitador.model.user.Role;
import com.firmadigital.tramitador.model.user.User;
import com.firmadigital.tramitador.model.user.UserRoles;
import com.firmadigital.tramitador.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserDto userDto = userService.findUserByEmail(userName);

        return PrincipalUser.build(toUser(userDto));
    }

    private User toUser(UserDto userDto){
        return new User()
                .setName(userDto.getFullName())
                .setPaternal(userDto.getPaternal())
                .setMaternal(userDto.getMaternal())
                .setEmail(userDto.getEmail())
                .setPassword(userDto.getPassword())
                .setRoles(toRole(userDto.getRoles()));
    }

    private Collection<Role> toRole(Collection<RoleDto> roleDtoCollection) {
        Collection<Role> roles = new HashSet<>();

        roleDtoCollection.forEach(roleDto -> {
            Role role = new Role()
                    .setRole(UserRoles.valueOf(roleDto.getRole()))
                    .setStatus(roleDto.getStatus());

            roles.add(role);
        });

        return roles;
    }
}
