package com.parth.rbac.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginUserDto {

   @NotEmpty(message = "Email Required")
   @NotBlank(message = "Email Required")
   private String email;
   private String password;
}
