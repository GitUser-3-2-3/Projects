package com.project.bookbackend.service;

import com.project.bookbackend.email.EmailService;
import com.project.bookbackend.email.EmailTemplateName;
import com.project.bookbackend.model.RegisterRequest;
import com.project.bookbackend.repo.RoleRepository;
import com.project.bookbackend.repo.TokenRepository;
import com.project.bookbackend.repo.UserRepository;
import com.project.bookbackend.token.Token;
import com.project.bookbackend.user.User;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public AuthenticationService(
        RoleRepository roleRepository, PasswordEncoder passwordEncoder,
        UserRepository userRepository, TokenRepository tokenRepository, EmailService emailService
    ) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
    }

    public void registerUser(RegisterRequest req) throws MessagingException {
        var userRole = roleRepository.findByName("USER")
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role wasn't initialized."));

        var user = User.builder().firstname(req.getFirstname()).lastname(req.getLastname())
            .dateOfBirth(req.getDateOfBirth()).userEmail(req.getUserEmail())
            .password(passwordEncoder.encode(req.getPassword()))
            .accountLocked(false).accountEnabled(false)
            .roles(List.of(userRole))
            .build();

        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);

        emailService.sendEmail(user.getUserEmail(), user.getFullName(),
            EmailTemplateName.ACTIVATE_ACCOUNT, activationUrl,
            newToken, "Account activation"
        );
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode();

        var token = Token.builder().token(generatedToken)
            .createdAT(LocalDateTime.now())
            .expiresAT(LocalDateTime.now().plusMinutes(15))
            .user(user).build();

        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode() {
        String chars = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < 6; i++) {
            int random = secureRandom.nextInt(chars.length());
            codeBuilder.append(chars.charAt(random));
        }
        return codeBuilder.toString();
    }
}








