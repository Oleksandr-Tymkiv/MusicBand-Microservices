package com.musicband.payment.dto;

import java.util.UUID;

public record TicketOrderMsgDto(
        UUID orderId,
        Double price,
        String userEmail
){}
