package com.jwt.security.service;

import com.jwt.security.auth.AuthRequest;
import com.jwt.security.auth.AuthResponse;
import com.jwt.security.auth.RegRequest;
import com.jwt.security.repo.UserRepo;
import com.jwt.security.user.Roles;
import com.jwt.security.user.Users;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserService(
        UserRepo userRepository, PasswordEncoder passwordEncoder,
        AuthenticationManager authManager,
        JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authManager;
        this.jwtService = jwtService;
    }

    public AuthResponse registerUser(final RegRequest req) {
        var user = Users.builder()
            .userName(req.getUserName()).userEmail(req.getUserEmail())
            .userPass(passwordEncoder.encode(req.getUserPass()))
            .role(Roles.USER).build();

        userRepository.save(user);

        return AuthResponse.builder().userName(req.getUserName())
            .userEmail(user.getUserEmail()).userRole(user.getRole())
            .build();
    }

    public String verifyUser(final AuthRequest req) {
        userAuthentication(req.getUserEmail(), req.getUserPass());

        var user = userRepository.findByUserEmail(req.getUserEmail())
            .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        return jwtService.generateToken(user);
    }

    private void userAuthentication(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );
        if (!authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("Authentication failed.");
        }
    }
}








