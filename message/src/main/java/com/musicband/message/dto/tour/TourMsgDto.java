package com.musicband.message.dto.tour;

import java.time.LocalDateTime;

public record TourMsgDto(
        Long tourId,
        String title,
        LocalDateTime tourDate,
        String country,
        String area
) {
}
