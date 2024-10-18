package com.project.bookbackend.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RegisterRequest {

    @NotBlank(message = "Field mandatory.")
    @NotEmpty(message = "Field mandatory.")
    private String firstname;

    @NotBlank(message = "Field mandatory.")
    @NotEmpty(message = "Field mandatory.")
    private String lastname;

    private LocalDate dateOfBirth;

    @Email(message = "Invalid Email")
    @Column(unique = true)
    private String userEmail;

    @Size(min = 8, message = "Password can't be << 8")
    private String password;
}







