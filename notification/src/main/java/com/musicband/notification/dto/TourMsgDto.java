package com.musicband.notification.dto;

import lombok.ToString;

import java.time.LocalDateTime;

public record TourMsgDto(
        Long tourId,
        String title,
        LocalDateTime tourDate,
        String country,
        String area
) {
}
