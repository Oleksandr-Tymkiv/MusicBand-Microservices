package com.musicband.tour.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TourDto {
    @NotEmpty(message = "Title cannot be a null or empty")
    private String title;
    @NotNull(message = "Tour Date cannot be null")
    private LocalDate tourDate;
    @NotEmpty(message = "Country cannot be a null or empty")
    private String country;
    @NotEmpty(message = "Area cannot be a null or empty")
    private String area;
}
