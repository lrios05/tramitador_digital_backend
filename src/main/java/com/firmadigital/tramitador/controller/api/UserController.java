package com.firmadigital.tramitador.controller.api;

import com.firmadigital.tramitador.controller.request.UserSignupRequest;
import com.firmadigital.tramitador.dto.model.user.UserDto;
import com.firmadigital.tramitador.dto.response.Response;
import com.firmadigital.tramitador.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/find/{email}")
    public Response findByEmail(@PathVariable("email") String email) {
        return Response.ok().setPayload(userService.findUserByEmail(email));
    }

    @PostMapping("/post")
    public @ResponseBody ResponseEntity<String> post() {
        return new ResponseEntity<String>("POST Response", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public Response signup(@Valid @RequestBody UserSignupRequest userSignupRequest) {
        return Response.ok().setPayload(registerUser(userSignupRequest, false));
    }

    public UserDto registerUser(UserSignupRequest userSignupRequest, Boolean isAdmin) {

        UserDto userDto = new UserDto()
                .setEmail(userSignupRequest.getEmail())
                .setPassword(userSignupRequest.getPassword())
                .setName(userSignupRequest.getName())
                .setPaternal(userSignupRequest.getPaternal())
                .setMaternal(userSignupRequest.getMaternal())
                .setAdmin(isAdmin);

        return userService.createUser(userDto);
    }

}
