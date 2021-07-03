package com.firmadigital.tramitador.service.user;

import com.firmadigital.tramitador.dto.model.user.UserDto;

public interface UserService {

    UserDto findById(Long userId);

    UserDto findUserByEmail(String email);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);
}
