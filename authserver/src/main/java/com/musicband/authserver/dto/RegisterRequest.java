package com.musicband.authserver.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "Name cannot be a null or empty")
    private String name;
    @NotEmpty(message = "Date of Birth cannot be a null or empty")
    private LocalDate dateOfBirth;
    @NotEmpty(message = "Email address cannot be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;
    @NotEmpty(message = "Password cannot be a null or empty")
    private String password;
}