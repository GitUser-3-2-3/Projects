package com.parth.rbac.controller;

import com.parth.rbac.dto.LoginResponseDto;
import com.parth.rbac.dto.LoginUserDto;
import com.parth.rbac.dto.RegisterUserDto;
import com.parth.rbac.model.User;
import com.parth.rbac.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

   private final AuthenticationService service;

   @PostMapping("/signup")
   public ResponseEntity<?> register(
        @RequestBody @Valid RegisterUserDto request
   ) {
      try {
         User registeredUser = service.signUp(request);
         return ResponseEntity.ok(registeredUser);
      } catch (Exception e) {
         return ResponseEntity.status(INTERNAL_SERVER_ERROR)
              .body("An error occurred during sign up: " + e.getMessage());
      }
   }

   @PostMapping("/login")
   public ResponseEntity<?> authenticate(
        @RequestBody @Valid LoginUserDto request
   ) {
      try {
         LoginResponseDto response = service.buildResponse(request);
         return ResponseEntity.ok(response);
      } catch (Exception e) {
         return ResponseEntity.status(UNAUTHORIZED)
              .body("Invalid credentials: " + e.getMessage());
      }
   }
}
