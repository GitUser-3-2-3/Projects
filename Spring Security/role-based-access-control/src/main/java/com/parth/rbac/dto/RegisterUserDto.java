package com.parth.rbac.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterUserDto {

   @NotEmpty(message = "Firstname is mandatory")
   @NotBlank(message = "Firstname is mandatory")
   private String firstname;

   @NotEmpty(message = "Lastname is mandatory")
   @NotEmpty(message = "Lastname is mandatory")
   private String lastname;

   @NotEmpty(message = "Email is mandatory")
   @NotBlank(message = "Email is mandatory")
   private String email;

   @NotEmpty(message = "Password is mandatory")
   @NotBlank(message = "Password is mandatory")
   @Size(min = 8, message = "8 chars minimum")
   private String password;
}
