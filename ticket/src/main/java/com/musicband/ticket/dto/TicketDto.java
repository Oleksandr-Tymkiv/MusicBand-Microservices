package com.musicband.ticket.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TicketDto {
    @Positive(message = "Price of ticket should be greater than zero")
    private Double price;
    @NotEmpty(message = "Place can not be a null or empty")
    private String place;
    @NotNull(message = "tourId cannot be null")
    private Long tourId;
}
