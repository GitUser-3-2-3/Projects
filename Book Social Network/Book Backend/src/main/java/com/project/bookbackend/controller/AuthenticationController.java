package com.project.bookbackend.controller;

import com.project.bookbackend.model.AuthRequest;
import com.project.bookbackend.model.AuthResponse;
import com.project.bookbackend.model.RegisterRequest;
import com.project.bookbackend.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication")
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationService authService;

    @Autowired
    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
        @RequestBody @Valid RegisterRequest req) throws MessagingException {

        authService.registerUser(req);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/verify")
    public ResponseEntity<AuthResponse> verifyUser(@RequestBody @Valid AuthRequest req) {
        return ResponseEntity.ok(authService.verifyUser(req));
    }

    @GetMapping("/activate-account")
    public void activateAccount(@RequestParam String token) throws MessagingException {
        authService.activateAccount(token);
    }
}







