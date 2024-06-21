package com.parth.rbac.service;

import com.parth.rbac.dto.LoginResponseDto;
import com.parth.rbac.dto.LoginUserDto;
import com.parth.rbac.dto.RegisterUserDto;
import com.parth.rbac.model.Role;
import com.parth.rbac.model.User;
import com.parth.rbac.model.UserRole;
import com.parth.rbac.repository.RoleRepository;
import com.parth.rbac.repository.UserRepository;
import com.parth.rbac.repository.UserRoleRepository;
import com.parth.rbac.security.JwtTokenUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final RoleRepository roleRepository;
   private final AuthenticationManager authenticationManager;
   private final JwtTokenUtil jwtTokenUtil;
   private final UserRoleRepository userRoleRepository;

   public User signUp(@Valid RegisterUserDto request) {
      var role = findUserRole("ADMIN");
      var user = createUser(request);

      User savedUser = userRepository.save(user);

      var userRole = createUserRole(role, user);
      user.setUserRoles(Set.of(userRole));
      userRoleRepository.save(userRole);

      return savedUser;
   }

   @SuppressWarnings("SameParameterValue")
   private Role findUserRole(String roleName) {
      return roleRepository.findByName(roleName)
           .orElseThrow(() -> new IllegalStateException("Role " + roleName + " was not initialized"));
   }

   private UserRole createUserRole(Role role, User user) {
      var userRole = new UserRole();
      userRole.setRole(role);
      userRole.setUser(user);
      return userRole;
   }

   private User createUser(RegisterUserDto request) {
      return User.builder().firstname(request.getFirstname())
           .lastname(request.getLastname())
           .email(request.getEmail())
           .password(passwordEncoder.encode(request.getPassword()))
           .accountLocked(false).enabled(true)
           .build();
   }

   public User authenticate(LoginUserDto request) {
      Authentication authentication = authenticationManager.authenticate(
           new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
           )
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);

      return userRepository.findByEmail(request.getEmail())
           .orElseThrow(() ->
                new IllegalArgumentException("No user found with email " + request.getEmail())
           );
   }

   public LoginResponseDto buildResponse(@Valid LoginUserDto request) {
      User authenticatedUser = authenticate(request);
      final String jwtToken = jwtTokenUtil.generateToken(authenticatedUser);
      long expiration = jwtTokenUtil.extractExpiration(jwtToken).getTime();

      return LoginResponseDto.builder()
           .token(jwtToken).expiresIn(expiration)
           .build();
   }
}
