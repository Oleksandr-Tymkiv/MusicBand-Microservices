package com.musicband.notification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationDto {
    @NotEmpty(message = "Email address cannot be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;
    @NotNull(message = "Tour Id cannot be null")
    private Long tourId;
}
