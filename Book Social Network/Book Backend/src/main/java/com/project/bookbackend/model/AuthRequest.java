package com.project.bookbackend.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthRequest {

    @Email(message = "Invalid Email")
    @Column(unique = true)
    private String userEmail;

    @Size(min = 8, message = "Password can't be << 8")
    private String password;
}
