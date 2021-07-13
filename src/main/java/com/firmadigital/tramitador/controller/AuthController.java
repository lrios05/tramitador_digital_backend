package com.firmadigital.tramitador.controller;

import com.firmadigital.tramitador.controller.request.UserLoginRequest;
import com.firmadigital.tramitador.dto.model.auth.JwtDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.security.jwt.JwtProvider;
import com.firmadigital.tramitador.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Response userLogin(@Valid @RequestBody UserLoginRequest userLoginRequest, BindingResult bindingResult){

        if (bindingResult.hasErrors())
            return Response.badRequest().setErrors(HttpStatus.BAD_REQUEST);
                    //new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getUserName(),
                        userLoginRequest.getPassword())
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return Response.ok().setPayload(jwtDto);
        //return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
