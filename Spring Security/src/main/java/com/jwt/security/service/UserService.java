package com.jwt.security.service;

import com.jwt.security.repo.UserRepo;
import com.jwt.security.user.Roles;
import com.jwt.security.user.Users;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserService(
        UserRepo userRepository, PasswordEncoder passwordEncoder,
        AuthenticationManager authManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authManager;
    }

    public Users registerUser(final Users req) {
        var user = Users.builder()
            .userName(req.getUsername()).userEmail(req.getUserEmail())
            .userPass(passwordEncoder.encode(req.getUserPass()))
            .role(Roles.USER)
            .build();

        return userRepository.save(user);
    }

    public boolean verifyUser(final Users req) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                req.getUserEmail(),
                req.getUserPass()
            )
        );
        return authentication.isAuthenticated();
    }
}








