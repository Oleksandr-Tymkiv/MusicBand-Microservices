package com.musicband.tour.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TourDto {
    private String title;
    private LocalDateTime tourDate;
    private String country;
    private String area;
}
