package com.musicband.merch.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MerchOrderDto {
    @NotNull(message = "Merch Id cannot be null")
    private Long merchId;
    @NotEmpty(message = "Email address cannot be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String userEmail;
}
