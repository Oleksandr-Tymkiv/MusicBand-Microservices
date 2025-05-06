package com.musicband.message.dto.tour;

import java.time.LocalDate;

public record TourMsgDto(
        Long tourId,
        String title,
        LocalDate tourDate,
        String country,
        String area
) {
}
