package com.jwt.security.auth;

import com.jwt.security.user.Roles;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponse {

    private String userName;
    private String userEmail;
    private Roles userRole;

    @Override
    public String toString() {
        return "AuthResponse{" +
            "userName='" + userName + '\'' +
            ", userEmail='" + userEmail + '\'' +
            ", userRole=" + userRole +
            '}';
    }
}
