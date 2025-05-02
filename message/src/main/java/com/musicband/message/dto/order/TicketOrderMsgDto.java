package com.musicband.message.dto.order;

import java.util.UUID;

public record TicketOrderMsgDto(
        UUID orderId,
        Double price,
        String userEmail
){}
