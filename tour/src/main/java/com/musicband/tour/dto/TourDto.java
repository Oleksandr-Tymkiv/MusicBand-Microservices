package com.musicband.tour.dto;

import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Tour Date cannot be a null or empty")
    private LocalDate tourDate;
    @NotEmpty(message = "Country cannot be a null or empty")
    private String country;
    @NotEmpty(message = "Area cannot be a null or empty")
    private String area;
}
