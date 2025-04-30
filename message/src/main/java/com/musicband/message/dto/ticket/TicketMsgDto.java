package com.musicband.message.dto.ticket;

public record TicketMsgDto(
        Double price,
        String place,
        Boolean isPurchase,
        Long tourId
) {
}
