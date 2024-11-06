package com.project.bookbackend.auth;

import com.project.bookbackend.email.EmailService;
import com.project.bookbackend.email.EmailTemplateName;
import com.project.bookbackend.repo.RoleRepository;
import com.project.bookbackend.repo.TokenRepository;
import com.project.bookbackend.repo.UserRepository;
import com.project.bookbackend.security.Token;
import com.project.bookbackend.security.JwtService;
import com.project.bookbackend.user.User;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public AuthenticationService(
        RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository,
        TokenRepository tokenRepository, EmailService emailService,
        AuthenticationManager authManager, JwtService jwtService
    ) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.authManager = authManager;
        this.jwtService = jwtService;
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

    public AuthResponse verifyUser(AuthRequest req) {
        var auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                req.getUserEmail(),
                req.getPassword()
            )
        );
        var extraClaims = new HashMap<String, Object>();
        var userDetails = ((User) auth.getPrincipal());

        extraClaims.put("fullName", userDetails.getFullName());
        var jwtToken = jwtService.generateToken(extraClaims, userDetails);

        return AuthResponse.builder().token(jwtToken).build();
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
        StringBuilder codeBuilder = new StringBuilder();
        String chars = "0123456789";
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < 6; i++) {
            int random = secureRandom.nextInt(chars.length());
            codeBuilder.append(chars.charAt(random));
        }
        return codeBuilder.toString();
    }

    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (LocalDateTime.now().isAfter(savedToken.getExpiresAT())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Token Expired. New Token Sent.");
        }
        validateUserAndSaveToken(savedToken);
    }

    private void validateUserAndSaveToken(Token savedToken) {
        var user = userRepository.findById(savedToken.getUser().getId())
            .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        user.setAccountEnabled(true);
        userRepository.save(user);

        savedToken.setValidatedAT(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }
}







