package com.jwt.security.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthRequest {

    @NotBlank(message = "Email cannot be empty.")
    private String userEmail;

    @NotBlank(message = "Password is mandatory.")
    private String userPass;

    @Override
    public String toString() {
        return "AuthRequest{" +
            "userEmail='" + userEmail + '\'' +
            ", userPass='" + userPass + '\'' +
            '}';
    }
}
