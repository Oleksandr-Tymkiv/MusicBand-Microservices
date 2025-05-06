package com.musicband.tour.dto;

import java.time.LocalDate;

public record TourMsgDto(
        Long tourId,
        String title,
        LocalDate tourDate,
        String country,
        String area
) {
}
