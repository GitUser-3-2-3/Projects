package com.jwt.security.controller;

import com.jwt.security.auth.AuthRequest;
import com.jwt.security.auth.AuthResponse;
import com.jwt.security.auth.RegRequest;
import com.jwt.security.service.UserService;
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
    public ResponseEntity<AuthResponse> registerUser(@RequestBody @Valid RegRequest req) {
        if (req == null) {
            throw new ResponseStatusException(BAD_REQUEST);
        }
        return new ResponseEntity<>(
            userService.registerUser(req), CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> verifyUser(@RequestBody @Valid AuthRequest req) {
        boolean verified = userService.verifyUser(req);

        if (verified) {
            return new ResponseEntity<>("Logged in Successfully", OK);
        }
        return new ResponseEntity<>("Login Failed", UNAUTHORIZED);
    }
}







