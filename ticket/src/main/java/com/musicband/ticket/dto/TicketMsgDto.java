package com.musicband.ticket.dto;

public record TicketMsgDto(
        Double price,
        String place,
        Long tourId
) {
}
