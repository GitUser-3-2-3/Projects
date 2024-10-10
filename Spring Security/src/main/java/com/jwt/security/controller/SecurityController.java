package com.jwt.security.controller;

import com.jwt.security.service.UserService;
import com.jwt.security.user.Users;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
public class SecurityController {

    private final UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody @Valid Users user) {
        if (user == null) {
            throw new ResponseStatusException(BAD_REQUEST);
        }
        return new ResponseEntity<>(
            userService.registerUser(user), CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> verifyUser(@RequestBody @Valid Users user) {
        boolean verified = userService.verifyUser(user);

        if (verified) {
            return new ResponseEntity<>("Logged in Successfully", OK);
        }
        return new ResponseEntity<>("Login Failed", UNAUTHORIZED);
    }
}







