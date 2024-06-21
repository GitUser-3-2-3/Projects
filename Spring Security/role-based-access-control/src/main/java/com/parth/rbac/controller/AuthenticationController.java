package com.parth.rbac.controller;

import com.parth.rbac.dto.LoginResponseDto;
import com.parth.rbac.dto.LoginUserDto;
import com.parth.rbac.dto.RegisterUserDto;
import com.parth.rbac.model.User;
import com.parth.rbac.security.JwtTokenUtil;
import com.parth.rbac.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

   private final AuthenticationService service;
   private final JwtTokenUtil jwtTokenUtil;

   @PostMapping("/signup")
   public ResponseEntity<User> register(
        @RequestBody @Valid RegisterUserDto request
   ) {
      User registeredUser = service.signUp(request);
      return ResponseEntity.ok(registeredUser);
   }

   @PostMapping("/login")
   public ResponseEntity<LoginResponseDto> authenticate(
        @RequestBody @Valid LoginUserDto request
   ) {
      LoginResponseDto response = service.buildResponse(request);
      return ResponseEntity.ok(response);
   }
}
