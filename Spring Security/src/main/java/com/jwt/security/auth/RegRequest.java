package com.jwt.security.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegRequest {

    @NotBlank(message = "Username is mandatory.")
    private String userName;

    @NotBlank(message = "Email is mandatory.")
    private String userEmail;

    @NotBlank(message = "Password is mandatory.")
    private String userPass;

    @Override
    public String toString() {
        return "RegRequest{" +
            "userName='" + userName + '\'' +
            ", userEmail='" + userEmail + '\'' +
            ", userPass='" + userPass + '\'' +
            '}';
    }
}
