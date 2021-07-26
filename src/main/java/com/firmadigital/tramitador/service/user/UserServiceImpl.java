package com.firmadigital.tramitador.service.user;

import com.firmadigital.tramitador.dto.mapper.UserMapper;
import com.firmadigital.tramitador.dto.model.user.UserDto;
import com.firmadigital.tramitador.exception.EntityType;
import com.firmadigital.tramitador.exception.ExceptionManager;
import com.firmadigital.tramitador.exception.ExceptionType;
import com.firmadigital.tramitador.model.user.Role;
import com.firmadigital.tramitador.model.user.User;
import com.firmadigital.tramitador.model.user.UserRoles;
import com.firmadigital.tramitador.repository.user.RoleRepository;
import com.firmadigital.tramitador.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static com.firmadigital.tramitador.exception.EntityType.USER;
import static com.firmadigital.tramitador.exception.ExceptionType.ENTITY_NOT_FOUND;
import static com.firmadigital.tramitador.exception.ExceptionType.DUPLICATE_ENTITY;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        }

        throw exception(USER, ENTITY_NOT_FOUND, userId.toString());
    }

    @Transactional
    public UserDto findUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));

        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        }

        throw exception(USER, ENTITY_NOT_FOUND, email);
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        Role userRole;
        User user = userRepository.findByEmail(userDto.getEmail());

        if (user == null) {
            if (userDto.isAdmin()){
                userRole = roleRepository.findByRole(UserRoles.ADMIN);
            } else {
                userRole = roleRepository.findByRole(UserRoles.CLIENTE);
            }

            user = new User()
                    .setEmail(userDto.getEmail())
                    .setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()))
                    .setName(userDto.getName())
                    .setPaternal(userDto.getPaternal())
                    .setMaternal(userDto.getMaternal())
                    .setRoles(new HashSet<>(Arrays.asList(userRole)));

            return UserMapper.toUserDto(userRepository.save(user));
        }

        throw exception(USER, DUPLICATE_ENTITY, userDto.getEmail());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));

        if (user.isPresent()){
            User userModel = user.get();

            userModel.setName(userDto.getName())
                    .setPaternal(userDto.getPaternal())
                    .setMaternal(userDto.getMaternal());
            return UserMapper.toUserDto(userRepository.save(userModel));
        }

        throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ExceptionManager.throwException(entityType, exceptionType, args);
    }
}
